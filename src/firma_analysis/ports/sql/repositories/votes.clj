(ns firma-analysis.ports.sql.repositories.votes
  (:require [firma-analysis.adapters.votes :as a.votes]
            [firma-analysis.ports.sql.core :as sql.core]
            [next.jdbc.sql :as sql]))
(defn upsert!
  [votes members-by-id]
  (dorun (map #(sql/delete! sql.core/datasource :votes {:poll_id (-> % name)}) (keys votes)))
  (sql/insert-multi! sql.core/datasource :votes
                     [:poll_id :member_id
                      :member_city :options]
                     (a.votes/extract-votes votes members-by-id)))
