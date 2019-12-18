package com.itis.template.model.dao

import androidx.room.*
import com.itis.template.model.entity.User

@Dao
interface UserDao {

    /*
    * По умолчанию выполняются в Dispatchers.IO - нет смысла переключать контекст для вызова даошных методов
    */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()
}
