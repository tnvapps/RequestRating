package com.tnvapps.example.requestrating

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tnvapps.requestrating.fragments.EnjoyingDialogFragment
import com.tnvapps.requestrating.fragments.ReviewDialogFragment
import com.tnvapps.requestrating.interfaces.EnjoyingDialogFragmentListener
import com.tnvapps.requestrating.interfaces.ReviewDialogFragmentListener

class MainActivity : AppCompatActivity(), EnjoyingDialogFragmentListener, ReviewDialogFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        EnjoyingDialogFragment.start(supportFragmentManager, appName = "iFake")

    }

    override fun onDismissedEnjoyingDialogFragment(result: Int) {
        if (result == Activity.RESULT_OK) {
            ReviewDialogFragment.start(supportFragmentManager, appName = "iFake")
        } else {
            Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDismissedReviewDialogFragment(result: Int) {
        if (result == Activity.RESULT_OK) {
            Toast.makeText(this, "YES", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show()
        }
    }
}