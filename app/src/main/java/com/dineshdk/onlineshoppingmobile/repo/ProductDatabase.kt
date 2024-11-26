package com.dineshdk.onlineshoppingmobile.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dineshdk.onlineshoppingmobile.models.ProductEntity
import com.dineshdk.onlineshoppingmobile.repo.ProductDao

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}

