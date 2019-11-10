package com.itis.template

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_group.view.*

class GroupFragment : Fragment() {
    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_SUBTITLE = "subtitle"
        private const val ARG_DESC = "desc"

        fun newInstance(title: String, subtitle: String, desc: String) = GroupFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_SUBTITLE, subtitle)
                putString(ARG_DESC, desc)
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        activity!!.title = getString(R.string.title_group)
        return inflater.inflate(R.layout.fragment_group, container, false).apply {
            tv_group_title.text = arguments?.getString(ARG_TITLE)
            tv_group_subtitle.text = arguments?.getString(ARG_SUBTITLE)
            tv_group_desc.text = arguments?.getString(ARG_DESC)
        }
    }
}