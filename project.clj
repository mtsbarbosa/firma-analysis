(defproject org.clojars.mtsbarbosa/firma-analysis "0.0.7"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "MIT License"
                      :url "https://opensource.org/licenses/MIT"}
            :dependencies [[org.clojure/clojure "1.11.1"]
                           [io.pedestal/pedestal.service "0.6.3"]
                           [io.pedestal/pedestal.jetty "0.6.3"]
                           [org.clojars.majorcluster/pedestal-api-helper "0.10.1"]
                           [org.clojars.majorcluster/clj-data-adapter "0.8.6"]
                           [org.clojure/data.json "2.4.0"]
                           [com.github.seancorfield/next.jdbc "1.3.894"]
                           [org.postgresql/postgresql "42.7.1"]
                           [com.outpace/config "0.13.5"]
                           [ch.qos.logback/logback-classic "1.4.14" :exclusions [org.slf4j/slf4j-api]]
                           [org.slf4j/jul-to-slf4j "2.0.9"]
                           [org.slf4j/jcl-over-slf4j "2.0.9"]
                           [org.slf4j/log4j-over-slf4j "2.0.9"]
                           [http-kit "2.8.0-beta3"]]
            :min-lein-version "2.0.0"
            :aliases {"config" ["run" "-m" "outpace.config.generate"]
                      "migrate" ["run" "-m" "firma-analysis.ports.sql.core/migrate"]
                      "diagnostics"          ["clojure-lsp" "diagnostics"]
                      "format"               ["clojure-lsp" "format" "--dry"]
                      "format-fix"           ["clojure-lsp" "format"]
                      "clean-ns"             ["clojure-lsp" "clean-ns" "--dry"]
                      "clean-ns-fix"         ["clojure-lsp" "clean-ns"]
                      "lint"                 ["do" ["diagnostics"]  ["format"] ["clean-ns"]]
                      "lint-fix"             ["do" ["format-fix"] ["clean-ns-fix"]]}
            :resource-paths ["config", "resources"]
            :jvm-opts ["-Dresource.config.edn=app-config.edn"
                       "-Duser.timezone=America/Sao_Paulo"]
            :profiles {:dev {:aliases {"run-dev"              ["trampoline" "run" "-m" "firma-analysis.server/run-dev"]
                                       "run-dev-w-migration"  ["trampoline" "run" "-m" "firma-analysis.server/run-dev-w-migration"]}
                             :dependencies [[io.pedestal/pedestal.service-tools "0.6.3"]]
                             :jvm-opts ["-Dresource.config.edn=dev-config.edn"]}
                       :test {:dependencies [[io.pedestal/pedestal.service-tools "0.6.3"]
                                             [com.h2database/h2 "2.2.224"]
                                             [nubank/matcher-combinators "3.8.8"]]
                              :jvm-opts ["-Dresource.config.edn=test-config.edn"]}
                       :uberjar {:aot :all}}
            :main ^{:skip-aot true} firma-analysis.server)
