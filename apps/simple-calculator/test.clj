(ns test.nano-app
  (:require [clojure.test :refer :all]))

(load-file "test/helpers.clj")

(def app-path "apps/simple-calculator")

(deftest test-nano-app
  (testing "Specification"
    (let [specification (h.load-specification app-path)]
      (is (= "simple-calculator" (:name specification)))
      (is (= true (contains? specification :description)))

      (is (= ["expression"] (get-in specification [:parameters :required])))
      (is (= {"expression" ""} (h.sample-input specification)))))

  (testing "Nano App"
    (is (= "6" (h.run-app app-path {"expression" "2 * 3"})))
    (is (= "100" (h.run-app app-path {"expression" "2 + 2 * 49"})))
    (is (= "4" (h.run-app app-path {"expression" "28 % 6"})))
    (is (= "1" (h.run-app app-path {"expression" "3 / 2"})))
    (is (= "1.5" (h.run-app app-path {"expression" "scale=1; 3 / 2"})))))

(clojure.test/run-tests 'test.nano-app)
