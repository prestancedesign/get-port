(ns prestancedesign.get-port-test
  (:require [clojure.test :refer [deftest testing is use-fixtures]]
            [prestancedesign.get-port :refer [get-port make-range]])
  (:import (java.net ServerSocket)))

(defn port-binding-setup [f]
  (with-open [_ (ServerSocket. 3000)]
    (f)))

(use-fixtures :each port-binding-setup)

(deftest make-range-test
  (testing "Generating port range"
    (is (= [3000 3001 3002 3003 3004 3005] (make-range 3000 3005))))
  (testing "Throws on port below 1024"
    (is (thrown? AssertionError (make-range 80 3000))))
  (testing "Throws on port above 65535"
    (is (thrown? AssertionError (make-range 3000 70000))))
  (testing "Throws when from is greater than to"
    (is (thrown? AssertionError (make-range 3005 3000)))))


(deftest get-port-test
  (testing "Test binding from a valid random port"
    (let [port (get-port)]
      (is (and (> port 1024) (< port 65536)))))
  (testing "Test binding from a specified port"
    (is (= 3001 (get-port {:port 3001}))))
  (testing "Test binding from a list of preferred ports"
    (is (= 3004 (get-port {:port [3000 3004 3010]}))))
  (testing "Test binding from first port available inside a range"
    (is (= 3001 (get-port {:port (make-range 3000 3005)}))))
  (testing "Returns nil when preferred port is bound"
    (is (nil? (get-port {:port 3000}))))
  (testing "Returns nil when all preferred ports are bound"
    (with-open [_ (ServerSocket. 3001)]
      (is (nil? (get-port {:port [3000 3001]})))))
  (testing "Returns random port with :fallback true when preferred port is bound"
    (let [port (get-port {:port 3000 :fallback true})]
      (is (and (> port 1024) (< port 65536) (not= port 3000)))))
  (testing "Returns random port with :fallback true when all preferred ports are bound"
    (with-open [_ (ServerSocket. 3001)]
      (let [port (get-port {:port [3000 3001] :fallback true})]
        (is (and (> port 1024) (< port 65536) (not (#{3000 3001} port))))))))
