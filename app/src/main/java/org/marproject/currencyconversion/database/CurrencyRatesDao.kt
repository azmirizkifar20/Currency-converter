package org.marproject.currencyconversion.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyRatesDao {
    @Query("SELECT * FROM currency_rates")
    fun getRates(): LiveData<CurrencyRates>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRates(rate: CurrencyRates)
}