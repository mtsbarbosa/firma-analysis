(ns firma-analysis.controllers.votes
  (:require [firma-analysis.ports.sql.repositories.votes :as r.votes]))

(defn ingest-votes
  [stored-votes members-by-id]
  (r.votes/upsert! stored-votes members-by-id))
