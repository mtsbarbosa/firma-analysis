(ns firma-analysis.controllers.ingestion
  (:require [firma-analysis.controllers.events :as c.events]
            [firma-analysis.controllers.availabilities :as c.availabilities]
            [firma-analysis.controllers.members :as c.members]
            [firma-analysis.controllers.votes :as c.votes]
            [firma-analysis.ports.http-out.json-storage :as json-storage]
            [firma-analysis.ports.sql.repositories.members :as r.members]))

(defn ingest-data
  [_]
  (let [stored-events   (json-storage/fetch-events!)
        stored-part     (json-storage/fetch-participation!)
        _ (c.members/ingest-members (get stored-part :participation/members []))
        _ (c.events/ingest-events (get stored-events :events/events []))
        _ (c.availabilities/ingest-availabilities (get stored-events :events/availabilitites []))
        grouped-members (r.members/find-grouped-by-id!)
        _ (c.votes/ingest-votes (get stored-part :participation/votes {}) grouped-members)]))
