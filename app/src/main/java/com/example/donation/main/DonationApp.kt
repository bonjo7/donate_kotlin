package com.example.donation.main

import android.app.Application
import android.util.Log
import com.example.donation.models.DonationMemStore
import com.example.donation.models.DonationStore

class DonationApp : Application() {

    lateinit var donationsStore: DonationStore

    override fun onCreate() {
        super.onCreate()
        donationsStore = DonationMemStore()
        Log.v("Donate","Donation App started")
    }
}