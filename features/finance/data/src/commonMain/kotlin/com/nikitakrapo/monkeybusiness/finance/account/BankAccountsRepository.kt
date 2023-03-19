package com.nikitakrapo.monkeybusiness.finance.account

import com.nikitakrapo.monkeybusiness.finance.models.BriefAccountInfo
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.coroutines.flow.Flow

interface BankAccountsRepository {
    suspend fun openBankAccount(
        currency: Currency
    ): Result<Unit>

    suspend fun getBankAccounts(): Flow<BriefAccountInfo>
}