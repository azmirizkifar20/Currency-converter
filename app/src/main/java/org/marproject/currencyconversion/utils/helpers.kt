package org.marproject.currencyconversion.utils

import org.marproject.currencyconversion.database.CurrencyRates
import org.marproject.currencyconversion.network.Rates

fun Rates.asDatabaseModel(): CurrencyRates {
    return CurrencyRates(
        AUD = AUD,
        BGN = BGN,
        BRL = BRL,
        CAD = CAD,
        CHF = CHF,
        CNY = CNY,
        CZK = CZK,
        DKK = DKK,
        GBP = GBP,
        HKD = HKD,
        HRK = HRK,
        HUF = HUF,
        IDR = IDR,
        ILS = ILS,
        INR = INR,
        ISK = ISK,
        JPY = JPY,
        KRW = KRW,
        MXN = MXN,
        MYR = MYR,
        NOK = NOK,
        NZD = NZD,
        PHP = PHP,
        PLN = PLN,
        RON = RON,
        RUB = RUB,
        SEK = SEK,
        SGD = SGD,
        THB = THB,
        TRY = TRY,
        USD = USD,
        ZAR = ZAR,
        EUR = 1.0
    )
}

fun dataCurrency(): Array<String> {
    return arrayOf(
        "Indonesian rupiah (IDR)",
        "US dollar (USD)",
        "European dollar (EUR)",
        "Australian Dollar (AUD)",
        "Hong Kong dollar (HKD)",
        "Malaysian ringgit (MYR)",
        "Indian rupee (INR)",
        "Japanese yen (JPY)",
        "Bulgarian lev (BGN)",
        "Brazilian real (BRL)",
        "Canadian dollar (CAD)",
        "Swiss franc (CHF)",
        "Chinese yuan renminbi (CNY)",
        "Czech koruna (CZK)",
        "Danish krone (DKK)",
        "Pound sterling (GBP)",
        "Croatian kuna (HRK)",
        "Hungarian forint (HUF)",
        "Israeli shekel (ILS)",
        "Icelandic krona (ISK)",
        "South Korean won (KRW)",
        "Mexican peso (MXN)",
        "Norwegian krone (NOK)",
        "New Zealand dollar (NZD)",
        "Philippine peso (PHP)",
        "Polish zloty (PLN)",
        "Romanian leu (RON)",
        "Russian rouble (RUB)",
        "Swedish krona (SEK)",
        "Singapore dollar (SGD)",
        "Thai baht (THB)",
        "Turkish lira (TRY)",
        "South African rand (ZAR)"
    )
}