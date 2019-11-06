package com.example.donation.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.donation.R
import com.example.donation.main.DonationApp
import com.example.donation.models.DonationModel

import kotlinx.android.synthetic.main.activity_donnate.*
import kotlinx.android.synthetic.main.content_donate.*

class Donate : AppCompatActivity() {

    lateinit var app: DonationApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donnate)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        app = this.application as DonationApp

        progressBar.max = 1000
        amountPicker.minValue = 1
        amountPicker.maxValue = 1000

        amountPicker.setOnValueChangedListener{ picker, oldVal, newVal ->
            paymentAmount.setText("$newVal")
        }

        var totalDonated = 0

        donateButton.setOnClickListener {
            val amount = if (paymentAmount.text.isNotEmpty())
                paymentAmount.text.toString().toInt() else amountPicker.value
            if(totalDonated >= progressBar.max)
                Toast.makeText(this,"Donate Amount Exceeded!", Toast.LENGTH_LONG).show()
            else {
                val paymentmethod = if(paymentMethod.checkedRadioButtonId == R.id.Direct) "Direct" else "Paypal"
                totalDonated += amount
                totalSoFar.text = "$$totalDonated"
                progressBar.progress = totalDonated
                app.donationsStore.create(DonationModel(paymentmethod = paymentmethod,amount = amount))
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_donate, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_report -> {
                startActivity(Intent(this, Report::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
