package edu.washington.alkwan.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceFragment
import android.support.v7.preference.PreferenceFragmentCompat
import android.text.InputType

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //the FM is the guy who moves fragments around
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, SettingsFragment())
            .commit()
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(p0: Bundle?, p1: String?) {
            addPreferencesFromResource(R.xml.settings)
        }
    }
}

