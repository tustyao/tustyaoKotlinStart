package com.sense.tustyao

import kotlin.reflect.KProperty

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/6/20
 */
/**
 * 委托 通过 by语法实现
 *
 */
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

/**
 * 派生类Derived实现接口Base 并委托给传递进来的b来调用Base的函数
 * 当派生类override后，被委托者b将调用派生类中重写的函数，而不是自己的实现
 * 重写的成员不会被委托对象(被委托者)调用，它只能调用自己对接口实现的成员
 */
class Derived(b: Base) : Base by b {
    override fun print() {
        println("av")
    }
}

/**
 * 属性委托
 * val/var <属性名>: <类型> by <表达式>
 * 属性的委托不必实现任何的接口，但是需要提供一个 getValue() 函数（与 setValue()——对于 var 属性）
 */
class Example {
    var a: String by Delegate()
    val b: String by lazy {
        "HELLO"
    }
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

fun main() {
    val b = BaseImpl(10)
    Derived(b).print()
    val example = Example()
    example.a = "yao"
    example.a
}