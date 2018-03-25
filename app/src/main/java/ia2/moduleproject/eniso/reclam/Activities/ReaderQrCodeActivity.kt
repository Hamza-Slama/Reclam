package ia2.moduleproject.eniso.reclam.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import ia2.moduleproject.eniso.reclam.Common.isNetworkAvailable
import ia2.moduleproject.eniso.reclam.Models.NavigablePeriods
import ia2.moduleproject.eniso.reclam.R
import ia2.moduleproject.eniso.reclam.Services.AuthService
import ia2.moduleproject.eniso.reclam.Services.VolleyService
import kotlinx.android.synthetic.main.activity_reader_qr_code.*
import org.json.JSONException

class ReaderQrCodeActivity : AppCompatActivity() {
    var authService = AuthService(this)
    var self : ReaderQrCodeActivity=this;
    val listObjects = ArrayList<NavigablePeriods>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader_qr_code)
        var url = "http://eniso.info/ws/wscript?s=Return(bean(%27core%27).findNavigatablePeriods())"
        var sessionIds = intent.extras.getString("X-JSESSIONID")
        tv_session.text = sessionIds
        tvShowPost.setOnClickListener {
//            authService.postTest(sessionIds, object : ia2.moduleproject.eniso.reclam.Common.Callback<Array<NavigablePeriods>?> {
//                override fun onSuccess(result: Array<NavigablePeriods>?) {
//                    var s = ""
//                    for ( i in 0 .. result!!.size-1){
//                        s+=result.get(i).toString()
//                    }
//                    tv_session.text=s
//                    Toast.makeText(self, s, Toast.LENGTH_LONG).show()
//                }
//
//
//                override fun onReceive() {
//
//                }
//
//
//
//                override fun onError(errorMessage: String) {
//                    Toast.makeText(self, errorMessage, Toast.LENGTH_LONG).show()
//                }
//
//                override fun onNotNetworkAwailable() {
//
//                }
//
//
//            })

            pp(sessionIds)
        }
    }


    fun pp (sessionId : String){
        var url = "http://eniso.info/ws/wscript?s=Return(bean(%27core%27).findNavigatablePeriods())"

            var jsObjRequest = object : JsonObjectRequest(Request.Method.POST, url,null,
                    Response.Listener { response ->

                        try {
                            var r = response.getJSONArray("$1")
                            var gson = Gson()
                            Log.d("reponse",r.toString())
                            var s = r.toString()
                            println(s)
                            Toast.makeText(this,s,Toast.LENGTH_SHORT).show()

                        } catch (e: JSONException) {
                            var r = response.getJSONObject("$" + "error")
                            e.printStackTrace()
                            Toast.makeText(this,r.toString(),Toast.LENGTH_SHORT).show()

                        }

                    },
                    Response.ErrorListener { error ->

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

    }
}
