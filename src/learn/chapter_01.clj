(ns learn.chapter_01)

; 练习 1.1

(println "practice 1.1-1:"
         10                   ; 10
         (+ 5 3 4)            ; 12
         (- 9 1)              ; 8
         (/ 6 2)              ; 3
         (+ (* 2 4) (- 4 6))) ; 6

(def a 3)                     ; 3
(def b (+ a 1))               ; 4

(println "practice 1.1-2:"
         (+ a b (* a b))        ; 19
         (= a b)
         (if (and (> b a) (< b (* a b)))
           b
           a)                   ; 4
         (cond (= a 4) 6
               (= b 4) (+ 6 7 a)
               (:else) 25)      ; 16
         (+ 2 (if (> b a) b a)) ; 6
         (* (cond (> a b) a
                  (< a b) b
                  (:else) -1)
            (+ a 1)))           ; 16

; 练习 1.2

(println "practice 1.2:"
         (/ (+ 5
               4
               (- 2
                  (- 3
                     (+ 6
                        (/ 4 5)))))
            (* 3
               (- 6 2)
               (- 2 7))))
; -37/150

; 练习 1.3

; 方法 1
(def merge-bigger-1
     (fn [a b c]
         (cond (and (> a c) (> b c)) (+ a b)
               (and (> c b) (> a b)) (+ c a)
               :else (+ b c))))
(println "practice 1.3-1:"
         (merge-bigger-1 1 2 3))

(def bigger
     (fn [a b]
         (if (> a b)
             a
             b)))
(def smaller
     (fn [a b]
         (if (> a b)
             b
             a)))

; 方法 2
(def merge-bigger-2
     (fn [a b c]
         (+ (bigger a b)
            (bigger (smaller a b) c))))

(println "practice 1.3-2:"
         (merge-bigger-2 1 2 3))

; 练习 1.4

; 类 lisp 语言中, 函数也是一种变量
; 可以作为参数传递, 从参数中获得的函数也可以直接拿来调用

(def a-plus-abs-b
     (fn [a b]
         ((if (> b 0)
              +
              -) a
                 b)))

(println "practice 1.4:"
         (a-plus-abs-b 1 2)
         (a-plus-abs-b 1 -2))

; 练习 1.5

(defn p [] p)
(println (p))
