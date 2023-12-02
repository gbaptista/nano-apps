(ns h)

(require '[clojure.java.io :as io])
(require '[babashka.fs :as fs])
(require '[clj-yaml.core :as yaml])

(defn load-specification [path]
  (yaml/parse-string (slurp (io/file (str path "/" "specification.yml")))))

(defn sample-value [type items]
  (cond
    (= type "string") ""
    (= type "integer") 0
    (= type "number") 0.0
    (= type "boolean") true
    (= type "array")
    (cond
      (= (:type items) "string") [""]
      (= (:type (:items items)) "string") [[""]]
      :else :TODO)
    :else :TODO))

(defn sample-input [parsed]
  (let [params (:parameters parsed)
        props (:properties params)]
    (->> props
         (map (fn [[k v]] [(name k) (sample-value (:type v) (:items v))]))
         (into {}))))

(require '[clojure.pprint :as pprint])

(defn run-app [source-path & parameters]
  (let [parameters (if (nil? parameters) {} (first parameters))
        source (slurp (io/file (str source-path "/app.clj")))
        nano-app-path (fs/create-temp-file {:prefix "nano-app-" :suffix ".clj"})
        nano-app-path-str (.toString nano-app-path)]
    (spit nano-app-path-str (str "(def parameters " (pr-str parameters) ")\n\n" source))
    (load-string (slurp nano-app-path-str))))
