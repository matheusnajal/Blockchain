(ns financeiro.blockchain
  (:require [blockchain.hash :as hash]))

(def blockchain
  (atom [{:index 0
          :timestamp (System/currentTimeMillis)
          :transacoes []
          :previous-hash "0"
          :hash "genesis-hash"
          :nonce 0}]))

(defn ultimo-bloco []
  (last @blockchain))

(defn criar-bloco [transacao previous-hash]
  (let [ultimo (ultimo-bloco)
        index (inc (:index ultimo))
        timestamp (System/currentTimeMillis)
        transacoes (conj (:transacoes ultimo) transacao)
        [hash nonce] (hash/proof-of-work previous-hash)]
    {:index index
     :timestamp timestamp
     :transacoes transacoes
     :previous-hash previous-hash
     :hash hash
     :nonce nonce}))

(defn adicionar-bloco [transacao]
  (let [ultimo (ultimo-bloco)
        previous-hash (if (zero? (:index ultimo))
                        (apply str (repeat 64 "0"))
                        (:hash ultimo))
        novo-bloco (criar-bloco transacao previous-hash)]
    (swap! blockchain conj novo-bloco)
    (println "Novo bloco adicionado:")
    (println "Bloco:" (:index novo-bloco))
    (println "Nonce:" (:nonce novo-bloco))
    (println "Dados:" (:transacoes novo-bloco))
    (println "Previous Hash:" (:previous-hash novo-bloco))
    (println "Hash do Bloco:" (:hash novo-bloco))
    novo-bloco))