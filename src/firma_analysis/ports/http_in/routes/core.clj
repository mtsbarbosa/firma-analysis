(ns firma-analysis.ports.http-in.routes.core
  (:require [firma-analysis.ports.http-in.routes.commons :refer [json-header]]
            [firma-analysis.ports.http-in.routes.error-handler :refer [service-error-handler]]
            [firma-analysis.ports.http-in.routes.ingestion :as r.ingestion]
            [firma-analysis.ports.http-in.routes.interceptors :as i]
            [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]))

(def json-public-interceptors [service-error-handler
                               (body-params/body-params)
                               (i/json-out)
                               http/html-body])

(def json-interceptors [service-error-handler
                        (body-params/body-params)
                        i/authz-user
                        (i/json-out)
                        http/html-body])

(def json-root-interceptors [service-error-handler
                             (body-params/body-params)
                             i/authz-admin
                             (i/json-out)
                             http/html-body])

(defn healthy
  [_]
  {:status 200 :headers json-header :body {}})

(def specs #{["/health-check" :get (conj json-interceptors `healthy)]
             ["/ingest" :post (conj json-interceptors `r.ingestion/ingest-data)]})
