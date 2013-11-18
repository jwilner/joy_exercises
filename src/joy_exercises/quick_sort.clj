(ns joy-exercises.quick-sort)

;;Listing 6.3
(defn nom [n] (take n (repeatedly #(rand-int n))))

(defn sort-parts
  "Lazy, tail-recursive, incremental quicksort. Works against and creates
  partitions based on the pivot, defined as 'work'."
  [work]
  (lazy-seq
            (loop [[part & parts] work]
              (if-let [[pivot & xs] (seq part)]
                (let [smaller? #(< % pivot)]
                  (recur (list*
                                (filter smaller? xs)
                                pivot
                                (remove smaller? xs)
                                parts)))
                (when-let [[x & parts] parts]
                  (cons x (sort-parts parts)))))))

(defn qsort [xs]
  (sort-parts (list xs)))

(qsort (nom 20))
(first (qsort (nom 100)))
