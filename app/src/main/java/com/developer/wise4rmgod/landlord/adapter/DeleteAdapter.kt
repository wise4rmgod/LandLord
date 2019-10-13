package com.developer.wise4rmgod.landlord.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.developer.wise4rmgod.landlord.R
import com.developer.wise4rmgod.landlord.database.UserModel

class DeleteAdapter(
    private var dataList: List<UserModel>,
    private val context: Context
) :
    RecyclerView.Adapter<DeleteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.listoftenants,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataList.get(position)

        holder.fullname.text = dataModel.fullname
        holder.occupations.text = dataModel.occupation

        holder.itemView.setOnClickListener {
            //  context.startActivity(goto)
           // Toast.makeText(holder.itemView.context, "" + dataModel.id, Toast.LENGTH_SHORT).show()
        }

    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var fullname: TextView = itemLayoutView.findViewById(R.id.listtenantname)
        var occupations: TextView = itemLayoutView.findViewById(R.id.listtenantoccupation)
        //   lateinit var description: TextView
        //  lateinit var image:ImageView


    }

}