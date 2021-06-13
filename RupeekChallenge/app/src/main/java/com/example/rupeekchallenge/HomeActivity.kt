package com.example.rupeekchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONObject

class HomeActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: PlacesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        title = "Home"

        recycler_view.layoutManager = LinearLayoutManager(this, )

        fetchPlaces()

        mAdapter = PlacesAdapter(this)
        recycler_view.adapter = mAdapter

    }

    private fun fetchPlaces(){
        val url = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/placesofinterest39c1c48.json"

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->

                val placesList = ArrayList<Place>()

                for(i in 0 until response.length()){
                    val placeData: JSONObject = response.getJSONObject(i)

                    val place = Place(
                        placeData.getString("name"),
                        placeData.getString("image"),
                        placeData.getString("address"),
                        placeData.getString("longitude"),
                        placeData.getString("latitude")
                    )

                    placesList.add(place)
                }

                mAdapter.updateNews(placesList)
            },
            { error ->

            }
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: Place) {

    }
}
