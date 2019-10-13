package com.developer.wise4rmgod.landlord.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "landlord")
data class UserModel(

    var fullname: String? = null,

    var age: Int? = null,

    var state: String? = null,

    var occupation: String? = null,

    var phonenumber: Int? = null,

    @PrimaryKey(autoGenerate = true)
    var id: Int?=null

)