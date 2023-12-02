(ns test.nano-app
  (:require [clojure.test :refer :all]))

(load-file "test/helpers.clj")

(def app-path "apps/random-number")

(deftest test-nano-app
  (testing "Specification"
    (let [specification (h.load-specification app-path)]
      (is (= "random-number" (:name specification)))
      (is (= true (contains? specification :description)))

      (is (= ["from" "to"] (get-in specification [:parameters :required])))
      (is (= {"from" 0, "to" 0} (h.sample-input specification)))))

  (testing "Nano App"
    (is (= 5 (h.run-app app-path {"from" 5 "to" 5})))
    (let [result (h.run-app app-path {"from" 1 "to" 10})]
      (is (and (>= result 1) (<= result 10))))))

(clojure.test/run-tests 'test.nano-app)
