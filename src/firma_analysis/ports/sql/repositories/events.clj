(ns firma-analysis.ports.sql.repositories.events
  (:require [firma-analysis.ports.sql.core :as sql.core]
            [firma-analysis.ports.sql.repositories.entities :as r.entities]
            [next.jdbc.sql :as sql]))

(defn find-grouped-by-poll-id!
  []
  (->> "events"
       r.entities/find-all
       (group-by :events/poll_id)
       (#(update-vals % first))))

(defn upsert!
  [events]
  (sql/insert-multi! sql.core/datasource :events
                     [:created_at :title :poll :options_length :poll_id
                      :type :city :name :description :scheduled_to :id]
                     (map #(vals %) events)))
