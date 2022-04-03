package me.bkkn.gb_ktl_prj2_movies.data

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val FILMS_API_KEY = "2fefea651f90ea8fe7cba508a8c64a10"


class FilmLoader(
    private val listener: FilmLoaderListener,
    private val lat: Double,
    private val lon: Double
) {
    interface FilmLoaderListener {
        fun onLoaded(filmDTO: FilmDTO)
        fun onFailed(throwable: Throwable)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadFilm() {
        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/550?api_key=${FILMS_API_KEY}")
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
//                    urlConnection.addRequestProperty(
//                        "X-Yandex-API-Key",
//                        WEATHER_API_KEY
//                    )
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    // преобразование ответа от сервера (JSON) в модель данных (WeatherDTO)
                    val filmDTO: FilmDTO =
                        Gson().fromJson(getLines(bufferedReader), FilmDTO::class.java)
                    handler.post { listener.onLoaded(filmDTO) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
            //Обработка ошибки
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}