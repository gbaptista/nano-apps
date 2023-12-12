(require '[babashka.fs :as fs])
(require '[clojure.string :as str])

(defn list-test-files [dir no-private]
  (let [paths(fs/glob dir "**/*test.clj")]
    (if no-private
      (filter (fn [path] (not (str/includes? path "@"))) paths)
      paths)))

(let [args       *command-line-args*
      option     (nth args 0 nil)
      no-private (= "--no-private" option)]
  (doseq [path (list-test-files "apps/" no-private)]
    (load-file (str path))))
