package com.nikitakrapo.monkeybusiness.finance.account

import com.nikitakrapo.monkeybusiness.finance.models.BriefAccountInfo
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

@Deprecated("stub impl")
class FakeBankAccountsRepositoryImpl : BankAccountsRepository {
    override suspend fun openBankAccount(currency: Currency): Result<Unit> {
        delay(1500)
        return if (Random.nextBoolean()) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Error while opening account"))
        }
    }

    override suspend fun getBankAccounts(): Flow<BriefAccountInfo> {
        TODO("Not yet implemented")
    }
}