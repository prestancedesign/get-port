(ns PrestanceDesign.get-port)

(defn get-free-port []
  (let [socket (java.net.ServerSocket. 0)]
    (.close socket)
    (.getLocalPort socket)))

(comment
  (get-free-port))
