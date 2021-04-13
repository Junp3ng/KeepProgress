import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.*
import io.reactivex.rxjava3.schedulers.Schedulers

fun main(){
    val list = listOf("apple", "banana", "cherry")

    val observable = list.toObservable()
    observable.sorted()
}