package com.developer.wise4rmgod.landlord


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.developer.wise4rmgod.landlord.database.AppDatabase
import com.developer.wise4rmgod.landlord.database.UserModel
import com.developer.wise4rmgod.landlord.databinding.FragmentAddtenantBinding
import com.developer.wise4rmgod.landlord.viewmodel.Addtenantviewmodel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.os.CountDownTimer
import androidx.work.*

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

            val newuser = UserModel(
                addtenantBinding.tenantfullname.text.toString(),
                addtenantBinding.tenantage.text.toString().toInt(),
                addtenantBinding.tenantstate.text.toString(),
                addtenantBinding.tenantoccupation.text.toString(),
                addtenantBinding.tenantphonenumber.text.toString().toInt()
            )
            addtenantBinding.tenantfullname.text = null
            addtenantBinding.tenantage.text = null
            addtenantBinding.tenantstate.text = null
            addtenantBinding.tenantoccupation.text = null
            addtenantBinding.tenantphonenumber.text = null

            addtenantViewModel?.addtenantmutablelivedata?.value = newuser

        }

        //  checktextchange()

        return addtenantBinding.root
    }

    fun savetenant(insertnewuser: UserModel) {

        doAsync {
            db?.UserDAO()?.insertAll(insertnewuser)
            uiThread {
                Toast.makeText(activity, "Added Successfully", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun checktextchange() {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                addtenantBinding.tenanttext.text = "getting started"

            }

            override fun onFinish() {
                addtenantBinding.tenanttext.text = "finished"
            }
        }.start()
    }

    fun checknewinternet() {

        val internetdata = Data.Builder()
        internetdata.putString("internet", "Internet is Available")
            .build()
        val constraints = Constraints.Builder()
            //  .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        val simpleRequest = OneTimeWorkRequest.Builder(MyworkManager::class.java)
            // .setInputData(internetdata)
            .setConstraints(constraints)
            .addTag("simple_work")
            .build()

        val workerid = simpleRequest.id

        WorkManager.getInstance().enqueue(simpleRequest)

        WorkManager.getInstance().getWorkInfoByIdLiveData(workerid)
            .observe(this, Observer { workInfo ->
                // Check if the current work's state is "successfully finished"
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    Toast.makeText(activity, "Internet Available", Toast.LENGTH_SHORT).show()
                }
            })

    }


    fun checkinternet() {


        doAsync {

            uiThread {

                //check for internet
                val cm =
                    activity!!.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
                val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
                if (!isConnected) {

                    addtenantBinding.tenanttext.text = "internet is not avaliable"

                } else {

                    addtenantBinding.tenanttext.text = "internet is avaliable"
                }
            }
        }


    }


}


