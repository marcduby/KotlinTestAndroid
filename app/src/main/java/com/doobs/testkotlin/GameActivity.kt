package com.doobs.testkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class GameActivity : AppCompatActivity() {
    var started = false
    var number = 0
    var tries = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        fetchSavedInstanceData(savedInstanceState)
        buttonDoGuess.setEnabled(started)

    }

    fun start(view: View) {
        edit_number.setText("")
        started = true
        buttonDoGuess.setEnabled(true)
        text_status.text = getString(R.string.hint_guess, 1, 9)
        number = 1 + Math.floor(Math.random() * 9).toInt()
        tries = 0
    }

    fun guess(view: View) {
        if (edit_number.toString() == "") return
        tries++
        val guess = edit_number.text.toString().toInt()

        if (guess < number) {
            text_status.setText(R.string.status_too_low)
            edit_number.setText("")

        } else if (guess > number) {
            text_status.setText(R.string.status_too_high)
            edit_number.setText("")

        } else {
            text_status.setText(getString(R.string.status_correct, tries))
            started = false
            buttonDoGuess.setEnabled(false)
        }
    }

    private fun fetchSavedInstanceData(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            with (savedInstanceState) {
                started = getBoolean("started")
                number = getInt("number")
                tries = getInt("tries")
                text_status.text = getString("text_status")

            }
        }
    }

    private fun putSavedInstanceData(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                putBoolean("started", started)
                putInt("number", number)
                putInt("tries", tries)
                putString("text_status", text_status.text.toString())
            }
        }
    }


}
