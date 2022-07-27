(ns prestancedesign.get-port
  (:import (java.net ServerSocket BindException)))

(defn- get-available-port
  "Return a random available TCP port in allowed range (between 1024 and 65535) or a specified one"
  ([] (get-available-port 0))
  ([port]
   (with-open [socket (ServerSocket. port)]
     (.getLocalPort socket))))

(defn make-range
  "Make a range of ports.
  Must be in the range 1024...65535"
  [from to]
  (range from (inc to)))

(defn get-port
  "Get an available TCP port according to the supplied options.

  - A preferred port: (get-port {:port 3000})
  - A vector of preferred ports: (get-port {:port [3000 3004 3010]})
  - Use the `make-range` helper in case you need a port in a certain (inclusive) range: (get-port {:port (make-range 3000 3005)})

  No args return a random available port"
  ([] (get-available-port))
  ([opts]
   (loop [port (:port opts)]
     (let [result
           (try
             (get-available-port (if (number? port) port (first port)))
             (catch Exception e (instance? BindException (.getCause e))))]
       (or result (recur (if (number? port) 0 (next port))))))))

(comment

  (get-port)

  (get-port {:port 80})

  (get-port {:port 3000})

  (get-port {:port [3000 3004 3010]})

  (get-port {:port (make-range 3000 3005)})

  ;; Bind a range of port for testing
  (doseq [port (range 3000 3005)]
    (try
      (ServerSocket. port)
      (catch BindException e (str "caught exception: " (.getMessage e))))))
