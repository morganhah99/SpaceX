package com.example.spacex.ui.compose.auth

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.common.nav.NavRoutes
import com.google.firebase.auth.FirebaseAuth

fun loginWithEmailAndPassword(
    email: String,
    password: String,
    auth: FirebaseAuth,
    context: Context,
    navController: NavHostController
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                navController.navigate(NavRoutes.Home.route)
            }
            else {
                Toast.makeText(
                    context,
                    task.exception?.message ?: "Login failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}


fun checkAuthStatus(auth: FirebaseAuth, navController: NavHostController) {
    val currentUser = auth.currentUser
    if (currentUser != null) {
        navController.navigate(NavRoutes.Home.route) {
            popUpTo(NavRoutes.Home.route) { inclusive = true }
        }
    }
}

fun signUpWithEmailAndPassword(
    email: String,
    password: String,
    confirmPassword: String,
    auth: FirebaseAuth,
    context: Context,
    onSignUpSuccess: () -> Unit
) {
    if (password != confirmPassword) {
        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
        return
    }

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                onSignUpSuccess()
            } else {
                Toast.makeText(context, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
}



fun signOut(auth: FirebaseAuth, navController: NavHostController) {
    auth.signOut()
    navController.navigate(NavRoutes.ROUTE_AUTH)
}