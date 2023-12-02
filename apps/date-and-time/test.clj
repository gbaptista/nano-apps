(ns test.nano-app
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))

(load-file "test/helpers.clj")

(def app-path "apps/date-and-time")

(deftest test-nano-app
  (testing "Specification"
    (let [specification (h.load-specification app-path)]
      (is (= "date-and-time" (:name specification)))
      (is (= true (contains? specification :description)))

      (is (= nil (get-in specification [:parameters :required])))
      (is (= {"timezone" ""} (h.sample-input specification)))))

  (testing "Nano App"
    (is (= [:date-time :timezone] (keys (h.run-app app-path))))

    (is (= "America/New_York" (:timezone (h.run-app app-path {"timezone" "America/New_York"}))))
    (is (= "dddd-dd-ddTdd:dd:dd-dd:dd"
           (str/replace (:date-time (h.run-app app-path {"timezone" "America/New_York"})) #"\d" "d")))

    (let [date-time (:date-time (h.run-app app-path {"timezone" "America/New_York"}))]
      (is (= "-05:00"
             (subs date-time (max 0 (- (count date-time) 6))))))))

(clojure.test/run-tests 'test.nano-app)
