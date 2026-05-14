package com.techohns.moneymate.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val amount: Double,
    val category: String,
    val note: String,
    val paymentMethod: String, // CASH, AIRTEL, MTN, ZAMTEL
    val date: Long,
    val isSynced: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
