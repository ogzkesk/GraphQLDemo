package com.ogzkesk.graphql

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ogzkesk.graphql.data.ApolloCountryClient
import com.ogzkesk.graphql.model.SimpleCountry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val apolloClient = ApolloCountryClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomBtn = findViewById<Button>(R.id.random_country)
        var countries = listOf<SimpleCountry>()


        lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                countries = apolloClient.getCountries()
                println(countries)
            }
        }

        randomBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val randomCountry = apolloClient.getCountry(countries.random().code)
                println(randomCountry)
            }
        }
    }
}
