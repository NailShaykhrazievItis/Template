package com.itis.template

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_groups.view.*

class GroupsFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        activity!!.title = getString(R.string.menu_groups)
        return inflater.inflate(R.layout.fragment_groups, container, false).apply {
            tv_groups_title.text = getString(R.string.menu_groups)
            btn_groups_new.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.nav_host_fragment, GroupCreationFragment())
                        ?.addToBackStack(null)
                        ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ?.commit()
            }
        }
    }
}