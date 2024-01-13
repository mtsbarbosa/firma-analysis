(ns firma-analysis.ports.http-in.routes.ingestion
  (:require [firma-analysis.controllers.ingestion :as c.ingestion]))

(def json-header
  {"Content-Type" "application/json"})

(defn ingest-data
  [_]
  (let [_ (c.ingestion/ingest-data "")]
    {:status 200 :headers json-header :body {}}))
