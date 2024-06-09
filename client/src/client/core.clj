(ns client.core
  (:require [clj-http.client :as client]
            [client.hash :refer [proof-of-work]]
            [cheshire.core :as json]))

(defn- post-thing [endpoint body]
  (-> (str "http://localhost:3000/" endpoint)
      (client/post)
      (:body)
      (json/parse-string)))

(defn- get-thing [endpoint]
  (-> (str "http://localhost:3000/" endpoint)
      (client/get)
      (:body)
      (json/parse-string)))


(defn- input-loop []
  (println (str "1-Mostrar transação\n"
                "2-Mostrar blockchain\n"
                "3-Fazer transação\n"
                "4-Sair do programa\n"
                ">>> "))
  (case (str (read-line))
    "1" (println(get-thing "transacoes"))
    "2" (get-thing "blockchain")
    "3" (post-thing "s")
    "4" (System/exit 0)
    :nada)
  (recur))


(defn -main []
  (println "BEM-VINDO")
  (input-loop))