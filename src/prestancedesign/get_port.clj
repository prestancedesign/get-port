(ns prestancedesign.get-port
  (:import (java.net ServerSocket BindException)))

(def range-port {:from 3000 :to 3005})

(defn get-available-port [& [port]]
  (let [socket (ServerSocket. (or port 0))]
    (.close socket)
    (.getLocalPort socket)))

(defn get-port-in-range [range]
  (loop [current-port (:from range)]
    (when (<= current-port (:to range))
      (let [result
            (try
              (get-available-port current-port)
              (catch Exception e (instance? BindException (.getCause e))))]
        (or result (recur (inc current-port)))))))

(comment

  (get-port-in-range range-port)

  (get-available-port 3001)
  (get-available-port)

  ;; Bind a range of port for testing
  (for [port (range 3000 3005)]
    (try
      (ServerSocket. port)
      (catch BindException e (str "caught exception: " (.getMessage e))))))
