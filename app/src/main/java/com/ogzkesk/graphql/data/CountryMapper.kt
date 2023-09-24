package com.ogzkesk.graphql.data

import com.ogzkesk.GetCountriesQuery
import com.ogzkesk.GetCountryQuery
import com.ogzkesk.graphql.model.DetailedCountry
import com.ogzkesk.graphql.model.SimpleCountry

fun GetCountryQuery.Country.toDetailedCountry() : DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital",
        currency = currency ?: "No Currency",
        languages = languages.map { it.name },
        continent = continent.name
    )
}

fun GetCountriesQuery.Country.toSimpleCountry() : SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital"
    )
}