(ns learn.my-project)

; 定义私有变量的两种方法
; 在 def 后加 ^{} 来限定属性
; 使用 def- 宏
(def ^{:private true}
  source-name "supersource")

(defn- data-stream
  [source]
  (comment ...))

; 还可以使用 ^{} 将定义设定为常量
(def ^{:const true}
  default-score 100)

(defn test-hello
  [& args]
  (println "Hello, World!"))