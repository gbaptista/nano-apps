(let [{:strs [from to]} parameters]
  (+ from (rand-int (+ 1 (- to from)))))
