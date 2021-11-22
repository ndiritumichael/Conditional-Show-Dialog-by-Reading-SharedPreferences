package com.example.showdialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    lateinit var sharedPrefs: SharedPrefs
    lateinit var resetPrefsButton: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPrefs = SharedPrefs(this)
        resetPrefsButton = findViewById(R.id.reset)

        val showDialog = sharedPrefs.shouldShowDialog()

        if (showDialog) {

            showDialog()
        } else {

            resetPrefsButton.apply {
                visibility = View.VISIBLE
                this.setOnClickListener {

                    sharedPrefs.showdialog(true)
                    Toast.makeText(this@MainActivity, "Dialog will Be shown Next Time",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("SHOW DIALOG AGAIN?")
        builder.setMessage("Select yes if you want to see this dialog each time you launch the app or no if you want to never see it again")
        builder.setIcon(R.drawable.ic_alert)
        builder.setPositiveButton("Show Dialog") { dialogInterface: DialogInterface, i: Int ->

            Toast.makeText(this, "You will be shown the Dialog next time", Toast.LENGTH_LONG).show()
            dialogInterface.dismiss()
        }

        builder.setNegativeButton("Dont Show Again") { dialogInterface, i ->
            Toast.makeText(this, "You will be not be shown the Dialog again", Toast.LENGTH_LONG).show()
            dialogInterface.dismiss()
            sharedPrefs.showdialog(false)
        }

        builder.create()
        builder.show()
    }
}
