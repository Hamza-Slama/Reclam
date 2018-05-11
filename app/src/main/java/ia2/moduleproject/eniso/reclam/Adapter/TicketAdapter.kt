package ia2.moduleproject.eniso.reclam.Adapter

/**
 * Created by hamza on 5/11/18.
 */

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import ia2.moduleproject.eniso.reclam.Models.Ticket
import ia2.moduleproject.eniso.reclam.Models.TicketState
import ia2.moduleproject.eniso.reclam.R


import java.util.ArrayList

class TicketAdapter(private val mDataset: ArrayList<Ticket>) : RecyclerView.Adapter<TicketAdapter.DataObjectHolder>() {


    private var context: Context? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataObjectHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.historique_ticket, parent, false)
        val dataObjectHolder = DataObjectHolder(view)
        context = parent.context
        return dataObjectHolder
    }

    override fun onBindViewHolder(holder: DataObjectHolder, position: Int) {




        holder.tv_name_ticket.setText(mDataset[position].equipment_name)
        holder.tv_date_ticket.setText("${mDataset[position].Openingtime}")
        holder.tv_comment_ticket.setText("${mDataset[position].message}")

        when (mDataset[position].ticketState){
            TicketState.OPEN ->{
                holder.A.alpha=1F
                holder.B.alpha=0.3F
                holder.C.alpha=0.3F
                holder.D.alpha=0.3F
            }
            TicketState.ASSIGNED -> {
                holder.A.alpha=0.3F
                holder.B.alpha=1F
                holder.C.alpha=0.3F
                holder.D.alpha=0.3F
            }
            TicketState.IN_PROGRESS ->{
                holder.A.alpha=0.3F
                holder.B.alpha=0.3F
                holder.C.alpha=1F
                holder.D.alpha=0.3F
            }
            TicketState.VALIDATION -> {
                holder.A.alpha=0.3F
                holder.B.alpha=0.3F
                holder.C.alpha=0.3F
                holder.D.alpha=1F
            }
        }





    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    inner class DataObjectHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tv_name_ticket: TextView
        internal var tv_date_ticket: TextView
        internal var tv_comment_ticket: TextView
        internal var A: RelativeLayout
        internal var B: RelativeLayout
        internal var C: RelativeLayout
        internal var D: RelativeLayout

        init {

            tv_name_ticket = itemView.findViewById<TextView>(R.id.tv_name_ticket) as TextView
            tv_date_ticket = itemView.findViewById<TextView>(R.id.tv_date_ticket) as TextView
            tv_comment_ticket = itemView.findViewById<TextView>(R.id.tv_comment_ticket) as TextView
            A = itemView.findViewById<RelativeLayout>(R.id.A) as RelativeLayout
            B = itemView.findViewById<RelativeLayout>(R.id.B) as RelativeLayout
            C = itemView.findViewById<RelativeLayout>(R.id.C) as RelativeLayout
            D = itemView.findViewById<RelativeLayout>(R.id.D) as RelativeLayout




        }
    }



}
