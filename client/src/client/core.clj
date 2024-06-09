(ns client.core
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [clj-http.client :as client]))

(defn- get-google []
  (client/get "http://www.google.com"))

(defn- mostrar-transacao []
  )

(defn- mostrar-blockchain []
  )

(defn- fazer-transacao []
  )

(defn- input-loop []
  (printf "1-%s\n2-%s\n3-%s\n4-%s\n>>"
          "Mostrar transação"
          "Mostrar blockchain"
          "Fazer transação"
          "Sair do programa")
  (case (str (read))
    "1" (mostrar-transacao)
    "2" (mostrar-blockchain)
    "3" (fazer-transacao)
    "4" "TCHAU"
    (recur))
  (recur))


(defn -main []
  (println "BEM-VINDO")
  (println (input-loop)))
