package org.marproject.currencyconversion.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.marproject.currencyconversion.database.CurrencyDatabase
import org.marproject.currencyconversion.database.CurrencyRates
import org.marproject.currencyconversion.repository.CurrencyRepository

@Suppress("SpellCheckingInspection")
class CurrencyViewModel(application: Application) : AndroidViewModel(application) {

    // buat variabel untuk menampung response
    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    // buat variabel untuk menampung data rates
    val currency : LiveData<CurrencyRates>

    // handling async
    private var job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    // get repository
    private val currencyRatesDao = CurrencyDatabase.getInstance(application).currencyRatesDao
    private val currencyRepository = CurrencyRepository(currencyRatesDao)

    init {
        _response.value = ""

        uiScope.launch {
            try {
                currencyRepository.refreshDataRates()
                _response.value = "Berhasil sinkronisasi data mata uang"
            } catch (t: Throwable) {
                _response.value = "Anda sedang offline"
            }
        }

        currency = currencyRepository.rates
    }

}