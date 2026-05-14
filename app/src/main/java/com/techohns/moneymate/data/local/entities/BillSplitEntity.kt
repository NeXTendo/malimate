package com.techohns.moneymate.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill_splits")
data class BillSplitEntity(
    @PrimaryKey val id: String,
    val groupId: String,
    val title: String,
    val totalAmount: Double,
    val createdBy: String,
    val isSynced: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
