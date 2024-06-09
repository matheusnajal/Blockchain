(defproject financeiro "0.1.0-SNAPSHOT"
  :uberjar-name "financeiro.jar"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :main ^:skip-aot client.core
  :dependencies [[compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [clj-http "3.9.1"]])
