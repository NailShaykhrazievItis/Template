package com.itis.template.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "age")
    var age: Int?,
    @Ignore
    var email: String?
) {

    /*
    * Пример переопределения конструктора для случаев когда используете аннотацию @Ignore.
    * Если игнора нет, то делать этого не нужно. Этот конструктор нужен Руму для правильной кодогенерации в связке с дата классом
    */
    constructor(id: Int, name: String?, age: Int?) : this(id, name, age, null)
}
