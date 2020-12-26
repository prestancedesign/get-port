(ns prestancedesign.get-port
  (:import (java.net ServerSocket BindException)))

(defn get-available-port [& [port]]
  (let [socket (ServerSocket. (or port 0))]
    (.close socket)
    (.getLocalPort socket)))

(defn get-port-in-range [range]
  (loop [current-port (first range)]
    (let [result
          (try
            (get-available-port current-port)
            (catch Exception e (instance? BindException (.getCause e))))]
      (or result (recur (inc current-port))))))

(defn get-port-in-list [list]
  (loop [current-port list]
    (let [result
          (try
            (get-available-port (first current-port))
            (catch Exception e (instance? BindException (.getCause e))))]
      (or result (recur (next current-port))))))

(comment

  (get-port-in-range [3000 3005])

  (get-port-in-list [3000 3004 3010])

  (get-available-port 3001)
  (get-available-port)

  ;; Bind a range of port for testing
  (for [port (range 3000 3005)]
    (try
      (ServerSocket. port)
      (catch BindException e (str "caught exception: " (.getMessage e))))))
