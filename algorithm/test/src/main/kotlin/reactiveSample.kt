import java.util.Locale


fun main(){

    New().sayHello()

    Locale.setDefault(Locale("lt")) //setting Lithuanian as locale

    val str = "\u00cc"
    println("Before case conversion is " + str +
            " and length is " + str.length) // Ì

    val lowerCaseStr = str.toLowerCase()
    println("Lower case is " + lowerCaseStr +
            " and length is " + lowerCaseStr.length) // iı`

}

class New : IOld by getOld() {

}

fun getOld():Old{
    return Old()
}

class Old:IOld{
    override fun sayHello() {
        println("Hello i am old 一".toLowerCase(Locale.PRC))
    }

}

interface IOld {
    fun sayHello()
}