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

//Функции задачи 6

private fun createChains(list : MutableList<String>) : List<List<String>>{
    var res : MutableList<List<String>> = mutableListOf()

    fun buildChain(curChain: List<String>) {
        val lastWord = curChain.last()
        val lastChar = lastWord.last()

        for(i in 0..list.count() - 1) {
            if (list[i] !in curChain && list[i].first() == lastChar) {
                val newChain = curChain + list[i]
                res.add(newChain)
                buildChain(newChain)
            }
        }
    }

    for (word in list) {
        buildChain(listOf(word))
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

    /*
    Я использовал рекурсивный метод составления цепочек (спуск в глубину DFS).
    Памятка для себя:
    Мы делаем рекурсивный спуск в глубину от каждого слова в листе, в этом спуске
    я добавляю новое слово в конец цепочки в случае если их начало и конец совпадают,
    после чего вызываю этот же метод, который будет дополнять эту цепочку, когда мы дойдем до
    конца листа, рекурсия прекратится и функция вернется на уровень выше и продолжить собирать другую цепочку
    и так до тех пор, пока мы не переберем все варианты с этим словом, после чего мы выходим из рекурсии
    и повторяем тоже самое но для других слов для листа
    */

    println("Задача 6: Анализ цепочек слов")
    val mainList2 = mutableListOf("кот","торт","рот","ток")
    var result2 = createChains(mainList2)
    result2.forEachIndexed { index, chain -> println("Цепочка ${index+1}: $chain") }
}