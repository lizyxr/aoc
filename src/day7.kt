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
  val file = File("src/day7data")
  file.useLines { lines ->
    val positions = lines
      .first()
      .split(',')
      .map { it.toInt() }
    println(minFuelWithConstantRate(positions))
    println(minFuelWithDynamicRate(positions))
  }
}