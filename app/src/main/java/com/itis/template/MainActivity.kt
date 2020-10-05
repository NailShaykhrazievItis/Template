package com.itis.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LessonFragment.newInstance("1", "2").also {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, it, "TAG")
                .commit()
        }
//        Example of using android.R.id.content
//        Snackbar.make(findViewById(android.R.id.content), "HELLO", Snackbar.LENGTH_LONG).show()

//        Example of adding view to layout from code
//        LayoutInflater.from(this).also {
//            it.inflate(R.layout.test_view, container, true)
//        }
    }

}
