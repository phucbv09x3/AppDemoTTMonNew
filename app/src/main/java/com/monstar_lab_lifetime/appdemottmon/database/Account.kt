package com.monstar_lab_lifetime.appdemottmon.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="sign_up")
data class Account ( @PrimaryKey(autoGenerate = true) val id:Int=0,
                     @ColumnInfo(name = "name") val name:String,
                     @ColumnInfo(name="email") val email:String,
                     @ColumnInfo(name="password") val password:String)
