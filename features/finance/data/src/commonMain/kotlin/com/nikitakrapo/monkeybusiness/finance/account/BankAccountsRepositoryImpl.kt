package com.nikitakrapo.monkeybusiness.finance.account

import com.nikitakrapo.monkeybusiness.finance.account.remote.BankAccountsApi
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.BankAccountOpeningRequest
import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class BankAccountsRepositoryImpl constructor(
    private val bankAccountsApi: BankAccountsApi,
) : BankAccountsRepository {

    override suspend fun openBankAccount(currency: Currency): Result<Unit> {
        // FIXME: handle errors
        val request = BankAccountOpeningRequest(
            currencyCode = currency.code,
        )
        return runCatching {
            bankAccountsApi.createAccount(request)
        }
    }

    override suspend fun getBankAccounts(): Flow<List<BankAccount>> {
        // FIXME: handle errors
        return flow {
            val res = bankAccountsApi.getAccountList().accounts.map {
                TODO()
            }
            emit(res)
        }
    }
}