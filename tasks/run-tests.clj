(require '[babashka.fs :as fs])

(defn list-test-files [dir]
  (->> (fs/glob dir "**/*test.clj")
       (map fs/path)))

(doseq [path (list-test-files "apps/")]
  (load-file (str path)))
