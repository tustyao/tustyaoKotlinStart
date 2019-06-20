package com.sense.tustyao

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/6/19
 */
/**
 * 数据类 data class
 * 1，主构造器必须至少有一个属性，并且必须使用val/var生命
 * 2，如果需要无参构造器，需要主构造器中属性设置默认值
 * 3，主构造器中的参数会在编译器生成的函数(equals(),hash(),copy(),toString())使用
 * 4,copy()函数主要用于拷贝一个对象，只改变部分属性,且属性必须是主构造器中的属性，
 * 5，componentN() 结构时亦然
 */
data class Person(val name: String = "") {
    var age: Int = 23

}

/**
 * 密封类 sealed class （枚举的扩展）
 * 1，抽象，不可实例化
 * 2，构造函数私有，不允许有非private的构造器
 * 3,密封类子类需要放置同一文件下,子类的子类则不必
 * 4，当when表达式使用密封类时候可以省去else子句
 */
sealed class Expr(val b: String = "") {
    val a = ""
}

interface AA {
    val a: Int
}

data class SubExpr(override val a: Int) : AA


fun main(args: Array<String>) {

}