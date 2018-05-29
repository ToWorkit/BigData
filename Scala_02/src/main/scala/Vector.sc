// 序列： 常用的序列 Vector Range

// Vector 带下标的序列，从 0 开始
val v = Vector(1, 2, 3, 4, 5, 6)

// 返回第一个满足条件的元素
v.find(_ > 3)

// 更新下标为 2 的元素值
v.updated(2, 100)

// Range 整数序列
println("第一种写法: " + Range(0, 5))
println("第二种写法: " + (0 until 5))
println("第三种写法: " + (0 to 4))

// 两个Range可以相加
('0' to '9') ++ ('A' to 'z')

// 可以把 Range 转成一个List
1 to 5 toList