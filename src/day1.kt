import java.io.File

fun countIncrease(measurements: List<Int>): Int {
  var last = Integer.MAX_VALUE
  return measurements.map {
    val tmp = last
    last = it
    if (last > tmp) 1 else 0
  }.sum()
}

fun getWindowList(measurements: List<Int>): List<Int> {
  return measurements.windowed(3).map { it.sum() }
}

fun main(args: Array<String>) {
  var data = mutableListOf<Int>()
  File("src/day1data").forEachLine { data.add(it.toInt()) }
  println(countIncrease(data))
  println(countIncrease(getWindowList(data)))
}