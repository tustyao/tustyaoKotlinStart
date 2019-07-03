package com.sense.tustyao

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.time.LocalDate

/**
 * kotlin中的单例对象是以关键字 [object] 为修饰符来声明的；它具有与类相同的特性(继承，扩展等)，除了不能使用构造器
 * 调用方式跟java中类的静态方法一样
 */

object Singleton {
    val property = "declare a property"
    fun function() {
        println("just define a function...")
    }
}

/**
 * object也可以声明在类中，类的实例共同持有一份object,在object中可以访问类实例中private成员
 * 调用方法 ClassName.objectName.functionName()
 *
 * 如果object前加上 [companion]关键字,访问时可以省略objectName，这样就可以代替工厂方法了
 *
 */

class ContainObject {

    val po = 1
    private fun sayHi() {
        function()
        println("out")
    }

    companion object Singleton {
        val property = "declare a property"

        fun function() {
            ContainObject().sayHi()

            println("just define a function...")
        }
    }
}

/**
 * companion object 名字可以省略 编译编译时将使用Companion
 * 一般定义的方法返回外层包括的类的类型，伴生对象可以实现接口，继承类
 * 这样，如果某个函数需要此接口类型的参数时，可以将此companion object传入，直接使用外层类名
 */

interface A<T> {
    fun b(): T
}

class Outter {
    companion object : A<Outter> {
        override fun b(): Outter {
            return Outter()
        }
    }
}

fun <T> product(inn: A<T>): T {

    return inn.b()
}

/**
 * 对companion object扩展时，如果伴生对象没有名字，则使用className.Companion.
 * 如果有名字则使用其名字
 */

class O {
    companion object
}

fun O.Companion.product(): O {

    println("O.Companion.product")
    return O()
}


operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
        object : Iterator<LocalDate> {
            var current = start
            override fun hasNext() = current <= endInclusive
            override fun next() = current.apply {
                current = plusDays(1)
            }
        }

data class NameComponents(val name: String,
                          val extension: String)

fun splitFilename(fullName: String): NameComponents {
    val (name, extension) = fullName.split('.', limit = 2)
    return NameComponents(name, extension)
}

var a = object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {

    }
}

/**
 * object expression
 *   代替java中的匿名内部类，实现方式跟java类似 ,它能实现多个接口，语法有区别  如
 *  var a = object : MouseAdapter(){
 *              override fun mouseClicked(e: MouseEvent) {
 *                clickCount++
 *              }
 *         }
 */
val items = listOf(1, 2, 3, 4, 5)

// Lambdas 表达式是花括号括起来的代码块。
fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

fun foo(baz: Int, bar: Int = 0) {
    println("bar=$bar and baz =$baz")
}
val sum = fun Int.(other: Int): Int = this + other
val sum1: Int.(Int) -> Int = { other -> plus(other) }

fun <Int> List<Int>.test(arg: Int, argf: Int.(Int) -> Int) {
    var result = arg
    for (e in this) {
        result = argf(result, e)
        println(result)
    }
}


fun testloop() {
    for (i in 1..10) {
        loop@ for (j in 1..10) {
            if (i == 2) break@loop
            println("i = $i ,j=$j")
        }
    }

}

fun main() {

//    O.product()  //call companion object`s extension function
//    Singleton.function() //object单例对象的成员方法调用
//    println(Singleton.property)
//    product(Outter) //伴生对象实现接口，方法中参数类型为此接口类型，传入伴生对象外层类名
//    foo(1)
    items.fold(0, {
        // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
        acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // lambda 表达式中的最后一个表达式是返回值：
        result
    })

    val listOf = listOf(1, 2, 3)
    listOf.test(1) { a: Int -> a + 1 }
}