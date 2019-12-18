package com.itis.template.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itis.template.model.dao.UserDao
import com.itis.template.model.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private const val DATABASE_NAME = "user.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        /*
        * Можно написть и так:
        * operator fun invoke(context: Context) = instance ?: buildDatabase(context).also { instance = it }
        *
        * Это более простой способо реализации без проверки на синхронизации потоков. Но может сложиться такая ситуация,
        * когда 2 разных потока придут сюда и создадут 2 разных объекта и у вас уже не синглтоновый объект
        */
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
