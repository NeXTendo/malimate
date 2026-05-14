package com.techohns.moneymate.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contributions")
data class ContributionEntity(
    @PrimaryKey val id: String,
    val groupId: String,
    val userId: String,
    val amount: Double,
    val paymentMethod: String,
    val note: String,
    val isSynced: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
