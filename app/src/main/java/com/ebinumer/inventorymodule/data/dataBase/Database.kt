package com.ebinumer.inventorymodule.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ebinumer.inventorymodule.utils.Converters

@Database(entities = [RegisterEntity::class,GrnEntity::class], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class InventoryDatabase : RoomDatabase() {

    abstract val registerDatabaseDao: RegisterDatabaseDao
    abstract val grnDao: GrnDao
    companion object {

        @Volatile
        private var INSTANCE: InventoryDatabase? = null


        fun getInstance(context: Context): InventoryDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        InventoryDatabase::class.java,
                        "inventory_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}