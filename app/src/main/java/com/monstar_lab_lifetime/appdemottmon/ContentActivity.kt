package com.monstar_lab_lifetime.appdemottmon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity(), View.OnClickListener, OnItemClick {
    private val feedFragment = FeedFragment()

    //private var lisFragment = arrayListOf(FeedFragment(), MessageFragment())
    private val messageFragment = MessageFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        //showHome()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.add(
            R.id.fr_content,
            feedFragment,
            "home"
        ).commit()

        //showFrag(lisFragment[0])
        imgbtn_home.setImageResource(R.drawable.home_black)
        imgbtn_home.setOnClickListener(this)
        imgbtn_mes.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgbtn_home -> {
                showHome()
                //showFrag(lisFragment[0])

            }
            R.id.imgbtn_mes -> {
                showMes()

                //showFrag(lisFragment[1])
            }
        }
    }


//    private val fragmentManager: FragmentManager = supportFragmentManager
//    private fun showFrag(fragment: Fragment) {
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//        if (fragment.isAdded) {
//            fragmentTransaction.show(fragment)
//            lisFragment.forEach {
//                if (it != fragment) fragmentTransaction.hide(it)
//            }
//        } else {
//            fragmentTransaction.replace(R.id.fr_content, fragment)
//        }
//        fragmentTransaction.commit()
//    }

    private fun showHome() {
        val fragmentManager = supportFragmentManager.beginTransaction()
        imgbtn_home.setImageResource(R.drawable.home_black)
        imgbtn_mes.setImageResource(R.drawable.ic_group_mes)
        if (feedFragment.isAdded) {
            fragmentManager.show(feedFragment)
        } else {
            fragmentManager.add(
                R.id.fr_content,
                feedFragment,
                "frgHome"
            )
        }
        fragmentManager.hide(messageFragment)
        fragmentManager.commit()
    }

    private fun showMes() {
        val fragmentManager = supportFragmentManager.beginTransaction()
        imgbtn_home.setImageResource(R.drawable.ic_group_7)
        imgbtn_mes.setImageResource(R.drawable.mes_black)
        if (messageFragment.isAdded) {
            fragmentManager.show(messageFragment)

        } else {
            fragmentManager.add(
                R.id.fr_content,
                messageFragment,
                "ok"
            )
        }
        fragmentManager.hide(feedFragment)
        fragmentManager.commit()
    }

    override fun onClicks(feedData: FeedData, position: Int) {
//        showMes()
//
//
//        messageFragment.mList.add(0, MesData(feedData.mName, feedData.mImageProfile,position))
//        messageFragment.mAdapter.setList(messageFragment.mList)
        var num =-1
        when {

            feedFragment.isVisible -> {
                showMes()
                (messageFragment as? MessageFragment)?.let {
//                    it.mList.forEachIndexed { index, mesData ->
//                        if (it.mList[index].id==position){
//                            num=index
//                        }
//                        if (num!=-1){
//                            it.mList.removeAt(num)
//                        }
                        it.mList.add(0,MesData(feedData.mName, feedData.mImageProfile,position))
                        it.mAdapter?.setList(it.mList)
                    }

            }
        }




    }


}
