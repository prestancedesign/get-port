(ns prestancedesign.get-port-test
  (:require [clojure.test :refer :all]
            [prestancedesign.get-port :refer :all])
  (:import (java.net ServerSocket BindException)))

(deftest get-available-port-test
  (testing "Test binding port 3000"
    (is (= 3000 (get-available-port 3000)))))

(deftest get-port-in-range-test
  (testing "Test bind port from a range"
    (is (= 3000 (get-port-in-range {:from 3000 :to 3005})))))
