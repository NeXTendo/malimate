package com.techohns.moneymate.ui.screens.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techohns.moneymate.domain.model.ExpenseCategory
import com.techohns.moneymate.domain.model.PaymentMethod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseSheet(
    onDismiss: () -> Unit,
    onSaved: () -> Unit,
    viewModel: WalletViewModel
) {
    val addState by viewModel.addState.collectAsState()

    var amountText by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(ExpenseCategory.OTHER) }
    var selectedPayment by remember { mutableStateOf(PaymentMethod.CASH) }
    var categoryExpanded by remember { mutableStateOf(false) }
    var paymentExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(addState) {
        if (addState is AddExpenseState.Success) {
            viewModel.resetAddState()
            onSaved()
        }
    }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Add Expense",
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = amountText,
                onValueChange = { amountText = it },
                label = { Text("Amount (K)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Note (optional)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Category dropdown
            ExposedDropdownMenuBox(
                expanded = categoryExpanded,
                onExpandedChange = { categoryExpanded = it }
            ) {
                OutlinedTextField(
                    value = selectedCategory.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false }
                ) {
                    ExpenseCategory.entries.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.label) },
                            onClick = {
                                selectedCategory = category
                                categoryExpanded = false
                            }
                        )
                    }
                }
            }

            // Payment method dropdown
            ExposedDropdownMenuBox(
                expanded = paymentExpanded,
                onExpandedChange = { paymentExpanded = it }
            ) {
                OutlinedTextField(
                    value = selectedPayment.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Payment Method") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = paymentExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = paymentExpanded,
                    onDismissRequest = { paymentExpanded = false }
                ) {
                    PaymentMethod.entries.forEach { method ->
                        DropdownMenuItem(
                            text = { Text(method.label) },
                            onClick = {
                                selectedPayment = method
                                paymentExpanded = false
                            }
                        )
                    }
                }
            }

            if (addState is AddExpenseState.Error) {
                Text(
                    text = (addState as AddExpenseState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 13.sp
                )
            }

            Button(
                onClick = {
                    val amount = amountText.toDoubleOrNull() ?: return@Button
                    viewModel.addExpense(
                        amount = amount,
                        category = selectedCategory.label,
                        note = note,
                        paymentMethod = selectedPayment.name,
                        date = System.currentTimeMillis()
                    )
                },
                enabled = addState !is AddExpenseState.Loading && amountText.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (addState is AddExpenseState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Save Expense")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
