package com.monstar_lab_lifetime.appdemottmon.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccountDAO {
    @Insert
    suspend fun insertAccount(accountDAO: Account)

    @Query("select * from sign_up ")
    fun getAllAccount():List<Account>

    @Query("SELECT * FROM sign_up WHERE email=:email")
    fun findAccountByMail(email:String) : Account
    @Delete
    fun delete(accountDAO: Account)




}