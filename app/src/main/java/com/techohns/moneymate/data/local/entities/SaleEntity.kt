package com.techohns.moneymate.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class SaleEntity(
    @PrimaryKey val id: String,
    val productId: String,
    val userId: String,
    val quantitySold: Int,
    val sellingPrice: Double,
    val totalAmount: Double,
    val isSynced: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
