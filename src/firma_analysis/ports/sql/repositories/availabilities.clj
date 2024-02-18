(ns firma-analysis.ports.sql.repositories.availabilities
  (:require [firma-analysis.ports.sql.core :as sql.core]
            [firma-analysis.ports.sql.repositories.entities :as r.entities]
            [next.jdbc.sql :as sql]))

(defn find-grouped-by-poll-id!
  []
  (->> "availabilities"
       r.entities/find-all
       (group-by :availabilities/poll_id)
       (#(update-vals % first))))

(defn upsert!
  [availabilities]
  (sql/insert-multi! sql.core/datasource :availabilities
                     [:id :title :name :description :dates
                      :created_at :poll_id :options_length]
                     (map #(vals %) availabilities)))
