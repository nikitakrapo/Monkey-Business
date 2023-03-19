package com.nikitakrapo.monkeybusiness.finance.account.remote

import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.AccountListResponse
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.CreateAccountRequest
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.CreateAccountResponse

interface BankAccountsApi {
    suspend fun createAccount(request: CreateAccountRequest): CreateAccountResponse
    suspend fun getAccountList(): AccountListResponse
}