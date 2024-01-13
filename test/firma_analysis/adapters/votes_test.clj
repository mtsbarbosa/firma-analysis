(ns firma-analysis.adapters.votes-test
  (:require [clojure.test :refer :all]
            [firma-analysis.adapters.votes :as a.votes]))

(def members-by-id
  {"4001" {:members/id 4001, :members/username nil, :members/first_name "Carlos", :members/last_name "Kamarada", :members/city "sbc"},
   "5001" {:members/id 5001, :members/username "julio81", :members/first_name "Julio", :members/last_name nil, :members/city "maua"}})

(def my-votes-single-poll-one-voter
  {:99999991 {:4001 {:user {:id 4001, :is-bot false, :first-name "Carlos", :last-name "Kamarada"}, :options [0]}}})

(def my-votes-single-poll-two-voters
  {:99999991 {:4001 {:user {:id 4001, :is-bot false, :first-name "Carlos", :last-name "Kamarada"}, :options [0]}
              :5001 {:user {:id 5001, :is-bot false, :first-name "Julio", :username "julio81"}, :options [1]}}})

(def my-votes-two-polls-single-voter
  {:99999991 {:4001 {:user {:id 4001, :is-bot false, :first-name "Carlos", :last-name "Kamarada"}, :options [0]}}
   :88888881 {:4001 {:user {:id 4001, :is-bot false, :first-name "Carlos", :last-name "Kamarada"}, :options [0]}}})

(def my-votes-two-polls-two-voters
  {:99999991 {:4001 {:user {:id 4001, :is-bot false, :first-name "Carlos", :last-name "Kamarada"}, :options [0]}
              :5001 {:user {:id 5001, :is-bot false, :first-name "Julio", :username "julio81"}, :options [1]}}
   :88888881 {:4001 {:user {:id 4001, :is-bot false, :first-name "Carlos", :last-name "Kamarada"}, :options [0]}
              :5001 {:user {:id 5001, :is-bot false, :first-name "Julio", :username "julio81"}, :options [1]}}})

(deftest extract-votes-test
  (testing "one poll single voter"
    (is (= [["99999991"
             4001
             "sbc"
             [0]]]
           (a.votes/extract-votes my-votes-single-poll-one-voter members-by-id))))
  (testing "single poll two voters"
    (is (= '(["99999991"
              4001
              "sbc"
              [0]]
             ["99999991"
              5001
              "maua"
              [1]])
           (a.votes/extract-votes my-votes-single-poll-two-voters members-by-id))))
  (testing "two polls single voter"
    (is (= '(["99999991"
              4001
              "sbc"
              [0]]
             ["88888881"
              4001
              "sbc"
              [0]])
           (a.votes/extract-votes my-votes-two-polls-single-voter members-by-id))))
  (testing "two polls two voters"
    (is (= '(["99999991"
              4001
              "sbc"
              [0]]
             ["99999991"
              5001
              "maua"
              [1]]
             ["88888881"
              4001
              "sbc"
              [0]]
             ["88888881"
              5001
              "maua"
              [1]])
           (a.votes/extract-votes my-votes-two-polls-two-voters members-by-id)))))
