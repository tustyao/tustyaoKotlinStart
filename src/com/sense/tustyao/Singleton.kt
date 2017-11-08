package com.sense.tustyao

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
            override fun next() = current.apply{
                current = plusDays(1)
            }
        }
data class NameComponents(val name: String,
                          val extension: String)fun splitFilename(fullName: String): NameComponents {
    val (name, extension) = fullName.split('.', limit = 2)
    return NameComponents(name, extension)
}
/**
 * object expression
 *   用户开代替java中的匿名内部类，实现方式跟java类似 ,它能实现多个接口，语法有区别  如
 *  var a = object : MouseAdapter(){
 *              override fun mouseClicked(e: MouseEvent) {
 *                clickCount++
 *              }
 *         }
 */

fun main(args: Array<String>) {
    O.product()  //call companion object`s extension function
    Singleton.function() //object单例对象的成员方法调用
    println(Singleton.property)
    product(Outter) //伴生对象实现接口，方法中参数类型为此接口类型，传入伴生对象外层类名
}