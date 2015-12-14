(ns floyd-warshall.core-test
  (:require [clojure.test :refer :all]
            [floyd-warshall.core :refer :all]))


(deftest zero-packing
  (testing "Packing no elements."
    (is (empty? (f-pack)))))

(deftest basic-packing
  (testing "Packing two elements."
    (is (= ((f-pack 0 1) 0)
           0))
    (is (= ((f-pack 0 1) 1)
           1))))

(deftest basic-function-product
  (testing "Basic product of two functions."
    (let [p (f-prod into conj)]
      (is (=
           (p (f-pack #{} []) [:0 :1])
           (f-pack #{:0 :1} [[:0 :1]]))))))

(deftest something
  (testing ""
    (is (=
         (set-up [[:0 :1]
                  [:1 :3]
                  [:1 :2]
                  [:2 :4]
                  [:2 :5]])
         {:vector [:0 :4 :1 :2 :5 :3]
          :map {:0 0 :1 1 :2 3 :3 2} }))))
