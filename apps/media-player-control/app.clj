(require '[clojure.java.shell :refer [sh]])

(defn run-command [action option player]
  (let [command     (if (nil? action) ["playerctl"] ["playerctl" action])
        args        (if (and (not (nil? option)) (not= "" option)) (conj command option) command)
        args        (if (and (not (nil? player)) (not= "" player)) (conj args (str "--player=" player)) args)
        result      (apply sh args)
        exit-status (:exit result)
        is-error    (or (not= exit-status 0) (and (:err result) (not (empty? (:err result)))))
        output      (if is-error (:err result) (:out result))]
    {:is-error is-error :output output :exit-status exit-status}))

(let [action (get parameters "command")
      option (get parameters "option")
      player (get parameters "player")
      valid-actions ["list" "volume" "position" "play" "pause" "play-pause" "stop" "next" "previous" "status" "metadata"]]
  (cond
    (= action "list")
      (:output (run-command nil "-l" player))
    (some #{action} valid-actions)
      (:output (run-command action option player))
    :else "Invalid Command"))
