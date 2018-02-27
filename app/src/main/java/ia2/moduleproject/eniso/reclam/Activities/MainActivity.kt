package ia2.moduleproject.eniso.reclam.Activities
import  	android.widget.Toast
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import ia2.moduleproject.eniso.reclam.Constant.MyUserLoginAndPassword
import ia2.moduleproject.eniso.reclam.R
import ia2.moduleproject.eniso.reclam.Services.GetDataLogin
import ia2.moduleproject.eniso.reclam.Services.UserSer
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {

    var urlLogin = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLoginUser()
        submit.setOnClickListener {
            var user = user.text
            var pass = pass.text
            urlLogin = "http://eniso.info/ws/login/$user?password=$pass"
            GetDataLogin(this,urlLogin).execute()
            Log.d("user", UserSer.toString())

            if (UserSer != null ) {
                Intent(this, ReaderQrCodeActivity::class.java).apply {
                    startActivity(this)
                }
            }

//            Toast.makeText(this , UserSer.toString(),Toast.LENGTH_LONG).show()
            RememberMe()
         }
        if (UserSer != null ) {
            Intent(this, ReaderQrCodeActivity::class.java).apply {
                startActivity(this)
            }
        }
    }



    fun saveLoginUser() {
        val infofile = getSharedPreferences(MyUserLoginAndPassword, Context.MODE_PRIVATE)
        val editor = infofile.edit()
        editor.putString("username",user!!.text.toString())
        editor.putString("pass", pass!!.text.toString())
        editor.putString("sessionId", UserSer!!.sessionId)
        editor.apply()

    }

    fun getLoginUser() {

        val infofile = getSharedPreferences(MyUserLoginAndPassword, Context.MODE_PRIVATE)
        val name = infofile.getString("username", "")
        val paswword = infofile.getString("pass", "")
       user!!.setText(name)
       pass!!.setText(paswword)
    }

    fun RememberMe() {
        if (remember_me!!.isChecked == true) {
            saveLoginUser()
            getLoginUser()
        }
    }


    fun isLoggedIn(): Boolean {
        val sharedPreferences = this.getSharedPreferences(MyUserLoginAndPassword, Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", null) != null
    }
}











//lateinit var progressDialog: ProgressDialog
//fun showProgress() {
//
//    progressDialog = ProgressDialog(this)
//    progressDialog.setMessage("Downloading Data ...")
//    progressDialog.setCancelable(false)
//    progressDialog.show()
//}
//
//
//fun execute(urlLogin :String) {
//    var mQueue = Volley.newRequestQueue(this)
//    var jsObjRequest = JsonObjectRequest(Request.Method.GET, urlLogin, null, Response.Listener { response ->
//        //            progressDialog.dismiss()
//        var gson = Gson()
//        var r = response.getJSONObject("$1")
//        Log.d("object", r.toString())
//        UserSer = gson.fromJson(r.toString(), UserInformationSerialized::class.java)
//    },
//            Response.ErrorListener {
//
//            })
////        jsObjRequest.retryPolicy = DefaultRetryPolicy(
////                10000,
////                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
////                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
//    mQueue!!.add(jsObjRequest)
//}

/*
    if (isLoggedIn()){
                finish()
                startActivity(Intent(this, ReaderQrCodeActivity::class.java))
            }
 */