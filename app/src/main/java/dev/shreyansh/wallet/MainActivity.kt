package dev.shreyansh.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dev.shreyansh.wallet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.logoutButton.setOnClickListener {
            signOutFlow()
        }

    }

    private fun signOutFlow() {
        val intent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googlesSignInClient = GoogleSignIn.getClient(this,gso)
        googlesSignInClient.signOut()
        FirebaseAuth.getInstance().signOut();
    }
}