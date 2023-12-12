(ns test.nano-app
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))

(load-file "test/helpers.clj")

(def app-path "apps/media-player-control")

(deftest test-nano-app
  (testing "Specification"
    (let [specification (h.load-specification app-path)]
      (is (= "media-player-control" (:name specification)))
      (is (= true (contains? specification :description)))

      (is (= ["command"] (get-in specification [:parameters :required])))
      (is (= {"command" "", "option" "", "player" ""} (h.sample-input specification)))))

  (testing "Nano App"
    (is (= "Invalid Command" (h.run-app app-path {"command" "N"})))
    (is (not= "Invalid Command" (h.run-app app-path {"command" "status"})))))

(clojure.test/run-tests 'test.nano-app)
