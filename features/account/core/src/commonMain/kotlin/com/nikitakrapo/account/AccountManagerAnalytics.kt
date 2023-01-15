package com.nikitakrapo.account

import com.nikitakrapo.analytics.AnalyticsManager

class AccountManagerAnalytics(
    private val analyticsManager: AnalyticsManager,
) {

    fun onLoginStarted(email: String) {
        analyticsManager.reportAttributedEvent(LOGIN_STARTED) {
            put(PARAM_EMAIL, email)
        }
    }

    fun onLoginSucceeded() {
        analyticsManager.reportEvent(LOGIN_SUCCEEDED)
    }

    fun onLoginFailed(error: String?) {
        analyticsManager.reportAttributedEvent(LOGIN_FAILED) {
            put(PARAM_ERROR, error ?: UNKNOWN_ERROR)
        }
    }

    fun onCreateAccountStarted(email: String) {
        analyticsManager.reportAttributedEvent(CREATE_ACCOUNT_STARTED) {
            put(PARAM_EMAIL, email)
        }
    }

    fun onCreateAccountSucceeded() {
        analyticsManager.reportEvent(CREATE_ACCOUNT_SUCCEEDED)
    }

    fun onCreateAccountFailed(error: String?) {
        analyticsManager.reportAttributedEvent(CREATE_ACCOUNT_FAILED) {
            put(PARAM_ERROR, error ?: UNKNOWN_ERROR)
        }
    }

    fun onLogoutStarted() {
        analyticsManager.reportEvent(LOGOUT)
    }

    fun onLogoutSucceeded() {
        analyticsManager.reportEvent(LOGOUT_SUCCEEDED)
    }

    fun onLogoutFailed(error: String?) {
        analyticsManager.reportAttributedEvent(LOGOUT_FAILED) {
            put(PARAM_ERROR, error ?: UNKNOWN_ERROR)
        }
    }

    private companion object {
        const val LOGIN_STARTED = "account_manager_login_started"
        const val LOGIN_SUCCEEDED = "account_manager_login_succeeded"
        const val LOGIN_FAILED = "account_manager_login_failed"

        const val CREATE_ACCOUNT_STARTED = "account_manager_create_account_started"
        const val CREATE_ACCOUNT_SUCCEEDED = "account_manager_create_account_succeeded"
        const val CREATE_ACCOUNT_FAILED = "account_manager_create_account_failed"

        const val LOGOUT = "account_manager_logout"
        const val LOGOUT_SUCCEEDED = "account_manager_logout_succeeded"
        const val LOGOUT_FAILED = "account_manager_logout_failed"

        const val PARAM_EMAIL = "email"
        const val PARAM_ERROR = "error"

        const val UNKNOWN_ERROR = "unknown"
    }
}
