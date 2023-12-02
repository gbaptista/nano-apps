(ns test.nano-app
  (:require [clojure.test :refer :all]))

(load-file "test/helpers.clj")

(def app-path "apps/sh")

(deftest test-nano-app
  (testing "Specification"
    (let [specification (h.load-specification app-path)]
      (is (= "sh" (:name specification)))
      (is (= true (contains? specification :description)))
      (is (= nil (get-in specification [:parameters :required])))
      (is (= {"command" [""] "piped_commands" [[""]]} (h.sample-input specification)))))

  (testing "Nano App"
    (is (= "Error: You need to provide either a command or piped_commands, not both."
           (h.run-app app-path {"command" ["pwd"] "piped_commands" [["pwd"]]})))

    (is (= "nano-bot\n" (h.run-app app-path {"command" ["echo" "nano-bot"]})))
    (is (= "HELLO WORLD\n"
           (h.run-app app-path
                      {"piped_commands" [["echo" "hello" "world"] ["tr" "a-z" "A-Z"]]})))))

(clojure.test/run-tests 'test.nano-app)
