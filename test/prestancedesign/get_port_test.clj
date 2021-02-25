(ns prestancedesign.get-port-test
  (:require [clojure.test :refer [deftest testing is use-fixtures]]
            [prestancedesign.get-port :refer [get-port make-range]])
  (:import (java.net ServerSocket BindException)))

(defn port-binding-setup [f]
  (with-open [_ (ServerSocket. 3000)]
    (f)))

(use-fixtures :each port-binding-setup)

(deftest make-range-test
  (testing "Generating port range"
    (is (= [3000 3001 3002 3003 3004 3005] (make-range 3000 3005)))))

(deftest get-port-test
  (testing "Test binding from a valid random port"
    (let [port (get-port)]
      (is (and (< port 65536) (> port 0)))))
  (testing "Test binding from a specified port"
    (is (= 3001 (get-port 3001))))
  (testing "Test binding from a list of prefered ports"
    (is (= 3004 (get-port {:port [3000 3004 3010]}))))
  (testing "Test binding from first port available inside a range"
    (is (= 3001 (get-port {:port (make-range 3000 3005)}))))
  (testing "Test binding an already used port"
    (is (thrown? BindException (get-port 3000)))))
