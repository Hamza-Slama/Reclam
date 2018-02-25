package ia2.moduleproject.eniso.reclam.Services

/**
 * Created by hamza on 25/02/18.
 */
import android.app.ProgressDialog
import android.content.Context
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson
import ia2.moduleproject.eniso.reclam.Models.UserInformationSerialized

/**
 * Created by hamza on 25/02/18.
 */

var UserSer: UserInformationSerialized? = null

public class GetDataLogin {
    var context: Context? = null
    var url: String? = null

    constructor(context: Context, url: String) {
        this.context = context
        this.url = url
        showProgress()
    }

    lateinit var progressDialog: ProgressDialog
    fun showProgress() {

        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Downloading Data ...")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    private val mQueue: RequestQueue? = null
    fun ok() {
        var jsObjRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
            progressDialog.dismiss()
            var gson = Gson()
            var r = response.getJSONObject("$1")
            UserSer = gson.fromJson(r.toString(), UserInformationSerialized::class.java)
        },
                Response.ErrorListener {

                })
        jsObjRequest.retryPolicy = DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        mQueue!!.add(jsObjRequest)
    }
}