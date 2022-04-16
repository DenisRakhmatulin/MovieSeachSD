package com.example.movieseachsd.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.movieseachsd.model.entites.rest_entites.MovieDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object MovieLoader {
    fun loadMovie(id: Int): MovieDTO? {
        val uri =
            URL("https://api.themoviedb.org/3/movie/${id}?api_key=b92fe7da8ec4b4826719674503a0a65b")

        lateinit var urlConnection: HttpsURLConnection
        return try {
            urlConnection = uri.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"

            urlConnection.readTimeout = 10000
            val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))

            val lines = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                getLinesForOld(bufferedReader)
            } else {
                getLines(bufferedReader)
            }
            Gson().fromJson(lines, MovieDTO::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            urlConnection.disconnect()
        }
    }

    private fun getLinesForOld(reader: BufferedReader): String {
        val rawData = StringBuilder(1024)
        var tempVariable: String?

        while (reader.readLine().also { tempVariable = it } != null) {
            rawData.append("\n")
        }
        reader.close()
        return rawData.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("/n"))
    }

}