(ns firma-analysis.controllers.members
  (:require [firma-analysis.logic.members :as l.members]
            [firma-analysis.ports.sql.repositories.members :as r.members]))

(defn ingest-members
  [stored-members]
  (let [missing-members (l.members/missing-members stored-members
                                                   (r.members/find-grouped-by-id!))]
    (r.members/upsert! missing-members)))
