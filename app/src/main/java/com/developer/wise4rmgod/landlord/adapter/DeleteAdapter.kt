package com.developer.wise4rmgod.landlord.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.developer.wise4rmgod.landlord.R
import com.developer.wise4rmgod.landlord.database.AppDatabase
import com.developer.wise4rmgod.landlord.database.UserModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DeleteAdapter(
    private var dataList: ArrayList<UserModel>,
    private val context: Context
) :
    RecyclerView.Adapter<DeleteAdapter.ViewHolder>() {
    var db: AppDatabase? = null
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
        db = Room.databaseBuilder(
            holder.itemView.context!!,
            AppDatabase::class.java,
            "landlordDB"
        ).build()

        val dataModel = dataList.get(position)
        holder.fullname.text = dataModel.fullname
        holder.occupations.text = dataModel.occupation

        holder.deletebtn.setOnClickListener {
            doAsync {
               db?.UserDAO()?.deleteall(dataModel)
                uiThread {

                    Toast.makeText(holder.itemView.context, "deleted"+position, Toast.LENGTH_SHORT).show()
                    dataList.remove(dataModel)
                    notifyDataSetChanged()
                    notifyItemRemoved(position)
                }
            }

        }

        holder.itemView.setOnClickListener {
            //  context.startActivity(goto)
            // Toast.makeText(holder.itemView.context, "" + dataModel.id, Toast.LENGTH_SHORT).show()
        }

    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var fullname: TextView = itemLayoutView.findViewById(R.id.listtenantname)
        var occupations: TextView = itemLayoutView.findViewById(R.id.listtenantoccupation)
        var deletebtn: ImageView = itemLayoutView.findViewById(R.id.deleteicon)
        //   lateinit var description: TextView
        //  lateinit var image:ImageView


    }

}