package andreylitvintsev.github.com.preferencefragment

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startExperimentsWithPreferences()
    }

    private fun startExperimentsWithPreferences() {
        getPreferences(Context.MODE_PRIVATE).edit().putString("getPreferences", "myValue").apply()
        getSharedPreferences("Global", Context.MODE_PRIVATE).edit().putString("getPreferences2", "myValue2").apply()
        getSharedPreferences("MainActivity", Context.MODE_PRIVATE).edit().putString("getPreferences3", "myValue3").apply()
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("getPreferences4", "myValue4").apply()
        PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putString("getPreferences5", "myValue4").apply()
    }

}
