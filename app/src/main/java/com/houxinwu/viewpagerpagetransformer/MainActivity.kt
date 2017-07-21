package com.houxinwu.viewpagerpagetransformer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: MutableList<Fragment> = mutableListOf()
        for(it in 0..5) {
            list.add(ViewPagerItemFragment.newInstance())
        }

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return list[position]
            }

            override fun getCount(): Int {
                return list.size
            }
        }

        viewPager.offscreenPageLimit = 2
        viewPager.setPageTransformer(true) {
            page, position ->
            val MIN_SCALE = 0.9f

            val scale = when (position) {
                in -1..1 -> MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
                else -> MIN_SCALE
            }

            page.scaleX = scale
            page.scaleY = scale
        }
    }
}
