package edu.aku.hassannaqvi.uen_midline.viewmodel

import org.threeten.bp.DateTimeException
import org.threeten.bp.LocalDate

class DateRepository {

    companion object {

        fun dateValidator(day: Int, month: Int, year: Int): Boolean {
            val date: LocalDate? = try {
                run { LocalDate.of(year, month, day) }
            } catch (e: DateTimeException) {
                null
            }
            return date != null
        }

        fun isDateLessThenDOB(day: Int, month: Int, year: Int, dt: LocalDate? = null): Boolean {
            val date: LocalDate = try {
                run { LocalDate.of(year, month, day) }
            } catch (e: DateTimeException) {
                null
            } ?: return false

            var localDT = dt
            if (localDT == null) localDT = LocalDate.now()

            return date < localDT
        }

    }

}