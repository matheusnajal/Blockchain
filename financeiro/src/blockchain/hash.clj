(ns blockchain.hash)

(import '(java.security MessageDigest))

(defn- sha256 [input]
  (let [digest (MessageDigest/getInstance "SHA-256")]
    (.digest digest (.getBytes input))))

(defn- hex-string [bytes]
  (apply str (map #(format "%02x" %) bytes)))

(defn- calculate-sha256 [input]
  (hex-string (sha256 input)))

(defn proof-of-work
  ([last-hash]
   (proof-of-work last-hash 0))
  ([last-hash nonce]
   (let [current-hash (calculate-sha256 (str last-hash nonce))]
     (if (= (subs current-hash 0 4) "0000")
       (do
         (println current-hash nonce (subs current-hash 0 4))
         [current-hash nonce])
       (do
         (println current-hash nonce (subs current-hash 0 4))
         (recur last-hash (inc nonce)))))))