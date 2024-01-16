package com.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.database.model.SampleEntity

@Database(
    entities = [SampleEntity::class],
    version = 0
)
abstract class Database : RoomDatabase() {

    /**
     * All abstract instances of Dao interface belong here
     */

}