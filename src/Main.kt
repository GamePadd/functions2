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

//Функции задачи 7

private fun codeStr(str : String) : String{
    var res = ""
    var counter = 0
    var templateTable : MutableMap<Char,Int> = mutableMapOf()

    for (char in str) {
        if (char !in templateTable) {templateTable[char] = counter; counter++}
        res += templateTable[char]
    }

    return res
}

//Функции задачи 8

private fun getMaxCommon(list : List<String>) : Triple<Pair<String,String>, Int, List<Char>>{
    var res : MutableList<Triple<Pair<String,String>, Int, List<Char>>> = mutableListOf()
    for (i in 0..list.count()-1) {
        var currentWord = list[i]

        for (j in 0..list.count()-1) {
            var secondWord = ""
            var count = 0
            var commonChars : MutableList<Char> = mutableListOf()
            if (i != j) {
                secondWord = list[j]
                //Сюда вернуть трипл со значениями
                //и тут же res.add
                res.add(getCommonLetters(currentWord,secondWord))
            }
        }
    }

    res.sortByDescending { it.second }
    return res[0]
}

private fun getCommonLetters(str1 : String, str2 : String) : Triple<Pair<String,String>, Int, List<Char>>{
    var cmn : MutableList<Char> = mutableListOf()
    var count = 0

    var str1Chars = str1.toCharArray()

    for (char in str2) {
        if(char in str1Chars) {
            count++
            cmn.add(char)
        }
    }

    return Triple(
        Pair(str1,str2),
        count,
        cmn
    )
}

//Функции задачи 9

private fun slogSplit(str : String) : String {
    var res = ""
    var glasnie = arrayOf('а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я')

    for (i in 0..str.count()-1) {
        res+=str[i]
        if (str[i] in glasnie && i < str.count()-1 && str[i+1] !in glasnie) {
            res+="-"
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

    //Задача 7
    println("Задача 7: Кодирование слов по шаблонам")
    print("Введите сообщение: ")
    var msg = readln()
    println("Закодированное сообщение: ${codeStr(msg)}")

    //Задача 8

    println("Задача 8: Поиск максимального пересечения букв")
    val mainList3 = listOf("программа", "грамм", "алгоритм", "мама", "папа", "рамка")
    println(getMaxCommon(mainList3))

    //Задача 9

    /*
     Разбейте слова на слоги по упрощенным правилам:
     Слог обычно начинается с согласной и заканчивается гласной.
     Делал упрощенную систему как и сказано в задании
    */

    println("Задача 9: Разбиение на слоги по правилам")
    print("Введение сообщение: ")
    var msg2 = readln()
    print(slogSplit(msg2))
}