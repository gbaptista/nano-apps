(require '[clojure.java.shell :refer [sh]])
(require '[clojure.string :as str])

(let [expression (get parameters "expression")
      result (sh "bc" :in (str expression "\n"))
      exit-status (:exit result)
      output (if (or
                  (not= exit-status 0)
                  (and (:err result) (not (empty? (:err result)))))
               (:err result)
               (:out result))]
  (str/replace output #"\r?\n+$" ""))
