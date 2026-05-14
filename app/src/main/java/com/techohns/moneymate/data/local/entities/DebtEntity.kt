package com.techohns.moneymate.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debts")
data class DebtEntity(
    @PrimaryKey val id: String,
    val billSplitId: String,
    val owedBy: String,
    val owedTo: String,
    val amount: Double,
    val isPaid: Boolean = false,
    val paidAt: Long? = null,
    val isSynced: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
