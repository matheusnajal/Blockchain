(ns financeiro.saldo-aceitacao-test
  (:require [midje.sweet :refer :all]
            [financeiro.handler :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]]
            [clj-http.client :as http]))

(def servidor (atom nil))
(defn iniciar-servidor [porta]
  (swap! servidor
         (fn [_] (run-jetty app {:port porta :join? false}))))
(defn parar-servidor []
  (.stop @servidor))

(facts "Saldo inicial é 0"
       (let [response (app (mock/request :get "/saldo"))]
         (fact "o status da resposta é 200"
               (:status response) => 200)
         (fact "o texto do corpo é '0'"
               (:body response) => "0")))