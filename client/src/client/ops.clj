(ns client.ops
  (:require [cheshire.core :as json]
            [clojure.pprint :as pprint]
            [clj-http.client :as client]
            [clojure.string :as string]
            ))

(defn- post-thing [endpoint body]
  (-> (str "http://localhost:3000/" endpoint)
      (client/post { :content-type :json
                    :body body})))

(defn- get-thing [endpoint]
  (-> (str "http://localhost:3000/" endpoint)
      (client/get)
      (:body)
      (json/parse-string)))

(defn show-transaction []
  (let [transacoes (get (get-thing "transacoes") "transacoes")]
    (if (seq transacoes)
      (do
        (pprint/print-table (get (get-thing "transacoes") "transacoes"))
        (newline)
        )
      (println "Nenhuma transação registrada"))))

(defn create-transaction []
  (println "valor e tipo")
  (let [input (string/split (read-line) #" ")]
    (println input)
    (println (json/generate-string
               {:valor (get input 0)
                :tipo (get input 1) }))
    (if (not (< (count input) 2))
      (post-thing "transacoes" (json/generate-string
                                 {:valor (Integer/parseInt(get input 0))
                                  :tipo (get input 1) }))
      (recur)))
  )

(defn show-blockchain []
  (dorun (map #(println (format
                   "\nBloco: %s\nNonce: %s\nTransacoes: %s\nPrevious hash: %s\nHash: %s\n"
                   (get % "index")
                   (get % "nonce")
                   (get % "transacoes")
                   (get % "previous-hash")
                   (get % "hash")
                   )) (get-thing "blockchain"))))
