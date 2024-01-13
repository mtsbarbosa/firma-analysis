(ns firma-analysis.adapters.votes)

(defn extract-votes
  [votes-map members-by-id]
  (->> votes-map
       (reduce (fn [acc-votes [poll-id votes]]
                 (reduce (fn [votes-by-poll-id [_ vote]]
                           (let [user-id (-> vote :user :id str)
                                 member (get members-by-id user-id)]
                             (conj votes-by-poll-id [(-> poll-id name (subs 0))
                                                     (:members/id member)
                                                     (:members/city member)
                                                     (-> vote :options)]))) acc-votes votes)) [])))
