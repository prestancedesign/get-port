(ns PrestanceDesign.get-port)

(defn get-available-port []
  (let [socket (java.net.ServerSocket. 0)]
    (.close socket)
    (.getLocalPort socket)))

(comment
  (get-available-port))

