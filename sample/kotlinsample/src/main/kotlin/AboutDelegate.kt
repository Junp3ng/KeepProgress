

interface ICar{
    // 启动
    fun start()

    // 刹车
    fun brake()
}
// 奥迪
class AUDI:ICar {

    // 奥迪启动了
    override fun start() {
        println("AUDI start now")
    }

    // 奥迪刹车了
    override fun brake() {
        println("AUDI brake now")
    }
}

// 宝马
class BMW:ICar {

    // 宝马启动了
    override fun start() {
        println("BMW start now")
    }

    // 宝马刹车了
    override fun brake() {
        println("BMW brake now")
    }
}

interface IFriend {

    // 从朋友处借车
    fun getCar():ICar
}

// 朋友A 有一辆奥迪
class FriendA():IFriend{
    private val car = AUDI()
    override fun getCar(): ICar {
        return car
    }
}

// 朋友B 有一辆宝马
class FriendB():IFriend{
    private val car = BMW()
    override fun getCar(): ICar {
        return car
    }
}

//// 我自己没有车，得从外边借
//class MyCar(private val carFromFriend:ICar):ICar{
//    override fun start() {
//        carFromFriend.start()
//    }
//
//    override fun brake() {
//        carFromFriend.brake()
//    }
//}

// 我自己没有车，得从外边借
class MyCar(carFromFriend:ICar):ICar by carFromFriend{

}

fun main() {
    println("第一天")
    // 首先：找朋友A借了辆车
    val carFromFriendA:ICar = FriendA().getCar()
    // 然后：假装这辆车是自己的车
    var myCar:MyCar = MyCar(carFromFriendA)
    // 接着开出去
    myCar.start()
    myCar.brake()

    println("第二天")
    // 然后第二天，又找朋友B借了车
    val carFromFriendB: ICar = FriendB().getCar()
    // 假装这辆车是自己的
    myCar = MyCar(carFromFriendB)
    // 开出去
    myCar.start()
    myCar.brake()
}