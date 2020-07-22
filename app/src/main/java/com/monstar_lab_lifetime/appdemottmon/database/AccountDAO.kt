package com.monstar_lab_lifetime.appdemottmon.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AccountDAO {
    @Query("SELECT *FROM sign_up ")
    fun getAllAccount():List<Account>

}