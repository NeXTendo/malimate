package com.techohns.moneymate.di

import android.content.Context
import androidx.room.Room
import com.techohns.moneymate.data.local.MaliMateDatabase
import com.techohns.moneymate.data.remote.supabase.supabaseClient
import com.techohns.moneymate.data.local.dao.ExpenseDao
import io.github.jan.supabase.SupabaseClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MaliMateDatabase {
        return Room.databaseBuilder(
            context,
            MaliMateDatabase::class.java,
            "malimate.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient = supabaseClient

    @Provides
    fun provideExpenseDao(database: MaliMateDatabase): ExpenseDao {
        return database.expenseDao()
    }
}
