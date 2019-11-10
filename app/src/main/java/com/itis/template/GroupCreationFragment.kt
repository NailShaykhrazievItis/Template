package com.itis.template

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_group_creation.view.*

class GroupCreationFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        activity!!.title = getString(R.string.title_group_creation)
        return inflater.inflate(
                R.layout.fragment_group_creation, container, false
        ).apply {
            btn_group_creation_create.setOnClickListener {
                val fragment = GroupFragment.newInstance(
                        et_group_creation_title.text.toString(),
                        et_group_creation_subtitle.text.toString(),
                        et_group_creation_desc.text.toString()
                )
                activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.nav_host_fragment, fragment)
                        ?.addToBackStack(null)
                        ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        ?.commit()
            }
        }
    }
}