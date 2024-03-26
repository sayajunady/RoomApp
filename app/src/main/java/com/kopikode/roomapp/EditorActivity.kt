package com.kopikode.roomapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kopikode.roomapp.entity.AppDatabase
import com.kopikode.roomapp.entity.User

class EditorActivity : AppCompatActivity() {
    private lateinit var fullName: EditText
    private lateinit var email: EditText
    private lateinit var phone:EditText
    private lateinit var address: EditText
    private lateinit var btnSimpan:Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        fullName = findViewById(R.id.full_name)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        address = findViewById(R.id.address)

        btnSimpan = findViewById(R.id.btnSimpan)

        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if(intent!=null){
            var user = database.userDao().get(intent.getInt("id"))

            fullName.setText(user.fullName)
            email.setText(user.email)
            phone.setText(user.phone)
            address.setText(user.address)
        }

        btnSimpan.setOnClickListener( {
            if (fullName.text.length > 0 && email.text.length > 0 && phone.text.length > 0 ) {
                if(intent!=null){
                    //code edit data
                    database.userDao().update(
                        User(
                            intent.getInt("id",0),
                            fullName.text.toString(),
                            email.text.toString(),
                            phone.text.toString(),
                            address.text.toString()
                    ))
                } else {
                    //code tambah data
                    database.userDao().insertAll(
                        User(
                            null,
                            fullName.text.toString(),
                            email.text.toString(),
                            phone.text.toString(),
                            address.text.toString()
                        )
                    )
                }
                    finish()

            } else {
                Toast.makeText(applicationContext, "Silahkan isi data dengan benar",
                    Toast.LENGTH_SHORT).show()
            }
        })
    }
}
