package com.example.examgos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room.databaseBuilder


class MainActivity : AppCompatActivity() {
    public var database: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        database = AppDatabase.getDatabase(context = this)
    }

    private fun init(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ListFragment())
            .setReorderingAllowed(true)
            .addToBackStack("ListFragment")
            .commit()
    }
}