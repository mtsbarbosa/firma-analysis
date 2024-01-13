(ns firma-analysis.adapters.events
  (:require [clojure.string :as cstring])
  (:import [java.sql Timestamp]))

(defn location->city
  [location]
  (get (cstring/split location #"\s-\s") 0))

(defn date-time->timestamp
  [date-time]
  (Timestamp/valueOf (str date-time ":00")))

(defn storage->
  [events]
  (map (fn [event]
         {:events/id (-> event :id parse-uuid)
          :events/title (:event-name event)
          :events/city (-> event :location location->city)
          :events/type (:type event)
          :events/name (:event-name event)
          :events/description (:event-name event)
          :events/scheduled-to (date-time->timestamp (:date-time event))
          :events/created-at (date-time->timestamp (:created-at event))
          :events/poll (and (contains? event :poll-id) (not (nil? (:poll-id event))))
          :events/poll-id (:poll-id event)
          :events/options-length (:total-options event)})
       events))
