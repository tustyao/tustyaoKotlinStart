package com.sense.tustyao

/**
 * Modifier：
 * kotlin中的访问修饰符，跟java类似，也有区别，kotlin中有4种
 * public/internal/protected/private
 * kotlin中默认是public，所修饰的类/方法/变量所有地方都可以访问
 * private仅限当前文件(或当前类)访问，这个跟java中的一样
 * internal仅限当前应用可访问
 * protected只能子类可以访问，这与java中的有点区别，java中的同一包下也可以访问
 * protected不支持顶级元素使用
 *
 */

/**
 * Extension Function：
 * java中，一个对象访问一个方法，那么此方法必须在类中定义，或者继承父类，实现接口
 * kotlin中，可以通过扩展实现，不必修改源代码，被扩展者称作receiver，一般定义在顶级元素下
 * 注意：
 * 01,
 * 扩展函数是静态编译的，所以决定扩展函数的真实调用时是由声明时参数的receiver决定，而不是实际传入
 * 的真实类型；即父类与子类同时扩展一个相同签名的函数
 * 02,
 * 一个类既有成员函数，又有扩展函数，且同名，参数类型兼容，成员函数的优先级高
 * 03，
 * 扩展函数的访问修饰符不能超过receiver的访问修饰符的范围，如receiver是internal，扩展函数
 * 不能是public,还有一点，扩展函数中不能访问类的private/protected成员
 *
 * 04，
 * nullable类型也可以扩展,如果nullable类型声明了扩展函数，那么null也可以调用此函数，并且执行其中的逻辑
 *
 * 05,
 * 类中也可以声明其他类的扩展，被扩展的类称为extension receiver，声明扩展的类称为dispatcher receiver，扩展函数中
 * 访问同名函数时，优先选择extension receiver的函数，如果需要调用dispatcher receiver的同名函数 使用 this@dispatcherClassName访问
 * 06，
 * 扩展既然可以在类中声明，那么它同样具有类的成员特性，可以被子类重写，对于被重写的扩展函数，dispatcher receiver运行时决定，extension receiver
 * 编译时决定(扩展函数调用时看声明处的类型)
 *
 * Extension property
 * 1,
 * 属性也可以扩展，但是扩展属性没有backing field，因此不能被初始化，且必须提供accessor，accessor中不能使用field关键字,
 *
 */

internal open class Bird {
    open fun fly() {
        println("i can fly")
    }

    //member function优先级高于extension function
    fun dance() {
        println("member function i can dance")
    }
}

internal fun Bird?.say() {
    this?.fly()
    println("say hi")
}

internal class Xique : Bird() {
    override fun fly() {
        println("i am xique,i can fly")
    }
}

//Bird定义一个简单的鸟类，那么我可以不通过在其类中增加函数的形式让其具有其他功能，如dance
internal fun Bird.dance() {
    println("i can dance")
}

private fun Xique.dance() {
    println("i am xique,i can dance")
}

internal fun feedBird(bird: Bird?) {
    bird?.dance() //安全调用?. ,如果bird==null dance()不会被执行
    println(bird?.toString())

}

internal val Bird.wing
    get() = "beautiful wing"

open class D
class D1 : D()
open class C {
    open fun D.foo() {
        println("D.foo in C")
    }

    open fun D1.foo() {
        println("D1.foo in C")
    }

    fun caller(d: D) {
        d.foo() // 调用扩展函数
    }
}

class C1 : C() {
    override fun D.foo() {
        println("D.foo in C1")
    }

    override fun D1.foo() {
        println("D1.foo in C1")
    }
}


fun main(args: Array<String>) {
    var bird = Bird()
    var xique = Xique()
//    bird.dance()
//    bird.fly()
    // feedBird(bird)  // i can dance
    //  feedBird(xique) //i can dance
    null.say() //nullable类型的扩展函数，null也可以调用
    feedBird(bird)
    println(bird.wing)
    C().caller(D()) // 输出 "D.foo in C"
    C1().caller(D()) // 输出 "D.foo in C1" —— 分发接收者虚拟解析
    C().caller(D1()) // 输出 "D.foo in C" —— 扩展接收者静态解析
}