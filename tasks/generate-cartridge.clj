(require '[clojure.java.io :as io])
(require '[clj-yaml.core :as yaml])
(require '[babashka.fs :as fs])
(require '[clojure.string :as str])

(defn list-app-dirs [dir no-private]
  (let [paths (->> (fs/glob dir "**/app.clj")
                   (map fs/parent))]
    (if no-private
      (filter (fn [path] (not (str/includes? path "@"))) paths)
      paths)))

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
      option        (nth args 2 nil)
      no-private    (= "--no-private" option)
      template      (slurp template-path)
      app-dirs      (list-app-dirs "apps/" no-private)
      apps-content  (concatenate-app-contents app-dirs)
      final-content (str/replace-first template "{tools}" apps-content)]
  (spit output-path final-content)
  (if no-private
    (println (str "Cartridge successfully generated (without private apps) at " output-path))
    (println (str "Cartridge successfully generated at " output-path))))

