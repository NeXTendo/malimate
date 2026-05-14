package com.techohns.moneymate.ui.screens.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techohns.moneymate.data.local.entities.ExpenseEntity
import com.techohns.moneymate.data.repository.AuthRepository
import com.techohns.moneymate.data.repository.ExpenseRepository
import com.techohns.moneymate.domain.model.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val userId get() = authRepository.currentUser()?.id ?: ""

    val expenses = expenseRepository.getExpenses(userId)
        .map { list -> list.map { it.toDomain() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalSpent = expenseRepository.getTotalSpent(userId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    private val _addState = MutableStateFlow<AddExpenseState>(AddExpenseState.Idle)
    val addState: StateFlow<AddExpenseState> = _addState

    fun addExpense(
        amount: Double,
        category: String,
        note: String,
        paymentMethod: String,
        date: Long
    ) {
        viewModelScope.launch {
            _addState.value = AddExpenseState.Loading
            try {
                expenseRepository.addExpense(userId, amount, category, note, paymentMethod, date)
                _addState.value = AddExpenseState.Success
            } catch (e: Exception) {
                _addState.value = AddExpenseState.Error(e.message ?: "Failed to add expense")
            }
        }
    }

    fun deleteExpense(expense: ExpenseEntity) {
        viewModelScope.launch {
            expenseRepository.deleteExpense(expense)
        }
    }

    fun resetAddState() {
        _addState.value = AddExpenseState.Idle
    }
}

sealed class AddExpenseState {
    object Idle : AddExpenseState()
    object Loading : AddExpenseState()
    object Success : AddExpenseState()
    data class Error(val message: String) : AddExpenseState()
}
