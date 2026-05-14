package com.techohns.moneymate.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_members")
data class GroupMemberEntity(
    @PrimaryKey val id: String,
    val groupId: String,
    val userId: String,
    val role: String, // ADMIN, MEMBER
    val joinedAt: Long = System.currentTimeMillis()
)
