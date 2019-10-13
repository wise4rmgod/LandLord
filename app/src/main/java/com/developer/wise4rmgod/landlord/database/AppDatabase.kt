package com.developer.wise4rmgod.landlord.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [UserModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDAO(): UserDAO

}