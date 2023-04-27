package com.nikitakrapo.monkeybusiness.finance.account

import com.nikitakrapo.monkeybusiness.finance.account.remote.BankAccountsApi
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.BankAccountDto
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.BankAccountOpeningRequest
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.toDomainModel
import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

internal class BankAccountsRepositoryImpl constructor(
    private val bankAccountsApi: BankAccountsApi,
) : BankAccountsRepository {

    private val bankAccountsFlow = MutableSharedFlow<List<BankAccount>>()

    override suspend fun openBankAccount(currency: Currency): Result<Unit> {
        // FIXME: handle errors
        val request = BankAccountOpeningRequest(
            currencyCode = currency.code,
        )
        return runCatching {
            bankAccountsApi.createAccount(request)
        }
    }

    override fun getBankAccounts(): Flow<List<BankAccount>> = bankAccountsFlow

    override suspend fun updateBankAccounts() {
        val accounts = fetchBankAccounts()
        bankAccountsFlow.emit(accounts)
    }

    private suspend fun fetchBankAccounts() =
        bankAccountsApi.getAccountList()
            .accounts
            .map(BankAccountDto::toDomainModel)
}