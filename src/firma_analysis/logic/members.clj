(ns firma-analysis.logic.members)

(defn missing-members [stored-data db-data]
  (let [db-ids (set (keys db-data))
        filter-fn (fn [data] (not (contains? db-ids (:members/id data))))]
    (filter filter-fn stored-data)))
