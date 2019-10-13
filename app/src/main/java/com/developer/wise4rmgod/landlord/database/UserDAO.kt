package com.developer.wise4rmgod.landlord.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {

    @Query("SELECT * FROM landlord")
    fun getAll(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(todo: UserModel)

    @Query("DELETE FROM landlord WHERE id = :userid")
    fun deletebyid(userid: Int)

    @Delete
    fun deleteall(todo: UserModel)
}