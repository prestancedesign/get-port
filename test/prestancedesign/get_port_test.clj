(ns prestancedesign.get-port-test
  (:require [clojure.test :refer [deftest testing is use-fixtures]]
            [prestancedesign.get-port :refer [get-available-port get-port-in-range get-port-in-list]])
  (:import (java.net ServerSocket BindException)))

(defn port-binding-setup [f]
  (let [socket (ServerSocket. 3000)]
    (f)
    (.close socket)))

(use-fixtures :each port-binding-setup)

(deftest get-available-port-test
  (testing "Test binding a valid random port"
    (let [port (get-available-port)]
      (is (and (< port 65536) (> port 0)))))
  (testing "Test binding a specified port"
    (is (= 3001 (get-available-port 3001))))
  (testing "Test binding an already used port"
    (is (thrown? java.net.BindException (get-available-port 3000)))))

(deftest get-port-in-range-test
  (testing "Test binding a port available from a range"
    (is (= 3001 (get-port-in-range [3000 3005])))))

(deftest get-port-in-list-test
  (testing "Test binding a port available from a list of predefined port"
    (is (= 3004 (get-port-in-list [3000 3004 3010])))))
