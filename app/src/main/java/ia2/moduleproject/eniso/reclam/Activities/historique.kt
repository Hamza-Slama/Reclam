package ia2.moduleproject.eniso.reclam.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import ia2.moduleproject.eniso.reclam.Adapter.TicketAdapter
import ia2.moduleproject.eniso.reclam.Models.Ticket
import ia2.moduleproject.eniso.reclam.Models.TicketState
import ia2.moduleproject.eniso.reclam.R
import kotlinx.android.synthetic.main.activity_historique.*
import kotlinx.android.synthetic.main.activity_qr_code_reader.*

class historique : AppCompatActivity() {


    private val TAG = "HomeActivity"
    private val ACTIVITY_NUM = 0

    private val mContext = this
    var ListTicket=ArrayList<Ticket>()
    var adpater: TicketAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historique)
        ltestloading()
        var layoutManager = LinearLayoutManager(mContext)
        recycler_view.layoutManager = layoutManager
        adpater = TicketAdapter(ListTicket)
        recycler_view.adapter = adpater
        create_button.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//            Intent(this, QrCodeReaderActivity::class.java).apply {
//                // setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                //addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(this)
//            }

            loadscanner()
        }
    }

    // Ticket (var id : String, var equipment_name: String , var user : String, var  Openingtime : String ,

    private fun ltestloading(){
        var sharesImageURL = "hamza"
        ListTicket.add(Ticket("1","Souris", sharesImageURL, "12/15/2001", "12/15/2001", "Materiel", "2",TicketState.OPEN))
        ListTicket.add(Ticket("2","Rasbperry Pi 3", sharesImageURL, "12/15/2001", "hamza", "http://i.imgur.com/j4AfH6P.jpg", "2",TicketState.ASSIGNED))
        ListTicket.add(Ticket("3","STM32", sharesImageURL, "12/15/2001", "hamza", "http://i.imgur.com/j4AfH6P.jpg", "2",TicketState.IN_PROGRESS))
        ListTicket.add(Ticket("3","STM32", sharesImageURL, "12/15/2001", "hamza", "http://i.imgur.com/j4AfH6P.jpg", "2",TicketState.VALIDATION))

    }

private fun loadscanner(){
    val integrator = IntentIntegrator(mContext)
    integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
    integrator.setPrompt("Scan")
    integrator.setCameraId(0)
    integrator.setBeepEnabled(false)
    integrator.setBarcodeImageEnabled(false)
    integrator.initiateScan()
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show()
            } else {
             //   tvShow!!.text = result.contents
                Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
                Intent(this, test::class.java).apply {
                    // setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    //addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    //intent.putExtra("result",result.contents)
                    startActivity(this)
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
//    private fun loadPost(userid:String) {
//        val url= localhost+"/IshareServer/TweetList.php?op=1&user_id="+userid+"&StartFrom=0"
//
//
//        val jsonObjReq = object : JsonObjectRequest(Method.POST,
//                url, null, Response.Listener { response ->
//            try {
//                if ( response.getString("msg")=="has tweet"){
//                    ListShares.clear()
//                    val tweets = JSONArray(response.getString("info"))
//                    for (i in 0..tweets.length()-1){
//                        val singleTweet= tweets.getJSONObject(i)
//                        ListShares.add(ListTicket(singleTweet.getString("tweet_id"),singleTweet.getString("tweet_text"),
//                                singleTweet.getString("tweet_picture"),singleTweet.getString("tweet_date")
//                                ,singleTweet.getString("first_name"),singleTweet.getString("picture_path"),
//                                singleTweet.getString("user_id")))
//                        adpater!!.notifyDataSetChanged()
//
//                    }
//                }
//
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//        }, Response.ErrorListener { }) {
//            override fun parseNetworkResponse(response: NetworkResponse): Response<JSONObject> {
//                try {
//                    var cacheEntry: Cache.Entry? = HttpHeaderParser.parseCacheHeaders(response)
//                    if (cacheEntry == null) {
//                        cacheEntry = Cache.Entry()
//                    }
//                    val cacheHitButRefreshed = (3 * 60 * 1000).toLong() // in 3 minutes cache will be hit, but also refreshed on background
//                    val cacheExpired = (24 * 60 * 60 * 1000).toLong() // in 24 hours this cache entry expires completely
//                    val now = System.currentTimeMillis()
//                    val softExpire = now + cacheHitButRefreshed
//                    val ttl = now + cacheExpired
//                    cacheEntry.data = response.data
//                    cacheEntry.softTtl = softExpire
//                    cacheEntry.ttl = ttl
//                    var headerValue: String?
//                    headerValue = response.headers["Date"]
//                    if (headerValue != null) {
//                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue)
//                    }
//                    headerValue = response.headers["Last-Modified"]
//                    if (headerValue != null) {
//                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue)
//                    }
//                    cacheEntry.responseHeaders = response.headers
//                    val jsonString = String(response.data)
////                            Charset( HttpHeaderParser.parseCharset(response.headers)))
////                            HttpHeaderParser.parseCacheHeaders(response)
//                    return Response.success(JSONObject(jsonString), cacheEntry)
//                } catch (e: UnsupportedEncodingException) {
//                    return Response.error(ParseError(e))
//                } catch (e: JSONException) {
//                    return Response.error(ParseError(e))
//                }
//
//            }
//
//            override fun deliverResponse(response: JSONObject) {
//                super.deliverResponse(response)
//            }
//
//            override fun deliverError(error: VolleyError) {
//                super.deliverError(error)
//            }
//
//            override fun parseNetworkError(volleyError: VolleyError): VolleyError {
//                return super.parseNetworkError(volleyError)
//            }
//        }
//
//        Volley.newRequestQueue(this).add(jsonObjReq)
//    }

}
