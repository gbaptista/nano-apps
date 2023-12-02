(require '[babashka.fs :as fs])
(require '[clojure.java.shell :refer [sh]])

(doseq [path (fs/glob "." "**/*.clj")]
  (let [result (sh "cljfmt" "fix" (str path))
        output (if (= (:out result) "") nil (:out result))
        error  (if (= (:err result) "") nil (:err result))]
    (when (or output error) (println ""))
    (println (str "cljfmt fix " path))
    (cond
      output (println output)
      error  (println error))))
