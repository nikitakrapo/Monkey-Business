package com.nikitakrapo.monkeybusiness.finances

import com.nikitakrapo.monkeybusiness.finance.TransactionsRepository

class FinancesDependencies(
    val transactionsRepository: TransactionsRepository,
    val transactionAddRouter: TransactionAddRouter,
)