package com.jordan.home.viewmodel

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carry.mobile.retrofit.ApiClient
import com.carry.mobile.retrofit.ApiService
import com.healthybrains.android.room.DatabaseClient
import com.jordan.home.model.NewsDataResponse
import com.jordan.room.AppDatabase
import com.jordan.room.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class CommonViewModel : ViewModel() {

    val rowResponse: MutableLiveData<NewsDataResponse> = MutableLiveData()

    val newsLocalData: MutableLiveData<List<News>> = MutableLiveData()

    private val apiService: ApiService by lazy { ApiClient.create() }
    private lateinit var contextRoom : FragmentActivity

    private val roomServices: AppDatabase by lazy { DatabaseClient.getInstance(contextRoom).appDatabase }

    fun getRows(context: FragmentActivity, country: String, apikey: String, category: String) {
        contextRoom = context
        val observable = apiService.fetchRows(country,apikey,category)

        observable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    rowResponse.value = it

                },
                {
                    if (it is HttpException) {
                        val errorBody = it.response()?.errorBody()?.string()
                        val jsonObject = JSONObject(errorBody)
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )

    }

    fun getLocalStoreNews(context: FragmentActivity) {
        contextRoom = context
        val observable = roomServices.NewsDao().getAll()

        observable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    newsLocalData.value = it

                },
                {
                    if (it is HttpException) {
                        val errorBody = it.response()?.errorBody()?.string()
                        val jsonObject = JSONObject(errorBody)
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )

    }


    fun getStoringNewsOnIndex(category: String) : Int {
        when(category) {
            "business" -> return 1
            "entertainment" -> return 2
            "general" -> return 3
            "health" -> return 4
            "science" -> return 5
            "sports" -> return 6
            "technology" -> return 7
        }
        return 0
    }
}