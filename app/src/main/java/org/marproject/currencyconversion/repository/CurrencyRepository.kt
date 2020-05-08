package org.marproject.currencyconversion.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.marproject.currencyconversion.database.CurrencyRatesDao
import org.marproject.currencyconversion.network.ApiCurrency
import org.marproject.currencyconversion.utils.asDatabaseModel

class CurrencyRepository(private val currencyRatesDao: CurrencyRatesDao) {

    val rates = currencyRatesDao.getRates()

    suspend fun refreshDataRates() {
        withContext(Dispatchers.IO) {
            // get data rates from server
            val rates = ApiCurrency.retrofitService.getData().rates
            // insert to room db
            currencyRatesDao.insertRates(rates.asDatabaseModel())
        }
    }
}