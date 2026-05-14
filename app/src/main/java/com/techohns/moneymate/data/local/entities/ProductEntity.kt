package com.techohns.moneymate.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val name: String,
    val buyingPrice: Double,
    val sellingPrice: Double,
    val stockQty: Int,
    val lowStockThreshold: Int = 5,
    val isSynced: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
