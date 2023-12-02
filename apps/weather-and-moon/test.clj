(ns test.nano-app
  (:require [clojure.test :refer :all]))

(load-file "test/helpers.clj")

(def app-path "apps/weather-and-moon")

(deftest test-nano-app
  (testing "Specification"
    (let [specification (h.load-specification app-path)]
      (is (= "weather-and-moon" (:name specification)))
      (is (= true (contains? specification :description)))

      (is (= nil (get-in specification [:parameters :required])))
      (is (= {"location" ""} (h.sample-input specification)))))

  (testing "Nano App"
    (is (= [:current_condition :nearest_area :request :weather]
           (keys (h.run-app app-path))))

    (is (= {:error "Error performing request.", :status-code 404, :details ""}
           (h.run-app app-path {"location" "UNKNOWN-LOCATION"})))

    (is (= [{:value "Sao Paulo"}]
           (:areaName (first (:nearest_area (h.run-app app-path {"location" "São Paulo"}))))))

    (is (= [{:value "Santos"}]
           (:areaName (first (:nearest_area (h.run-app app-path {"location" "Santos"}))))))))

(clojure.test/run-tests 'test.nano-app)
