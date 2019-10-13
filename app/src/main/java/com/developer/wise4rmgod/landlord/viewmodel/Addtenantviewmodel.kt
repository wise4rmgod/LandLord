package com.developer.wise4rmgod.landlord.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.wise4rmgod.landlord.database.UserModel

class Addtenantviewmodel : ViewModel() {

    var addtenantmutablelivedata: MutableLiveData<UserModel>? = null

    fun addtenant(): MutableLiveData<UserModel> {
        if (addtenantmutablelivedata == null) {
            addtenantmutablelivedata = MutableLiveData<UserModel>()
        }
        return addtenantmutablelivedata as MutableLiveData<UserModel>
    }
}