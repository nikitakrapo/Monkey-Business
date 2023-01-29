package com.nikitakrapo.monkeybusiness.network.auth

sealed class ClientAuth {

    object None : ClientAuth()

    class Bearer(val bearerTokensProvider: BearerTokensProvider) : ClientAuth()
}
