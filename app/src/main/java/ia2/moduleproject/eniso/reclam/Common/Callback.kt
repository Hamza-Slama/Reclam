package ia2.moduleproject.eniso.reclam.Common


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by hamza on 02/03/18.
 */


interface Callback<T> {
    fun onReceive()
    fun onSuccess(result:T)
    fun onError(errorMessage:String)
    fun onNotNetworkAwailable()
}

@SuppressLint("MissingPermission")

fun isNetworkAvailable(context : Context): Boolean {
    val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}