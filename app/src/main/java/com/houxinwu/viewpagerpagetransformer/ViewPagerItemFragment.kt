package com.houxinwu.viewpagerpagetransformer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_view_pager_item.*

/**
 * Created by houxinwu on 2017/7/21.
 */
class ViewPagerItemFragment : Fragment() {
    companion object {
        fun newInstance(): ViewPagerItemFragment {
            val args = Bundle()

            val fragment = ViewPagerItemFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_view_pager_item, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        iv_view_pager_item.loadRes(R.drawable.bg_default_course)
    }
}