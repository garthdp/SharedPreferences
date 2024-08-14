package com.garth.sharedpreferences

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var age: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        name = findViewById(R.id.txtName)
        age = findViewById(R.id.txtAge)
    }

    // fetch the stored data in onResume() because this is what till be called when the app opens again
    override fun onResume(){
        super.onResume()
        //fetching the stored data from the sharedpreference
        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val s1 = sh.getString("name", "")
        val a = sh.getInt("age", 0)

        //setting the fetched data in the edit texts
        name.setText(s1)
        age.setText(a.toString())
    }

    //store the data in sharedpreference in the onPause() method
    //when the user closes the application onPause() will be called and data will be stored
    override fun onPause(){
        super.onPause()
        //creating a shared pref object with a file name "mySharedPref" in private mose
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        //write all the data entered by the user in sharedpreference and apply
        myEdit.putString("name", name.text.toString())
        myEdit.putInt("age", age.text.toString().toInt())
        myEdit.apply()
    }
}