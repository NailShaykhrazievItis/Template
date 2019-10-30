package com.itis.template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BlankFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.also {
            it.beginTransaction().apply {
                add(R.id.container, BlankFragment.newInstance(), "fdfd")
                commit()
            }
        }
    }

    override fun onFragmentInteraction(sum: Int) {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                replace(R.id.container, FirstFragment.newInstance(sum))
                addToBackStack(FirstFragment::class.java.name)
                commit()
            }
        }
    }
}
