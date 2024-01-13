(ns firma-analysis.controllers.events
  (:require [firma-analysis.logic.events :as l.events]
            [firma-analysis.ports.sql.repositories.events :as r.events]))

(defn ingest-events
  [stored-events]
  (let [missing-events (l.events/missing-events stored-events
                                                (r.events/find-grouped-by-poll-id!))]
    (r.events/upsert! missing-events)))
