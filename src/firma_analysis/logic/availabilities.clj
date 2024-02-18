(ns firma-analysis.logic.availabilities)

(defn missing [stored-data db-data]
  (let [db-ids (set (keys db-data))
        filter-fn (fn [data] (not (contains? db-ids (:availabilities/poll-id data))))]
    (filter filter-fn stored-data)))
