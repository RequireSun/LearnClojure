; 参考：
; (原文没了, 只剩快照了) http://webcache.googleusercontent.com/search?q=cache:woI2RKojiEUJ:huangznote.readthedocs.org/zh_CN/latest/language/clojure/composite_type.html+&cd=5&hl=zh-CN&ct=clnk&gl=cn
; http://developer.51cto.com/art/201307/401465.htm
; http://clojure-api-cn.readthedocs.org/en/latest/
; http://clojure.github.io/clojure/

; 使用 str 函数连接字符串
;(println (str "Hello" " " "World" "!"))

; 运算符运算
;(print (+ 1 1) (- 2 1) (* 1 2) (/ 2 1))

; 比较
;(println (= 1 1) (= 2 1))

; 取反运算符
;(println (not true))

; 使用 class 来判断数据类型, nil 就是 null
;(println
;  (str
;    (class 1) "\n"
;    (class 1.) "\n"
;    (class "") "\n"
;    (class false) "\n"
;    (class nil)
;    ))

; 使用 quote 或是 ' 来打印一个表达式(不执行, 直接打印字面量)
;(println
;  (quote (+ 1 2))
;  '(class 1)
;  (not true))

; 使用 eval 运算字面量表示的表达式
;(println
;  (eval '(+ 1 2))
;  (eval (quote (+ 1 2))))

; 序列(列表)
;(println
;  ''(1 2 3)       ":" (class '(1 2 3)) "\n"
;  '(list 1 2 3)  ":" (class (list 1 2 3)) "\n")

; 定义一个列表并进行各种操作, 可以看出每个操作的结果都是生成一个新的列表, 并不会改变原列表
; peek 取出头元素
; pop 取出除头元素之外的所有元素
; conj 将一个新元素添加到列表头部
;(def list_01 (list "a" 'b "c"))
;(println list_01
;  (peek list_01)
;  (pop list_01)
;  (conj list_01 'alpha)
;  list_01)

; 向量 就是数组
;(println
;  '[1 2 3]          ":" (class [1 2 3]) "\n"
;  ''[1 2 3]         ":" (class '[1 2 3]) "\n"
;  '(vector 1 2 3)  ":" (class (vector 1 2 3)))

; 定义一个向量并进行各种操作
; get 获取指定下标的数据, 若不存在则返回 nil
; nth 获取指定下标的数据, 第三个参数为默认值, 未指定时若查询不存在的元素, 则会报错, 指定之后会返回指定的值
; 可以直接使用向量作为函数来获取指定的元素, 但该元素不存在时会报错
; assoc 修改指定下标的数据, 若指定的下标为向量的末尾, 则添加该数据
;(def vector_01 (vector 'a 'b 'b 'd))
;(println vector_01
;  (get vector_01 0)
;  (get vector_01 100)
;  (nth vector_01 100 nil)
;  (vector_01 3)
;  (assoc vector_01 4 100)
;  (assoc vector_01 2 'c)
;  vector_01)

; 映射
; 哈希映射
; 把哈希作为函数直接调用可以取得对应的值, 值不存在时返回 nil
; get 取得对应的值, 值不存在时返回 nil
; assoc 添加 key-value, 将会覆盖重复的
; dissoc 删除 key-value, 将会忽略不存在的
;(def hash_01 (hash-map :k1 "value1" :k2 100))
;(println hash_01
;  (hash_01 :k1)
;  (hash_01 :no-exists-key)
;  (get hash_01 :k2)
;  (get hash_01 :no-exists-key) "\n"
;  (assoc hash_01 :newKey '(1 2 3) :anotherKey [1 2 3] :k1 "test" :k1 "ttt")
;  (dissoc hash_01 :k1 :no-exists-key))

; 有序映射
; 不知道为啥数字和字符不能同时做索引
; sorted-map-by 可以指定比较所用的符号, 用等号的时候错乱的一塌糊涂
;(def sorted_01 (sorted-map :ac 1 :b 3 :1 4))
;(def sorted_02 (sorted-map 1 :b 10 :1 5 'd))
;(def sorted_03 (sorted-map-by > 1 :a 2 :b 3 :c))
;(def sorted_04 (sorted-map-by = 1 :a 2 :b 3 :c 2 :e))
;(println
;  sorted_01 "\n"
;  sorted_02 "\n"
;  sorted_03 "\n"
;  sorted_04 "\n"
;  (get sorted_03 2) "\n")

; merge 合并映射, 如果映射的 key 类型不统一, 会爆炸
; zipmap 生成映射, 生成出来的是普通哈希映射, 且顺序为输入的倒序, 欠缺的值将会忽略
;(println
;  (merge sorted_03 sorted_04) "\n"
;  (zipmap '(:a :b :e :d :c) '(1 3 5 2)) "\n"
;  (contains? sorted_01 :ac) "\n"
;  (class sorted_01) "\n"
;  (class sorted_04) "\n"
;  (class {:a 1 :b 2 :c 3}))

; 集合
;(def set_01 #{ :a :b :c :d })
;(def set_02 (hash-set :e :f :d))

; conj 向集合中添加数据
; disj 删除集合中的某个数据
; get 集合中存在该值时, 返回该值, 否则返回 nil
; 直接将集合作为函数调用同上
;(println
;  set_01
;  set_02 "\n"
;  (conj set_01 :z :x :a)
;  (conj set_02 [:p :q]) "\n"
;  (disj set_01 :b)
;  (disj set_02 :g) "\n"
;  (get set_01 :c)
;  (get set_02 :no-exists-key) "\n"
;  (set_01 :a)
;  (set_02 :no-exists-key))

; 需要引入类库
; union 返回集合的并集
; intersection 返回集合的交集
; difference 返回在二个集合中没有出现过的第一个集合的元素
;(use 'clojure.set)
;(println
;  (union set_01 set_02) "\n"
;  (intersection set_01 set_02) "\n"
;  (difference set_01 set_02))

; 有序集合
;(def set_03 (sorted-set :i :h :j))
;(println set_03)

;(def listTest '(1 2 3))
;(def vectorTest [1 2 3])
;(def hashMapTest (hash-map :k1 "value1"))
;(def sortedMapTest (sorted-map :k2 "value2"))
;(def setTest (hash-set :a :b))
;(def sortedSetTest (sorted-set :a :b))

; coll? 判断是否是集合类
;(println
;  (coll? listTest)
;  (coll? vectorTest)
;  (coll? hashMapTest)
;  (coll? sortedMapTest)
;  (coll? setTest)
;  (coll? sortedSetTest))

;seq? 判断是否有序
;(println
;  (seq? listTest)
;  (seq? vectorTest)
;  (seq? hashMapTest)
;  (seq? sortedMapTest)
;  (seq? setTest)
;  (seq? sortedSetTest))

; range 从 0 开始生成数字
; take 取前 n 个数字,
;(println
;  (range 4)
;;  (range)
;  (take 6 (range))
;  (take 3 [1 2 5 6])
;;  (take 4)
;  (take-last 4 (range 12))
;  (take-nth 6 (range 20)))

; cons 向向量或者列表头部添加数据
; conj 向向量尾部或是列表头部添加数据
; concat 合并向量或者列表, 合并后的结果总为列表
;(println
;  (cons 4 [1 2 3])
;  (cons 4 '(1 2 3))
;  (conj [1 2 3] 4)
;  (conj '(1 2 3) 4)
;  (concat [1 2] '(3 4))
;  (concat [1 2] [3 4]))

; inc 相当于 ++
; dec 相当于 --
; map 对集合中每一项执行一次函数
; even? 判断是否是偶数
; odd? 判断是否是奇数
;(println
;  (inc 1)
;  (map inc [1 2 3])
;  (map dec '(1 2 3))
;  (filter even? [1 2 3])
;  (filter odd? [1 2 3]))

; reduce 不停调用(忘记怎么表述了, 和 js 的 reduce 一样用, 虽然 js 就是学 lisp 做的 reduce)
;(println
;  (reduce + [1 2 3 4])
;  (reduce + 1 [2 3 4])
;  (reduce concat [] [[1 2] [3 4] [5 6] [7 8]]))



