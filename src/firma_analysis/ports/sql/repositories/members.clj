(ns firma-analysis.ports.sql.repositories.members
  (:require [firma-analysis.ports.sql.core :as sql.core]
            [firma-analysis.ports.sql.repositories.entities :as r.entities]
            [next.jdbc.sql :as sql]))

(defn find-grouped-by-id!
  []
  (->> "members"
       r.entities/find-all
       (group-by :members/id)
       (#(update-vals % first))))

(defn upsert!
  [members]
  (sql/insert-multi! sql.core/datasource :members
                     [:id :first_name :last_name :username :city]
                     (map #(vals %) members)))
