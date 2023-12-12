(require '[clojure.java.shell :refer [sh]])

(defn run-command [command & [input]]
  (let [args (if input (conj command :in input) command)
        result (apply sh args)
        exit-status (:exit result)
        is-error (or (not= exit-status 0) (and (:err result) (not (empty? (:err result)))))
        output (if is-error (:err result) (:out result))]
    {:is-error is-error :output output :exit-status exit-status}))

(defn run-piped-commands [piped-commands]
  (reduce (fn [acc cmd]
            (if (:is-error acc) acc (run-command cmd (:output acc))))
          {:output nil}
          piped-commands))

(let [command        (get parameters "command")
      piped-commands (get parameters "piped_commands")]
  (cond
    (and (not (nil? command)) (not (nil? piped-commands)))
    "Error: You need to provide either a command or piped_commands, not both."
    (not (nil? command))
    (:output (run-command command))
    (not (nil? piped-commands))
    (:output (run-piped-commands piped-commands))
    :else "Invalid or missing parameters"))
