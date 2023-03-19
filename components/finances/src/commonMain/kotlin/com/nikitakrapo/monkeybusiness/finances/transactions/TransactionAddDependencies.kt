package com.nikitakrapo.monkeybusiness.finances.transactions

import com.nikitakrapo.monkeybusiness.finance.transactions.TransactionsRepository

class TransactionAddDependencies(
    val transactionsRepository: TransactionsRepository,
)