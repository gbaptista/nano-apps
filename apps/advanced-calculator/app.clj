(require '[clojure.java.shell :refer [sh]])
(require '[babashka.fs :as fs])
(require '[clojure.string :as str])

(defn create-temp-file [prefix suffix]
  (str (fs/create-temp-file {:prefix prefix :suffix suffix})))

(let [expression (get parameters "expression")
      expression-file (create-temp-file "octave" ".m")
      output-file (create-temp-file "octave" ".txt")
      octave-expression (str "echo 'pkg load symbolic; " expression "' > " expression-file)
      run-command (str "octave --silent " expression-file " >" output-file " 2>&1")]

  (sh "bash" "-c" octave-expression)
  (sh "bash" "-c" run-command)

  (str/replace (slurp output-file) #"\r?\n+$" ""))
