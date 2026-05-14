package com.techohns.moneymate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techohns.moneymate.data.local.dao.ExpenseDao
import com.techohns.moneymate.data.local.entities.*

@Database(
    entities = [
        UserEntity::class,
        ExpenseEntity::class,
        GroupEntity::class,
        GroupMemberEntity::class,
        BillSplitEntity::class,
        DebtEntity::class,
        ContributionEntity::class,
        ProductEntity::class,
        SaleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MaliMateDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}
