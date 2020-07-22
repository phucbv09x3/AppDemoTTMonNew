package com.monstar_lab_lifetime.appdemottmon.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="sign_up")
class Account ( @PrimaryKey(autoGenerate = true) var id:Int=0,
    @ColumnInfo(name = "name") var name:String,
@ColumnInfo(name="email") var email:String,
@ColumnInfo(name="password") var password:String)

