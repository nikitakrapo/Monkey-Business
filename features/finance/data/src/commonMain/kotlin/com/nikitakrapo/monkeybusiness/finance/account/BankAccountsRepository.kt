package com.nikitakrapo.monkeybusiness.finance.account

import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.coroutines.flow.Flow

interface BankAccountsRepository {
    suspend fun openBankAccount(
        currency: Currency
    ): Result<Unit>

    fun getBankAccounts(): Flow<List<BankAccount>>

    suspend fun updateBankAccounts()
}