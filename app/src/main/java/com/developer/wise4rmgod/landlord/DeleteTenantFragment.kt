package com.developer.wise4rmgod.landlord


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.developer.wise4rmgod.landlord.adapter.DeleteAdapter
import com.developer.wise4rmgod.landlord.database.AppDatabase
import com.developer.wise4rmgod.landlord.database.UserModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * A simple [Fragment] subclass.
 */
class DeleteTenantFragment : Fragment() {

    var db: AppDatabase? = null
    var dataList = ArrayList<UserModel?>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val deleteview = inflater.inflate(R.layout.fragment_delete_tenant, container, false)

        val deleterecycler = deleteview.findViewById<RecyclerView>(R.id.deleterecyclerview)

        deleterecycler.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)

        db = Room.databaseBuilder(
            activity?.applicationContext!!,
            AppDatabase::class.java,
            "landlordDB"
        ).build()
        // Inflate the layout for this fragment
        doAsync {

            val listy = db?.UserDAO()?.getAll()
            uiThread {
                dataList.addAll(listy!!)
                deleterecycler.adapter =
                    DeleteAdapter(listy, activity?.applicationContext!!)
                deleterecycler.adapter?.notifyDataSetChanged()

                listy.forEach()
                {

                }
            }

        }
        /**   GlobalScope.launch {
        val getdetails = db?.UserDAO()?.getAll()



        } **/
        return deleteview
    }

}
