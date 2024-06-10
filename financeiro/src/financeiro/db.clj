(ns financeiro.db)

(def registros
  (atom []))

(defn saldo []
  (reduce (fn [acumulado transacao]
            (let [valor (:valor transacao)]
              (if (= "despesa" (:tipo transacao))
                (- acumulado valor)
                (+ acumulado valor))))
          0
          @registros))

(defn limpar []
  (reset! registros []))

(defn transacoes []
  @registros)

(defn registrar [transacao]
  (let [colecao-atualizada (swap! registros conj transacao)]
    (merge transacao {:id (count colecao-atualizada)})))

(defn transacoes-do-tipo [tipo]
  (filter #(= tipo (:tipo %)) (transacoes)))

(defn transacoes-com-filtro [filtros]
  (let [rotulos (->> (:rotulos filtros)
                     (conj [])
                     (flatten)
                     (set))]
    (filter #(some rotulos (:rotulos %)) (transacoes))))