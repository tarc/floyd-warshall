(ns floyd-warshall.core)

(defn f-pack
  [& ps]
  
  (into [] ps))



(defn f-prod
  [& fs]

  (fn [col el]
    (->>
     (map #((% 1) (col (% 0)) el) (map-indexed vector fs))
     (apply f-pack))))



(defn update-distance

  [graph v-map i j distance]

  (->> (-> (graph (v-map i))     ; updates from the i-th line of graph
           (assoc (v-map j) distance))  ; its j-th element with
                                        ; distance

       (assoc graph (v-map i)))) ; and then updates graph with this
                                 ; i-th line



(defn update-distance-undirected

  [graph v-map i j distance]

  (-> graph
      (update-distance v-map i j distance)
      (update-distance v-map j i distance)))



(defn set-up
  
  [edges]

  (let [v-set (reduce into #{} edges)
        v-vector (into [] v-set)
        v-map (into {} (map-indexed #(vector %2 %1)
                                    v-vector))
        g-matrix (into [] (repeat
                           (count v-vector)
                           (into [] (repeat
                                     (count v-vector) nil))) )

        g-matrix (reduce #(update-distance-undirected %1 v-map (%2 0) (%2 1) 1) 
                         g-matrix edges)]

    {:vertices v-vector
     :map v-map
     :graph g-matrix}))
