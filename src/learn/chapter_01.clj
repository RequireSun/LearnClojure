(ns learn.chapter_01)

; 练习 1.1

(println "====================")

(println "practice 1.1-1:\n"
         10                   ; 10
         (+ 5 3 4)            ; 12
         (- 9 1)              ; 8
         (/ 6 2)              ; 3
         (+ (* 2 4) (- 4 6))  ; 6
         "\n====================")

(def a 3)                     ; 3
(def b (+ a 1))               ; 4

(println "practice 1.1-2:\n"
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
            (+ a 1))            ; 16
         "\n====================")

; 练习 1.2

(println "practice 1.2:\n"
         (/ (+ 5
               4
               (- 2
                  (- 3
                     (+ 6
                        (/ 4 5)))))
            (* 3
               (- 6 2)
               (- 2 7)))
         "\n====================")
; -37/150

; 练习 1.3

; 方法 1
(def merge-bigger-1
     (fn [a b c]
         (cond (and (> a c) (> b c)) (+ a b)
               (and (> c b) (> a b)) (+ c a)
               :else (+ b c))))
(println "practice 1.3-1:\n"
         (merge-bigger-1 1 2 3)
         "\n====================")

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

(println "practice 1.3-2:\n"
         (merge-bigger-2 1 2 3)
         "\n====================")

; 练习 1.4

; 类 lisp 语言中, 函数也是一种变量
; 可以作为参数传递, 从参数中获得的函数也可以直接拿来调用

(def a-plus-abs-b
     (fn [a b]
         ((if (> b 0)
              +
              -) a
                 b)))

(println "practice 1.4:\n"
         (a-plus-abs-b 1 2)
         (a-plus-abs-b 1 -2)
         "\n====================")

; 练习 1.5

(defn p [] p)
(defn practice-1-5
      [x y]
      (if (= x 0)
          0
          y))

(println "practice 1.5:\n"
         (practice-1-5 0 (p))
         "\n====================")

; 解释:
; 背景: p 函数将会形成一个自己调用自己的无限递归, 所以只要执行 p 函数, 程序就会死循环
; 应用序: practice 函数调用时将会计算两参数的值, 调用 p 函数使程序陷入假死
; 正则序: practice 函数调用时将两参数传进 practice 函数体中
;        程序进行到 if 语句
;        求 x 的值, 代换为传入的 0
;        程序进行到 if 的成功语句中, 返回 0
; 重点在于理解应用序的立即求值与正则序的用时求值

; 实例 1.1.7

(defn average
      [x y]
      (/ (+ x y) 2))

(defn improve
      [guess x]
      (average guess (/ x guess)))

(defn abs
      [x]
      (if (< x 0)
          (- x)
          x))

(defn square
      [x]
      (* x x))

(defn good-enough?
      [guess x]
      (< (abs (- (square guess)
                 x))
         0.001))

(defn sqrt-iter
      [guess x]
      (if (good-enough? guess x)
          guess
          (sqrt-iter (improve guess x)
                     x)))

(defn sqrt
      [x]
      (sqrt-iter 1.0 x))

(println "example 1.1.7:\n"
         (sqrt 9)
         "\n"
         (sqrt (+ 100 37))
         "\n"
         (sqrt (+ (sqrt 2)
                  (sqrt 3)))
         "\n"
         (square (sqrt 1000))
         "\n====================")

; 练习 1.6

; 个人猜测:
; 如果使用应用序解析的编译器, 在调用 new-if 函数时, 会计算 else-clause 的式子的值
; 此时又会进入一个新的 sqrt-iter 函数, 并再次在其中调用 new-if 函数, 自此程序陷入无限循环

; 实际上:
; if 语句是一个特殊的语句, 他只会根据输入值执行对应的语句
; 而我们自己定义的语句则会计算所有参数的值, 所以会出错

; 练习 1.7

(defn good-enough-new?
  [guess-old guess-new]
  (> 0.0001
     (abs (- 1
             (/ guess-old guess-new)))))

; let: 在其语句内定义变量, 减少运算量
(defn sqrt-iter-new
      [guess x]
      (let [guess-new (improve guess x)]
           (if (good-enough-new? guess guess-new)
               guess-new
               (sqrt-iter-new guess-new
                              x))))

(defn sqrt-new
      [x]
      (sqrt-iter-new 1.0 x))

(println "practice 1.7:\n"
         "old:"
         (sqrt 0.00009)
         "\n new:"
         (sqrt-new 0.00009)
         "\n====================")

; 练习 1.8

(defn improve-cube
      [guess x]
      (/ (+ (/ x
               (* guess guess))
            (* 2 guess))
         3))

(defn cube-iter
      [guess x]
      (let [guess-new (improve-cube guess x)]
      (if (good-enough-new? guess guess-new)
          guess-new
          (cube-iter guess-new x))))

(defn cube
      [x]
      (cube-iter 1.0 x))

(println "practice 1.8:\n"
  (cube 27)
  "\n====================")

; 练习 1.9

(defn plus-1
      [a b]
      (if (= a 0)
          b
          (inc (plus-1 (dec a) b))))

; 线性递归
;(plus-1 4 5)
;(inc (plus-1 3 5))
;(inc (inc (plus-1 2 5)))
;(inc (inc (inc (plus-1 1 5))))
;(inc (inc (inc (inc (plus-1 0 5)))))
;(inc (inc (inc (inc 5))))
;(inc (inc (inc 6)))
;(inc (inc 7))
;(inc 8)
;9

(defn plus-2
      [a b]
      (if (= a 0)
          b
          (plus-2 (dec a) (inc b))))

; 线性迭代
;(plus-2 4 5)
;(plus-2 3 6)
;(plus-2 2 7)
;(plus-2 1 8)
;(plus-2 0 9)
;9

; 练习 1.10

(defn A
      [x y]
      (cond (= y 0) 0
            (= x 0) (* 2 y)
            (= y 1) 2
            :else (A (- x 1)
                       (A x (- y 1)))))

;(A 1 10)
;(A 0 (A 1 9))
;(A 0 (A 0 (A 1 8)))
;(A 0 (A 0 (A 0 (A 1 7))))
;(A 0 (A 0 (A 0 (A 0 (A 1 6)))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 1 5))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 4)))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 3))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 2)))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 4))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 8)))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 16))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 32)))))
;(A 0 (A 0 (A 0 (A 0 64))))
;(A 0 (A 0 (A 0 128)))
;(A 0 (A 0 256))
;(A 0 512)
;1024

; 不分解了, 再分解就爆炸了
; 最后得出的结论是
; (A 0 n): 2 * n
; (A 1 n) => (A 0 (A 1 n-1)): 2 ^ n
; (A 2 n) => (A 1 (A 2 n-1)): 2 ^ 2(n 个 2 叠起来)
; (A 3 n) => (A 2 (A 3 n-1)): 2 ^ 2((A 3 n-1) 个 2 叠起来, 大脑已爆炸)