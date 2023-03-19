package com.nikitakrapo.monkeybusiness.finance.account

import com.nikitakrapo.monkeybusiness.finance.models.BriefAccountInfo
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.coroutines.flow.Flow

@Deprecated("stub impl")
class FakeBankAccountsRepositoryImpl : BankAccountsRepository {
    override suspend fun openBankAccount(currency: Currency): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getBankAccounts(): Flow<BriefAccountInfo> {
        TODO("Not yet implemented")
    }
}