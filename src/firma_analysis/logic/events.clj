(ns firma-analysis.logic.events)

(defn missing-events [stored-data db-data]
  (let [db-ids (set (keys db-data))
        filter-fn (fn [data] (not (contains? db-ids (:events/poll-id data))))]
    (filter filter-fn stored-data)))
