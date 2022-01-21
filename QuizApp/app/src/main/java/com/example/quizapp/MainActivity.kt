package com.example.quizapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectButton = findViewById<Button>(R.id.button2)
        val userName = findViewById<EditText>(R.id.editTextTextPersonName)
        val getContacts = registerForActivityResult(ActivityResultContracts.PickContact(),
            ActivityResultCallback{
            val list = listOf(ContactsContract.Contacts.DISPLAY_NAME).toTypedArray()
            val cursor = contentResolver.query(it, list, null, null, null)
            if (cursor != null){
                if(cursor.moveToFirst()){
                    userName.setText(cursor.getString(0))
                }
                cursor.close()
            }
        })

        selectButton.setOnClickListener {
            // Pass in the mime type you'd like to allow the user to select
            // as the input
            getContacts.launch(null)
        }
    }

    fun sendMessage(view: View) {
        // Do something in response to button
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}