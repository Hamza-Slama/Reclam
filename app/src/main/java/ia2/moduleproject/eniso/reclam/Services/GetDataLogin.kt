package ia2.moduleproject.eniso.reclam.Services

/**
 * Created by hamza on 25/02/18.
 */
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import ia2.moduleproject.eniso.reclam.Dialogs.AlertDialogShowReponse
import ia2.moduleproject.eniso.reclam.Models.UserInformationSerialized
import ia2.moduleproject.eniso.reclam.R
import ia2.moduleproject.eniso.reclam.Utils.isNetworkAvailable
import kotlinx.android.synthetic.main.dialog_no_internet.view.*
import org.json.JSONException

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
        if(isNetworkAvailable(context!!)) {
            showProgress()
        }
    }


    lateinit var progressDialog: ProgressDialog
    fun showProgress() {

        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading ...")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    fun execute() {
            if(isNetworkAvailable(context!!)) {
        var jsObjRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    try {
            progressDialog.dismiss()

            var r = response.getJSONObject("$1")
            if (r == null){
                 progressDialog.dismiss()
                println("Invalide")
                }
                var gson = Gson()

                UserSer = gson.fromJson(r.toString(), UserInformationSerialized::class.java)
                    } catch (e: JSONException) {
                        var r = response.getJSONObject("$"+"error")
                        Toast.makeText(context, r.getString("message").toString(), Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }

        },
                object : Response.ErrorListener {
                    override fun onErrorResponse(volleyError: VolleyError) {
                        Toast.makeText(context, "Invalid login or password", Toast.LENGTH_LONG).show()
                    }
                })

        VolleyService.requestQueue.add(jsObjRequest)
        VolleyService.requestQueue.start()
            }else {
                AlertDialogShowReponse(context!!)
            }
    }
}

