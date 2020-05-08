package org.marproject.currencyconversion.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.marproject.currencyconversion.R
import org.marproject.currencyconversion.database.CurrencyRates
import org.marproject.currencyconversion.databinding.ActivityMainBinding
import org.marproject.currencyconversion.utils.dataCurrency
import java.text.DecimalFormat

@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity() {

    // create binding & viewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CurrencyViewModel

    // buat currency object & currency spinner item
    private var currencyRates = CurrencyRates()
    private var currencySpinnerItemAwal = ""
    private var currencySpinnerItemAkhir = ""

    // konversi uang awal dari IDR
    private var konversiUangAwal = 16178.05
    private var konversiUangAkhir = 16178.05

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init binding & viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)

        // config spinner
        spinnerConfig()

        // observe data dari api
        viewModel.currency.observe({ lifecycle }, {
            it?.let { rates -> currencyRates = rates }
        })

        // observe response
        viewModel.response.observe({ lifecycle }, {
            if (it != "") {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        })

        // button convert
        binding.btnConvert.setOnClickListener {
            // pengecekan null value
            if (binding.etMataUang1.text.toString().trim() == "") {
                Toast.makeText(this@MainActivity,
                    "Masukkan nominal mata uang!", Toast.LENGTH_SHORT).show()
            } else {
                hitung()
            }
        }

        // button reset
        binding.btnReset.setOnClickListener {
            reset()
        }

    }

    private fun hitung() {
        // get data input
        val currencyFrom = binding.etMataUang1.text.toString().toDouble()

        // base currency dari API = EUR (euro)
        // rumus : uang yang akan dikonversi (from input) / konversi mata uang awal
        // rumus : hasil tersebut * tujuan konversi mata uang
        val convertToFirstCurrency = currencyFrom / konversiUangAwal
        val result = convertToFirstCurrency * konversiUangAkhir

        // gunakan decimal format untuk pembulatan
        val decimal = DecimalFormat("#,###.#")
        val resultFix = decimal.format(result)
        val firstCurrency = decimal.format(currencyFrom)

        // memunculkan atribut di UI yang di hidden
        showResult()

        // set hasil ke UI
        binding.tvMataUangResult1.text = currencySpinnerItemAwal
        binding.tvMataUangResult2.text = currencySpinnerItemAkhir
        binding.tvHasilUangAwal.text = firstCurrency.toString()
        binding.tvHasilUangAkhir.text = resultFix.toString()
    }

    private fun showResult() {
        binding.tvHasil.visibility = View.VISIBLE
        binding.tvEquals.visibility = View.VISIBLE
        binding.tvEquals2.visibility = View.VISIBLE
        binding.tvHasilUangAwal.visibility = View.VISIBLE
        binding.tvHasilUangAkhir.visibility = View.VISIBLE
        binding.tvMataUangResult1.visibility = View.VISIBLE
        binding.tvMataUangResult2.visibility = View.VISIBLE
        binding.btnReset.visibility = View.VISIBLE
    }

    private fun reset() {
        binding.tvHasil.visibility = View.GONE
        binding.tvEquals.visibility = View.GONE
        binding.tvEquals2.visibility = View.GONE
        binding.tvHasilUangAwal.visibility = View.GONE
        binding.tvHasilUangAkhir.visibility = View.GONE
        binding.tvMataUangResult1.visibility = View.GONE
        binding.tvMataUangResult2.visibility = View.GONE
        binding.btnReset.visibility = View.GONE
        binding.etMataUang1.setText("")
    }

    private fun spinnerConfig() {
        // buat array adapter untuk menampung data
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dataCurrency())
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // set spinner adapter dengan array adapter
        val spinner1 = binding.spinner1
        val spinner2 = binding.spinner2
        spinner1.adapter = arrayAdapter
        spinner2.adapter = arrayAdapter

        // event ketika item di dropdown dipilih
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // get item
                val item = parent?.getItemAtPosition(position).toString()
                currencySpinnerItemAwal = item

                getCurrencyRates(currencySpinnerItemAwal, "awal")
                Log.i("testingKonversiAwal", konversiUangAwal.toString())
            }
        }

        // event ketika item di dropdown dipilih
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // get item
                val item = parent?.getItemAtPosition(position).toString()
                currencySpinnerItemAkhir = item

                getCurrencyRates(currencySpinnerItemAkhir, "akhir")
                Log.i("testingKonversiAkhir", konversiUangAkhir.toString())
            }
        }
    }

    private fun getCurrencyRates (currencySpinner: String, kondisi: String) {

        if (kondisi == "awal") {
            when (currencySpinner) {
                "Indonesian rupiah (IDR)" -> konversiUangAwal = currencyRates.IDR
                "US dollar (USD)" -> konversiUangAwal = currencyRates.USD
                "European dollar (EUR)" -> konversiUangAwal = currencyRates.EUR
                "Australian Dollar (AUD)" -> konversiUangAwal = currencyRates.AUD
                "Hong Kong dollar (HKD)" -> konversiUangAwal = currencyRates.HKD
                "Malaysian ringgit (MYR)" -> konversiUangAwal = currencyRates.MYR
                "Indian rupee (INR)" -> konversiUangAwal = currencyRates.INR
                "Japanese yen (JPY)" -> konversiUangAwal = currencyRates.JPY
                "Bulgarian lev (BGN)" -> konversiUangAwal = currencyRates.BGN
                "Brazilian real (BRL)" -> konversiUangAwal = currencyRates.BRL
                "Canadian dollar (CAD)" -> konversiUangAwal = currencyRates.CAD
                "Swiss franc (CHF)" -> konversiUangAwal = currencyRates.CHF
                "Chinese yuan renminbi (CNY)" -> konversiUangAwal = currencyRates.CNY
                "Czech koruna (CZK)" -> konversiUangAwal = currencyRates.CZK
                "Danish krone (DKK)" -> konversiUangAwal = currencyRates.DKK
                "Pound sterling (GBP)" -> konversiUangAwal = currencyRates.GBP
                "Croatian kuna (HRK)" -> konversiUangAwal = currencyRates.HRK
                "Hungarian forint (HUF)" -> konversiUangAwal = currencyRates.HUF
                "Israeli shekel (ILS)" -> konversiUangAwal = currencyRates.ILS
                "Icelandic krona (ISK)" -> konversiUangAwal = currencyRates.ISK
                "South Korean won (KRW)" -> konversiUangAwal = currencyRates.KRW
                "Mexican peso (MXN)" -> konversiUangAwal = currencyRates.MXN
                "Norwegian krone (NOK)" -> konversiUangAwal = currencyRates.NOK
                "New Zealand dollar (NZD)" -> konversiUangAwal = currencyRates.NZD
                "Philippine peso (PHP)" -> konversiUangAwal = currencyRates.PHP
                "Polish zloty (PLN)" -> konversiUangAwal = currencyRates.PLN
                "Romanian leu (RON)" -> konversiUangAwal = currencyRates.RON
                "Russian rouble (RUB)" -> konversiUangAwal = currencyRates.RUB
                "Swedish krona (SEK)" -> konversiUangAwal = currencyRates.SEK
                "Singapore dollar (SGD)" -> konversiUangAwal = currencyRates.SGD
                "Thai baht (THB)" -> konversiUangAwal = currencyRates.THB
                "Turkish lira (TRY)" -> konversiUangAwal = currencyRates.TRY
                "South African rand (ZAR)" -> konversiUangAwal = currencyRates.ZAR
            }
        } else if (kondisi == "akhir") {
            when (currencySpinner) {
                "Indonesian rupiah (IDR)" -> konversiUangAkhir = currencyRates.IDR
                "US dollar (USD)" -> konversiUangAkhir = currencyRates.USD
                "European dollar (EUR)" -> konversiUangAkhir = currencyRates.EUR
                "Australian Dollar (AUD)" -> konversiUangAkhir = currencyRates.AUD
                "Hong Kong dollar (HKD)" -> konversiUangAkhir = currencyRates.HKD
                "Malaysian ringgit (MYR)" -> konversiUangAkhir = currencyRates.MYR
                "Indian rupee (INR)" -> konversiUangAkhir = currencyRates.INR
                "Japanese yen (JPY)" -> konversiUangAkhir = currencyRates.JPY
                "Bulgarian lev (BGN)" -> konversiUangAkhir = currencyRates.BGN
                "Brazilian real (BRL)" -> konversiUangAkhir = currencyRates.BRL
                "Canadian dollar (CAD)" -> konversiUangAkhir = currencyRates.CAD
                "Swiss franc (CHF)" -> konversiUangAkhir = currencyRates.CHF
                "Chinese yuan renminbi (CNY)" -> konversiUangAkhir = currencyRates.CNY
                "Czech koruna (CZK)" -> konversiUangAkhir = currencyRates.CZK
                "Danish krone (DKK)" -> konversiUangAkhir = currencyRates.DKK
                "Pound sterling (GBP)" -> konversiUangAkhir = currencyRates.GBP
                "Croatian kuna (HRK)" -> konversiUangAkhir = currencyRates.HRK
                "Hungarian forint (HUF)" -> konversiUangAkhir = currencyRates.HUF
                "Israeli shekel (ILS)" -> konversiUangAkhir = currencyRates.ILS
                "Icelandic krona (ISK)" -> konversiUangAkhir = currencyRates.ISK
                "South Korean won (KRW)" -> konversiUangAkhir = currencyRates.KRW
                "Mexican peso (MXN)" -> konversiUangAkhir = currencyRates.MXN
                "Norwegian krone (NOK)" -> konversiUangAkhir = currencyRates.NOK
                "New Zealand dollar (NZD)" -> konversiUangAkhir = currencyRates.NZD
                "Philippine peso (PHP)" -> konversiUangAkhir = currencyRates.PHP
                "Polish zloty (PLN)" -> konversiUangAkhir = currencyRates.PLN
                "Romanian leu (RON)" -> konversiUangAkhir = currencyRates.RON
                "Russian rouble (RUB)" -> konversiUangAkhir = currencyRates.RUB
                "Swedish krona (SEK)" -> konversiUangAkhir = currencyRates.SEK
                "Singapore dollar (SGD)" -> konversiUangAkhir = currencyRates.SGD
                "Thai baht (THB)" -> konversiUangAkhir = currencyRates.THB
                "Turkish lira (TRY)" -> konversiUangAkhir = currencyRates.TRY
                "South African rand (ZAR)" -> konversiUangAkhir = currencyRates.ZAR
            }
        }

    }
}
