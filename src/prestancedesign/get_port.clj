(ns prestancedesign.get-port
  (:import (java.net ServerSocket BindException)))

(defn- get-available-port [& [port]]
  (with-open [socket (ServerSocket. (or port 0))]
    (.getLocalPort socket)))

(defn make-range [from to]
  (range from (inc to)))

(defn get-port [& [options]]
  (if-let [ports (:port options)]
    (loop [port ports]
      (let [result
            (try
              (get-available-port (if (number? port) port (first port)))
              (catch Exception e (instance? BindException (.getCause e))))]
        (or result (recur (next port)))))
    (get-available-port options)))

(comment

  (get-port)
  (get-port {:port 3000})
  (get-port {:port [3000 3004 3010]})
  (get-port {:port (make-range 3000 3005)})

  ;; Bind a range of port for testing
  (for [port (range 3000 3005)]
    (try
      (ServerSocket. port)
      (catch BindException e (str "caught exception: " (.getMessage e))))))
