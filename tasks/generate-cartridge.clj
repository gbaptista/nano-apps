(require '[clojure.java.io :as io])
(require '[clj-yaml.core :as yaml])
(require '[babashka.fs :as fs])
(require '[clojure.string :as str])

(defn list-app-dirs [dir]
  (->> (fs/glob dir "**/app.clj")
       (map fs/parent)))

(defn format-specification [spec-contents]
  (let [lines (str/split-lines spec-contents)
        filtered-lines (if (= (first lines) "---")
                         (rest lines)
                         lines)]
    (str "  - " (first filtered-lines) "\n"
         (str/join "\n" (map (fn [line] (str "    " line)) (rest filtered-lines))))))

(defn format-app-content [app-contents]
  (->> app-contents
       (str/split-lines)
       (map (fn [line] (str "      " line)))
       (str/join "\n")))

(defn concatenate-app-contents [app-dirs]
  (apply str (mapcat (fn [dir]
                       ["\n"
                        (format-specification (slurp (str dir "/specification.yml")))
                        "\n    clojure: |\n"
                        (format-app-content (slurp (str dir "/app.clj")))
                        "\n"])
                     app-dirs)))

(let [args          *command-line-args*
      template-path (first args)
      output-path   (second args)
      template      (slurp template-path)
      app-dirs      (list-app-dirs "apps/")
      apps-content  (concatenate-app-contents app-dirs)
      final-content (str/replace-first template "{tools}" apps-content)]
  (spit output-path final-content)
  (println (str "Cartridge successfully generated at " output-path)))
