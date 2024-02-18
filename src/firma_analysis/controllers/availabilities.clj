(ns firma-analysis.controllers.availabilities
  (:require [firma-analysis.logic.availabilities :as l.availabilities]
            [firma-analysis.ports.sql.repositories.availabilities :as r.availabilities]))

(defn ingest-availabilities
  [stored]
  (let [missing (l.availabilities/missing stored
                                   (r.availabilities/find-grouped-by-poll-id!))]
    (r.availabilities/upsert! missing)))
