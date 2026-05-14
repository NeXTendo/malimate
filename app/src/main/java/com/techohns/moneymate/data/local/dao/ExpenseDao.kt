package com.techohns.moneymate.data.local.dao

import androidx.room.*
import com.techohns.moneymate.data.local.entities.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity)

    @Query("SELECT * FROM expenses WHERE userId = :userId ORDER BY date DESC")
    fun getExpensesByUser(userId: String): Flow<List<ExpenseEntity>>

    @Query("SELECT SUM(amount) FROM expenses WHERE userId = :userId")
    fun getTotalSpent(userId: String): Flow<Double?>

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity)
}
