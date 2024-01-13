(ns firma-analysis.adapters.members
  (:require [clojure.string :as cstring]))

(defn storage->
  [member]
  (let [[username first-name last-name id city] (cstring/split member #":")]
    {:members/id         id
     :members/first-name first-name
     :members/last-name  (if (empty? last-name) nil last-name)
     :members/username   (if (empty? username)  nil username)
     :members/city       city}))
