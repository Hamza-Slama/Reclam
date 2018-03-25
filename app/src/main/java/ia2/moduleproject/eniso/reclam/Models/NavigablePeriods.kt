package ia2.moduleproject.eniso.reclam.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.R.attr.name
import org.json.JSONObject


/**
 * Created by hamza on 05/03/18.
 */
class NavigablePeriods {
var s : StringBuilder? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("navigatable")
    @Expose
    var navigatable: Boolean? = null
    @SerializedName("readOnly")
    @Expose
    var readOnly: Boolean? = null
    @SerializedName("snapshotName")
    @Expose
    var snapshotName: String? = null
    @SerializedName("creationTime")
    @Expose
    lateinit  var creationTime: JSONObject

    override fun toString(): String {
        return s!!.append("id", id).append("name", name).append("navigatable", navigatable).append("readOnly", readOnly).append("snapshotName", snapshotName).toString()
        //.append("creationTime", creationTime).toString()
    }
}