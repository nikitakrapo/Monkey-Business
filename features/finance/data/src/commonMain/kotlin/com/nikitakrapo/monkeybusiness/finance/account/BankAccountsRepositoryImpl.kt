package com.nikitakrapo.monkeybusiness.finance.account

import com.nikitakrapo.monkeybusiness.finance.account.remote.BankAccountsApi
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.BankAccountDto
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.BankAccountOpeningRequest
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.BankAccountsResponse
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.toDomainModel
import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import io.github.aakira.napier.Napier
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
        return bankAccountsApi.createAccount(request)
    }

    override fun getBankAccounts(): Flow<List<BankAccount>> = bankAccountsFlow

    override suspend fun updateBankAccounts() {
        val accounts = getBankAccountsFromApi()
        accounts.onSuccess { bankAccountsFlow.emit(it) }
    }

    override suspend fun fetchBankAccounts(): Result<List<BankAccount>> {
        return getBankAccountsFromApi()
    }

    private suspend fun getBankAccountsFromApi() =
        bankAccountsApi.getAccountList()
            .onFailure { Napier.e("Error fetching accounts", it) }
            .map(BankAccountsResponse::accounts)
            .map { accountList -> accountList.map(BankAccountDto::toDomainModel) }
}