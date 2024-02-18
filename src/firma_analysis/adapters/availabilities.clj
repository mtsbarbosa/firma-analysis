(ns firma-analysis.adapters.availabilities
  (:require [clojure.string :as cstring])
  (:import [java.sql Timestamp]))

(defn date-time->timestamp
  [date-time]
  (Timestamp/valueOf (str date-time ":00")))

(defn storage->
  [events]
  (map (fn [event]
         {:availabilities/id (-> event :id parse-uuid)
          :availabilities/title (:name event)
          :availabilities/name (:name event)
          :availabilities/description (:name event)
          :availabilities/dates (map date-time->timestamp (:dates event))
          :availabilities/created-at (date-time->timestamp (:date-time event))
          :availabilities/poll-id (:poll-id event)
          :availabilities/options-length (count (:dates event))})
       events))
