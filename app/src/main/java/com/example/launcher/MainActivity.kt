package com.example.launcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        recyclerView.adapter = PackageAdapter(PackageManagerCompat.getInstalledApps(this)) {
            showIntent(it)
        }

        recyclerView.setHasFixedSize(true)

        goBtn.setOnClickListener {
            openWeb(editView.text.toString())
        }
    }

    private fun showIntent(packageInformation: PackageInformation) {

        val intent: Intent? = packageManager.getLaunchIntentForPackage(packageInformation.packageName)

        intent?.let {
            startActivity(intent)
        }
    }

    private fun openWeb(webUrl : String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
        startActivity(browserIntent)
    }
}