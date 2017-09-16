package com.houxinwu.viewpagerpagetransformer

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        val ITEM_COUNT = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indicator.itemCount = ITEM_COUNT

        viewPager.adapter = object : PagerAdapter() {
            override fun instantiateItem(container: ViewGroup?, position: Int): Any {
                val parent = FrameLayout(this@MainActivity)
                val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
                layoutParams.gravity = Gravity.CENTER
                val image = ImageView(this@MainActivity)
                image.loadRes(R.drawable.bg_default_course)
                image.layoutParams = layoutParams
                parent.addView(image)
                container?.addView(parent)
                return parent
            }

            override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
                return view == `object`
            }

            override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
                if (`object` is View) {
                    container?.removeView(`object`)
                }
            }

            override fun getCount(): Int {
                return ITEM_COUNT
            }

        }

        viewPager.offscreenPageLimit = 3
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

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                indicator.setCurrent(position, positionOffset)
            }

            override fun onPageSelected(position: Int) {

            }
        })
    }
}
