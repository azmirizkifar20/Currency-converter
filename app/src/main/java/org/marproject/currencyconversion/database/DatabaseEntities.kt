package org.marproject.currencyconversion.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_rates")
data class CurrencyRates (
    @PrimaryKey
    val AUD: Double = 1.6598,
    val BGN: Double = 1.9558,
    val BRL: Double = 5.8565,
    val CAD: Double = 1.5077,
    val CHF: Double = 1.0558,
    val CNY: Double = 7.6665,
    val CZK: Double = 27.097,
    val DKK: Double = 7.4584,
    val GBP: Double = 0.86905,
    val HKD: Double = 8.43,
    val HRK: Double = 7.579,
    val HUF: Double = 352.72,
    val IDR: Double = 16178.05,
    val ILS: Double = 3.8069,
    val INR: Double = 81.6108,
    val ISK: Double = 159.3,
    val JPY: Double = 115.87,
    val KRW: Double = 1313.09,
    val MXN: Double = 25.7953,
    val MYR: Double = 4.6767,
    val NOK: Double = 11.184,
    val NZD: Double = 1.7705,
    val PHP: Double = 54.772,
    val PLN: Double = 4.5336,
    val RON: Double = 4.8431,
    val RUB: Double = 79.892,
    val SEK: Double = 10.6639,
    val SGD: Double = 1.5324,
    val THB: Double = 35.216,
    val TRY: Double = 7.5979,
    val USD: Double = 1.0876,
    val ZAR: Double = 19.6572,
    val EUR: Double = 1.0
)