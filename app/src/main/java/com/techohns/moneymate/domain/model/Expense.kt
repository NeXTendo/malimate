package com.techohns.moneymate.domain.model

data class Expense(
    val id: String,
    val userId: String,
    val amount: Double,
    val category: String,
    val note: String,
    val paymentMethod: String,
    val date: Long
)

fun com.techohns.moneymate.data.local.entities.ExpenseEntity.toDomain() = Expense(
    id = id,
    userId = userId,
    amount = amount,
    category = category,
    note = note,
    paymentMethod = paymentMethod,
    date = date
)
