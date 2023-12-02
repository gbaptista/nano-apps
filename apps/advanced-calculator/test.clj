(ns test.nano-app
  (:require [clojure.test :refer :all]))

(load-file "test/helpers.clj")

(def app-path "apps/advanced-calculator")

(deftest test-nano-app
  (testing "Specification"
    (let [specification (h.load-specification app-path)]
      (is (= "advanced-calculator" (:name specification)))
      (is (= true (contains? specification :description)))

      (is (= ["expression"] (get-in specification [:parameters :required])))
      (is (= {"expression" ""} (h.sample-input specification)))))

  (testing "Nano App"
    (is (= "ans = 6" (h.run-app app-path {"expression" "2 * 3"})))
    (is (= "ans = 100" (h.run-app app-path {"expression" "2 + 2 * 49"})))
    (is (= "ans = 1.5000" (h.run-app app-path {"expression" "3 / 2"})))))

(clojure.test/run-tests 'test.nano-app)
