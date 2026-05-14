package com.techohns.moneymate.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class GroupEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val createdBy: String,
    val type: String, // SPLIT, CHAMA
    val isSynced: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
