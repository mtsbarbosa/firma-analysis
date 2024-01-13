(ns firma-analysis.logic.members-test
  (:require [clojure.test :refer :all]
            [firma-analysis.logic.members :as l.members]))

(def stored-data
  [{:members/id         "033fd7ff-094c-4e2e-859e-cba2337cfb4a",
    :members/first-name "Ze",
    :members/last-name  nil,
    :members/username   "ze101"
    :members/city       "diadema"}
   {:members/id         "033fd7ff-094c-4e2e-859e-cba2337cfb2a",
    :members/first-name "Maria",
    :members/last-name  "Santos",
    :members/username   "mariasantos82"
    :members/city       "scs"}])

(def db-data
  {"033fd7ff-094c-4e2e-859e-cba2337cfb4a"
   [{:members/id "033fd7ff-094c-4e2e-859e-cba2337cfb4a",
     :members/username "ze101",
     :members/first-name "Ze",
     :members/last-name nil,
     :members/city      "diadema"}]})

(deftest missing-members-test
  (testing "when everyone is missing"
    (is (= stored-data (l.members/missing-members stored-data {}))))
  (testing "when single member is missing"
    (is (= [(get stored-data 1)] (l.members/missing-members stored-data db-data))))
  (testing "when no member is missing"
    (is (= [] (l.members/missing-members stored-data (assoc db-data
                                                       "033fd7ff-094c-4e2e-859e-cba2337cfb2a" {}))))))
