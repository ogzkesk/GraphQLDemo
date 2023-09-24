package com.ogzkesk.graphql.data

import com.apollographql.apollo3.ApolloClient
import com.ogzkesk.GetCountriesQuery
import com.ogzkesk.GetCountryQuery
import com.ogzkesk.graphql.model.DetailedCountry
import com.ogzkesk.graphql.model.SimpleCountry

class ApolloCountryClient {

    private val apollo = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()

    suspend fun getCountries(): List<SimpleCountry> {
        return apollo.query(GetCountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    suspend fun getCountry(code: String): DetailedCountry? {
        return apollo.query(GetCountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}