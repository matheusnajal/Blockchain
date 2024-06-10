(defproject financeiro "0.1.0-SNAPSHOT"
  :uberjar-name "financeiro.jar"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :main ^:skip-aot client.core
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [cheshire "5.13.0"]
                 [clj-http "3.13.0"]])
