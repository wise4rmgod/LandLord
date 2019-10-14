package com.developer.wise4rmgod.landlord


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.developer.wise4rmgod.landlord.database.AppDatabase
import com.developer.wise4rmgod.landlord.database.UserModel
import com.developer.wise4rmgod.landlord.databinding.FragmentAddtenantBinding
import com.developer.wise4rmgod.landlord.viewmodel.Addtenantviewmodel
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * A simple [Fragment] subclass.
 */
class AddtenantFragment : Fragment() {

    lateinit var addtenantBinding: FragmentAddtenantBinding
    private var db: AppDatabase? = null
    private var addtenantViewModel: Addtenantviewmodel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //  val addview = inflater.inflate(R.layout.fragment_addtenant, container, false)

        addtenantBinding = FragmentAddtenantBinding.inflate(inflater, container, false)
        addtenantBinding.lifecycleOwner
        // Inflate the layout for this fragment
        // var db = Room.databaseBuilder(applicationContext, AppDb::class.java, "BookDB").build()
        addtenantViewModel = ViewModelProviders.of(this).get(Addtenantviewmodel::class.java)
        db = Room.databaseBuilder(
            activity?.applicationContext!!,
            AppDatabase::class.java,
            "landlordDB"
        ).build()
        // db = AppDatabase.getAppDataBase(context = activity?.applicationContext!!)

        addtenantViewModel?.addtenant()?.observe(this,
            Observer<UserModel> { t ->
                if (t != null) {
                    savetenant(t)
                }

            })

        addtenantBinding.savetenant.setOnClickListener {

            val newuser = UserModel()
            newuser.fullname = addtenantBinding.tenantfullname.text.toString()
            newuser.occupation = addtenantBinding.tenantoccupation.text.toString()
            newuser.state = addtenantBinding.tenantstate.text.toString()
            newuser.phonenumber = addtenantBinding.tenantphonenumber.text.toString().toInt()
            newuser.age = addtenantBinding.tenantage.text.toString().toInt()
            addtenantViewModel?.addtenantmutablelivedata?.value = newuser

            Toast.makeText(activity, "Successful", Toast.LENGTH_SHORT).show()

        }

        return addtenantBinding.root
    }

    fun savetenant(insertnewuser: UserModel) {
        GlobalScope.launch {

            db?.UserDAO()?.insertAll(insertnewuser)
            //fetch Records
        }


    }

}
