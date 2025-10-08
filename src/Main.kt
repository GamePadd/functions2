//Функции задачи 5

private fun isPal(str : String) : Boolean {
    return (str == str.reversed())
}

private fun getPalList(list : MutableList<String>) : MutableList<Pair<String, String>> {
    var res : MutableList<Pair<String, String>> = mutableListOf()

    for (i in 0..list.count()-1) {
        for (j in 0..list.count()-1) {
            var str = list[i] + list[j]
            if (isPal(str)) {
                res.add(Pair(list[i],list[j]))
            }
        }
    }

    return res
}

fun main() {
    //Задача 5
    println("Задача 5: Поиск палиндромных пар")
    val mainList = mutableListOf("кот","торт","рот","ток")
    val result = getPalList(mainList)
    result.forEach { (w1, w2) -> println("$w1 + $w2 = ${w1+w2} (Палиндром)") }

    //Задача 6
}