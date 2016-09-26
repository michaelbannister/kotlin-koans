package iii_conventions

import kotlin.comparisons.compareValuesBy

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return compareValuesBy(this, other, { it.year }, { it.month }, { it.dayOfMonth })
    }
}

operator fun MyDate.rangeTo(other: MyDate) = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            private var nextDate = start

            override fun next(): MyDate {
                val current = nextDate
                nextDate = nextDate.nextDay()
                return current
            }

            override fun hasNext(): Boolean {
                return nextDate <= endInclusive
            }

        }
    }
}
