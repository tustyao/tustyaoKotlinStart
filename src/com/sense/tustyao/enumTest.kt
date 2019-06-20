package com.sense.tustyao

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/6/20
 */
interface I {
    fun t()
}

enum class RGB { RED, GREEN, BLUE }
enum class B(val age: Int) : I {
    C(2) {
        override fun t() {
            println("C")
        }
    },
    D(4) {
        override fun t() {
            println("D")
        }
    };


}


inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}

fun main(args: Array<String>) {
    printAllValues<RGB>()
    val values = RGB.values()
    print(RGB.valueOf("RED"))
    for (value in values) {
        println(value.name+"--"+value.ordinal)
    }

}