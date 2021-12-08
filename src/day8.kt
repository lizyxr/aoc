import java.io.File


fun String.alphabetized() = String(toCharArray().apply { sort() })

// occurs in st1 not str2
fun countDifference(str1: String, str2: String): Int {
  return str1.count { !str2.contains(it) }
}

fun countUniqueDigits(digits: List<Pair<List<String>, List<String>>>): Int {
  return digits.sumOf { it.second.count { output -> output.length in listOf(2, 3, 4, 7) } }
}

fun sumDigits(digits: List<Pair<List<String>, List<String>>>): Int {
  return digits.sumOf {
    val map = mutableMapOf<String, String>()
    var seven = ""
    var four = ""
    it.first.forEach { digit ->
      when (digit.length) {
        2 -> map[digit] = "1"
        3 -> {
          map[digit] = "7"
          seven = digit
        }
        4 -> {
          map[digit] = "4"
          four = digit
        }
        7 -> map[digit.alphabetized()] = "8"
      }
    }
    it.first.forEach { digit ->
      when (digit.length) {
        5 -> {
          if (countDifference(seven, digit) == 0) {
            map[digit] = "3"
          } else if (countDifference(four, digit) == 2) {
            map[digit] = "2"
          } else {
            map[digit] = "5"
          }
        }
        6 -> {
          if (countDifference(seven, digit) == 1) {
            map[digit] = "6"
          } else if (countDifference(four, digit) == 1) {
            map[digit] = "0"
          } else {
            map[digit] = "9"
          }
        }
      }

    }
    it.second.joinToString("") { digit -> map[digit]!! }.toInt()
  }
}

fun main(args: Array<String>) {
  val digits = File("src/day8data").readLines().map {
    val pair = it.split(" | ")
    Pair(pair[0].split(" ").map { digit -> digit.alphabetized() },
         pair[1].split(" ").map { digit -> digit.alphabetized() })
  }
  println(countUniqueDigits(digits))
  println(sumDigits(digits))
}