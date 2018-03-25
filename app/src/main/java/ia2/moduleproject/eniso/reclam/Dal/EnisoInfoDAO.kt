package ia2.moduleproject.eniso.reclam.Dal

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import ia2.moduleproject.eniso.reclam.Common.Callback
import ia2.moduleproject.eniso.reclam.Models.UserInformationSerialized
import ia2.moduleproject.eniso.reclam.Services.VolleyService
import org.json.JSONException

import dmax.dialog.SpotsDialog
import ia2.moduleproject.eniso.reclam.Common.isNetworkAvailable
import ia2.moduleproject.eniso.reclam.Dialogs.AlertDialogShowReponse
import ia2.moduleproject.eniso.reclam.Models.NavigablePeriods
import ia2.moduleproject.eniso.reclam.R
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import org.json.JSONObject






/**
 * Created by hamza on 02/03/18.
 */

class EnisoInfoDAO {
    var sessionId : String? = null
    var context : Context? = null
    lateinit var progressDialog: ProgressDialog
    lateinit var dialog: AlertDialog
    constructor(context : Context?){
        this.context=context
    }



    fun showProgress() {
        dialog = SpotsDialog(context, R.style.Custom)
        dialog.show()
//        progressDialog = ProgressDialog(context)
//        progressDialog.setMessage("Loading ...")
//        progressDialog.setCancelable(false)
//        progressDialog.show()
    }


    fun progressDismiss(){
        dialog.dismiss()
//        progressDialog.dismiss()
    }


    fun login(user: String, password: String , listener : Callback<UserInformationSerialized?>) {
        var urlLogin = "http://eniso.info/ws/login/$user?password=$password"
        if (isNetworkAvailable(context!!)) {
            var jsObjRequest = JsonObjectRequest(Request.Method.GET, urlLogin, null,
                    Response.Listener { response ->
                        //progressDialog.dismiss()
                        listener.onReceive()
                        try {
                            var r = response.getJSONObject("$1")
                            var gson = Gson()
                            listener.onSuccess(gson.fromJson(r.toString(), UserInformationSerialized::class.java))
                        } catch (e: JSONException) {
                            var r = response.getJSONObject("$" + "error")
                            e.printStackTrace()
                            listener.onError(r.getString("message").toString())
                        }
                    },
                    Response.ErrorListener { error ->
                        listener.onError(error.toString())
                    })

            VolleyService.requestQueue.add(jsObjRequest)
            VolleyService.requestQueue.start()
        } else {
            listener.onNotNetworkAwailable()
        }

    }

    fun post(sessionId: String, listener : Callback<Array<NavigablePeriods>?>) {
        var url = "http://eniso.info/ws/wscript?s=Return(bean(%27core%27).findNavigatablePeriods())"
        if (isNetworkAvailable(context!!)) {
            var jsObjRequest = object : JsonObjectRequest(Request.Method.POST, url,null,
                    Response.Listener { response ->
                        listener.onReceive()
                        try {
                            var r = response.getJSONArray("$1")
                            Log.d("reponse",r.toString())
                            var gson = Gson()
                           var s = r.toString()
                            println(s)
                            val data = gson.fromJson(r.toString(), Array<NavigablePeriods>::class.java)
//                            val listObjects = ArrayList<NavigablePeriods>()
//                            for (i in 0 .. r.length()-1) {
//
//                                //get the object
//                                val jsonObject = r.getJSONObject(i)
//
//                                //get string of object from Json library to convert it to real object with Gson library
//                                listObjects.add(gson.fromJson(jsonObject.toString(), NavigablePeriods::class.java))
//                            }
                            //TODO : add the array object for the json file
                            listener.onSuccess(data)
                        } catch (e: JSONException) {
                            var r = response.getJSONObject("$" + "error")
                            e.printStackTrace()
                            listener.onError(r.getString("message").toString())
                        }

                    },
                    Response.ErrorListener { error ->
                        listener.onError(error.toString())
                    }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("X-JSESSIONID", sessionId)
                    return headers
                }
            }

            VolleyService.requestQueue.add(jsObjRequest)
//            progressDialog.dismiss()
            VolleyService.requestQueue.start()
        } else {
            listener.onNotNetworkAwailable()
        }
    }
}




