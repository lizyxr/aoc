import java.io.File
import kotlin.math.abs

fun totalFuel(l: List<Int>, target: Int, rateCalculator: (Long) -> Long) =
  l.sumOf { rateCalculator(abs(it - target.toLong())) }

fun minFuelWithConstantRate(crabLocations: List<Int>): Long {
  return if (crabLocations.isEmpty()) 0 else (crabLocations.minOrNull()!!..crabLocations.maxOrNull()!!).minOf { it ->
    totalFuel(crabLocations,
             it) { it }
  }
}

fun minFuelWithDynamicRate(crabLocations: List<Int>): Long {
  return if (crabLocations.isEmpty()) 0 else (crabLocations.minOrNull()!!..crabLocations.maxOrNull()!!).minOf { it ->
    totalFuel(crabLocations,
             it) { (0..it).sum() }
  }
}

fun main(args: Array<String>) {
  val crabLocations = File("src/day7data").readLines().first().split(",").map { it.toInt() }
  println(minFuelWithConstantRate(crabLocations))
  println(minFuelWithDynamicRate(crabLocations))
}