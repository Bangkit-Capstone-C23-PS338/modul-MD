package com.example.promosee.view.splash
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.R
import com.example.promosee.model.remote.retrofit.ApiConfig
import com.example.promosee.view.BoardingActivity
import com.example.promosee.view.company.mainCompany.MainActivityCompany
import com.example.promosee.view.ViewModelFactory

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        splashViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        )[SplashViewModel::class.java]
    }

    private fun setupAction() {

        splashViewModel.getUser().observe(this){user ->
            ApiConfig.TOKEN = user.access_token
            // pengecekan token dan authorisasi
            if(user.access_token.isNotEmpty()){
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this@SplashActivity, MainActivityCompany::class.java)
                    startActivity(intent)
                    finish()
                }, DURATION)
            }else{
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this@SplashActivity, BoardingActivity::class.java)
                    startActivity(intent)
                    finish()
                }, DURATION)
            }
        }
    }

    companion object{
        const val DURATION = 2000L
    }
}