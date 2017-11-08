package com.sense.tustyao


//方法的参数类型为函数式接口类型
abstract class P {
    abstract fun testLambda(r: () -> Unit)
}

class OP : P() {
    override fun testLambda(r: () -> Unit) {
        r()
        print("vvv")
    }
}

data class Person(val name: String, val age: Int) {

    fun get(): Int = age
}

fun testFunctional(i: Int, r: Runnable) {

}

fun main(args: Array<String>) {

    val a:String? = null
    print(a!!.length)

    testFunctional(1, Runnable { println(1) })
    Test().postponeComputation(1){ print(1)}
    //结合操作
//    val list = listOf(1, 2, 3, 4)
//    val map = list.map { it > 2 }
//    //val filter = list.filter { it % 2 == 0 }
//    println("原集合$list")
//    println("map集合$map")
//    println("原集合$list")
    //  println("filter后集合$filter")
    //集合操作

    val people = listOf(Person("Alice", 29), Person("xiao", 31), Person("Bob", 31))
    println(people.groupBy { it.age })
    println(people.count { it.age < 30 })
    println(people.find { it.age < 32 })
    val se = generateSequence(0) { it + 1 }
    println(se.takeWhile { it > 10 }.sum())
//    println(people.all { it.age  <30 })
//    println(people.any { it.age <30 })

//    println(people.maxBy(Person::age))
//    val names =people.filter { it.age > 30 }.map(Person::name)
//    println(names)
//    println(people.maxBy(Person::age))
//    val person = Person("yao", 12)
//    val p = Person::age //member reference
//    val p1 = person::age //bound reference
//    val age = p(person)
//    val age1 = p1()
//    println(age)
//    println(age1)
//
//    run { println("aaa") }
//    val a = {
//        var i = 0
//        println(i)
//        println("我是lambda表达式")
//    }
//    OP().testLambda { a() }
}