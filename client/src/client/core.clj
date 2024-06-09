(ns client.core
  (:require [client.ops :refer :all]))

(defn- input-loop []
  (println (str "1-Mostrar transação\n"
                "2-Mostrar blockchain\n"
                "3-Fazer transação\n"
                "4-Sair do programa"))
  (case (str (read-line))
    "1" (show-transaction)
    "2" (show-blockchain)
    "3" (create-transaction)
    "4" (System/exit 0)
    :nada)
  (recur))

(defn -main []
      (println "BEM-VINDO")
      (input-loop))
