(ns firma-analysis.ports.http-in.core
  (:require [firma-analysis.configs :as configs]
            [firma-analysis.ports.http-in.routes.core :as routes]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(def service {:env (keyword configs/env)
              ::http/routes routes/specs
              ::http/resource-path "/public"

              ::http/type :jetty
              ::http/port 8080
              ::http/container-options {:h2c? true
                                        :h2? false
                                        :ssl? false}})

(defn start
  []
  (println "server starting with env=" configs/env)
  (println "SQL_DB_NAME=" (System/getenv "SQL_DB_NAME"))
  (let [is-prod (= :prod (keyword (or configs/env "dev")))
        _ (println "is-prod" is-prod)
        built-service (if is-prod (assoc service
                                    ::http/host "0.0.0.0")
                                  service)]
    (http/start (http/create-server built-service))))

(defn start-dev
  []
  (-> service
      (merge {:env :dev
              ::http/join? false
              ::http/routes #(route/expand-routes (deref #'routes/specs))
              ::http/allowed-origins {:creds true :allowed-origins (constantly true)}
              ::http/secure-headers {:content-security-policy-settings {:object-src "'none'"}}})
      http/default-interceptors
      http/dev-interceptors
      http/create-server
      http/start))
