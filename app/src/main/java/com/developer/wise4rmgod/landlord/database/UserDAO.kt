package com.developer.wise4rmgod.landlord.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {

    @Query("SELECT * FROM landlord")
    fun getAll(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(todo: UserModel)

    @Delete
    fun delete(todo: UserModel)
}