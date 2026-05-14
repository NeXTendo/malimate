package com.techohns.moneymate.data.repository

import com.techohns.moneymate.data.local.dao.ExpenseDao
import com.techohns.moneymate.data.local.entities.ExpenseEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao
) {
    fun getExpenses(userId: String): Flow<List<ExpenseEntity>> =
        expenseDao.getExpensesByUser(userId)

    fun getTotalSpent(userId: String): Flow<Double?> =
        expenseDao.getTotalSpent(userId)

    suspend fun addExpense(
        userId: String,
        amount: Double,
        category: String,
        note: String,
        paymentMethod: String,
        date: Long
    ) {
        val expense = ExpenseEntity(
            id = UUID.randomUUID().toString(),
            userId = userId,
            amount = amount,
            category = category,
            note = note,
            paymentMethod = paymentMethod,
            date = date
        )
        expenseDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: ExpenseEntity) {
        expenseDao.deleteExpense(expense)
    }
}
