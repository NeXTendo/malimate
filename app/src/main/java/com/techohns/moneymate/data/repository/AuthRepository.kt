package com.techohns.moneymate.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val supabase: SupabaseClient
) {
    suspend fun register(email: String, password: String) {
        supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun login(email: String, password: String) {
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun logout() {
        supabase.auth.signOut()
    }

    fun currentUser() = supabase.auth.currentUserOrNull()

    fun sessionFlow() = supabase.auth.sessionStatus

    suspend fun sendPasswordReset(email: String) {
        supabase.auth.resetPasswordForEmail(email)
    }
}
