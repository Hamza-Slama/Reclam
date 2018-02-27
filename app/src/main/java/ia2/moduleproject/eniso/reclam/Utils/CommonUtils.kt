package ia2.moduleproject.eniso.reclam.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


@SuppressLint("MissingPermission")
        /**
 * Created by hamza on 25/02/18.
 */
fun isNetworkAvailable(context : Context): Boolean {
    val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}


