//package ia2.moduleproject.eniso.reclam.Services
//
///**
// * Created by hamza on 25/02/18.
// */
//import android.app.AlertDialog
//import android.app.ProgressDialog
//import android.content.Context
//import android.view.LayoutInflater
//import android.widget.Toast
//import com.android.volley.*
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley
//import com.google.gson.Gson
//import ia2.moduleproject.eniso.reclam.Dialogs.AlertDialogShowReponse
//import ia2.moduleproject.eniso.reclam.Models.UserInformationSerialized
//import ia2.moduleproject.eniso.reclam.R
//import ia2.moduleproject.eniso.reclam.Utils.isNetworkAvailable
//import kotlinx.android.synthetic.main.dialog_no_internet.view.*
//import org.json.JSONException
//import okhttp3.OkHttpClient
//import java.io.File
//import  android.util.LruCache
//
//
///**
// * Created by hamza on 25/02/18.
// */
//
//var UserSer: UserInformationSerialized? = null
//
//public class GetDataLogin {
//    var test = true
//    var context: Context? = null
//    var url: String? = null
//
//    constructor(context: Context, url: String) {
//        this.context = context
//        this.url = url
//        if (isNetworkAvailable(context!!) && test ) {
//            showProgress()
//        }
//    }
//
//
//
//    lateinit var progressDialog: ProgressDialog
//
//    fun showProgress() {
//
//        progressDialog = ProgressDialog(context)
//        progressDialog.setMessage("Loading ...")
//        progressDialog.setCancelable(false)
//        progressDialog.show()
//    }
//
//    fun execute() {
//
//        if (isNetworkAvailable(context!!)) {
//            var jsObjRequest =  JsonObjectRequest(Request.Method.GET, url, null,
//                    Response.Listener { response ->
//                        progressDialog.dismiss()
//                        try {
//                            var r = response.getJSONObject("$1")
//                            var gson = Gson()
//                            UserSer = gson.fromJson(r.toString(), UserInformationSerialized::class.java)
//                            progressDialog.dismiss()
//                        } catch (e: JSONException) {
//                            var r = response.getJSONObject("$" + "error")
//                            Toast.makeText(context, r.getString("message").toString(), Toast.LENGTH_LONG).show()
//                            e.printStackTrace()
//                        }
//
//                    },
//                    Response.ErrorListener { error ->
//                    })
//
//            VolleyService.requestQueue.add(jsObjRequest)
//
//            VolleyService.requestQueue.start()
//        } else {
//            AlertDialogShowReponse(context!!)
//        }
//    }
//
//    fun post(sessionId: String) {
//        test = false
//        if (isNetworkAvailable(context!!)) {
//            progressDialog.dismiss()
//            var jsObjRequest = object : JsonObjectRequest(Request.Method.POST, url,null,
//                    Response.Listener { response ->
//                        try {
//                            var r = response.getJSONObject("$1")
//                            var gson = Gson()
//                           var s = r.toString()
//                            println(s)
//                            Toast.makeText(context, s, Toast.LENGTH_LONG).show()
//                        } catch (e: JSONException) {
//                            var r = response.getJSONObject("$" + "error")
//                            Toast.makeText(context, r.getString("message").toString(), Toast.LENGTH_LONG).show()
//                            e.printStackTrace()
//                        }
//
//                    },
//                    Response.ErrorListener { error ->
//                    }) {
//                @Throws(AuthFailureError::class)
//                override fun getHeaders(): Map<String, String> {
//                    val headers = HashMap<String, String>()
//                    headers.put("sessionId", sessionId)
//                    return headers
//                }
//            }
//
//            VolleyService.requestQueue.add(jsObjRequest)
////            progressDialog.dismiss()
//            VolleyService.requestQueue.start()
//        } else {
//            AlertDialogShowReponse(context!!)
//        }
//    }
//}
//
//
///*
//File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
//Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
//
//OkHttpClient httpClient = new OkHttpClient.Builder()
//    .cache(cache)
//    .addInterceptor(chain -> {
//        try {
//            return chain.proceed(chain.request());
//        } catch (Exception e) {
//            Request offlineRequest = chain.request().newBuilder()
//                    .header("Cache-Control", "public, only-if-cached," +
//                            "max-stale=" + 60 * 60 * 24)
//                    .build();
//            return chain.proceed(offlineRequest);
//        }
//    })
//    .build();
//
//retrofit = new Retrofit.Builder()
//    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//    .addConverterFactory(gsonConverterFactory)
//    .client(httpClient)
//    .baseUrl(BASE_URL)
//    .build();
//}
// */
////    var httpCacheDirectory = File(context.getCacheDir(), "httpCache")
////    var cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
////
////    var httpClient = OkHttpClient.Builder()
////            .cache(cache)
////            .addInterceptor { chain ->
////                try {
////                    return OkHttpClient.Builder()
////                            .cache(cache)
////                            .addInterceptor chain . proceed chain.request()
////                } catch (e: Exception) {
////                    val offlineRequest = chain.request().newBuilder()
////                            .header("Cache-Control", "public, only-if-cached," +
////                                    "max-stale=" + 60 * 60 * 24)
////                            .build()
////                    return OkHttpClient.Builder()
////                            .cache(cache)
////                            .addInterceptor chain . proceed offlineRequest
////                }
////            }
////            .build()