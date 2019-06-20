package com.sense.tustyao

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/6/20
 */
/**
 * 1，对象表达式（匿名对象）作为共有属性/共有函数的返回值，函数或属性实际类型是其超类型，无超类就是Any，此时无法访问内部属性和函数
 * 2，在本地和私有作用域使用匿名对象，其类型是匿名对象类型，可以访问内部属性和函数
 * 3,匿名对象内部可以访问包含它的外部变量，不必是final
 */
val o = object {
    val p = 1
    fun test(a: Int, b: Int) = a + b
}

class Tes {
    var i = 2
    //私有属性，所以其类型为匿名对象类型
    private val o = object {
        val p = 1
        fun test(a: Int, b: Int) = a + b
    }

    //公有函数，返回Any类型
    fun a() = object {
        val b = 1
        val c = i
    }

    fun test() {
        o.p
        o.test(1, 2)
        // error
        //a().b
    }
}

/**
 * 对象声明 object objectName{}
 *1， 对象声明不是表达式，不能作为右值，初始化过程线程安全，引用直接objectName.functionName/objectName.property
 *2，可以有超类型
 *3，类内部的对象声明可以加companion ，成为伴生对象，伴生对象的内部属性可以直接使用类名.调用
 *4，伴生对象可以省略对象名,类名可以作为伴生对象的引用 ClassName === ClassName.objectName
 *
 */
object Obj {
    fun registerDataProvider(a: Int) {
        // ……
    }
}

class MyClass {
    companion object KKK {
        val j = 1
    }
}

/**
 * 对象表达式是在使用他们的地方立即执行（及初始化）的；
 * 对象声明是在第一次被访问到时延迟初始化的；
 * 伴生对象的初始化是在相应的类被加载（解析）时，与 Java 静态初始化器的语义相匹配。
 */
fun main(args: Array<String>) {
    // Obj.registerDataProvider(1)
    //val x = MyClass.KKK
    println(MyClass.KKK === MyClass)
}