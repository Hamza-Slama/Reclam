package ia2.moduleproject.eniso.reclam.Dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import ia2.moduleproject.eniso.reclam.R
import kotlinx.android.synthetic.main.dialog_no_internet.view.*

/**
 * Created by hamza on 26/02/18.
 */
fun AlertDialogShowReponse (context : Context){
    val altdial = AlertDialog.Builder(context)
    var context : Context = context
    var inflatore = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var myView = inflatore.inflate(R.layout.dialog_no_internet,null)
    altdial.setView(myView)
    val alert = altdial.create()
    alert.show()

    myView.btn_close_reponse.setOnClickListener {
        alert.dismiss()
    }

}