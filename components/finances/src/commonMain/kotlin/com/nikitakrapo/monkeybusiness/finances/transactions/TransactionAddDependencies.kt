package com.nikitakrapo.monkeybusiness.finances.transactions

import com.nikitakrapo.monkeybusiness.finance.TransactionsRepository

class TransactionAddDependencies(
    val transactionsRepository: TransactionsRepository,
)