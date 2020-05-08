package org.marproject.currencyconversion.network

data class Currency (
    var base: String,
    var date: String,
    var rates: Rates
)