package com.monstar_lab_lifetime.appdemottmon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.monstar_lab_lifetime.appdemottmon.model.FeedData
import com.monstar_lab_lifetime.appdemottmon.Interface.OnItemClick
import com.monstar_lab_lifetime.appdemottmon.model.MesData
import com.monstar_lab_lifetime.appdemottmon.R
import com.monstar_lab_lifetime.appdemottmon.database.AccountDatabase
import com.monstar_lab_lifetime.appdemottmon.database.Messenger
import com.monstar_lab_lifetime.appdemottmon.fragment.FeedFragment
import com.monstar_lab_lifetime.appdemottmon.fragment.MessageFragment
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContentActivity : AppCompatActivity(), View.OnClickListener, OnItemClick,CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    private val feedFragment = FeedFragment()

    private var fragmentManagerr = supportFragmentManager
    private var lisFragment = arrayListOf(
        FeedFragment(),
        MessageFragment()
    )
    private val messageFragment =
        MessageFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val fragmentTransaction: FragmentTransaction = fragmentManagerr.beginTransaction()
        lisFragment.forEachIndexed { index, fragment ->
            fragmentTransaction.add(R.id.fr_content, fragment)
            fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.show(lisFragment[0])
        fragmentTransaction.commit()

        imgbtn_home.setImageResource(R.drawable.home_black)
        imgbtn_home.setOnClickListener(this)
        imgbtn_mes.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.imgbtn_home -> {
                imgbtn_home.setImageResource(R.drawable.home_black)
                imgbtn_mes.setImageResource(R.drawable.ic_group_mes)

                showFrag(lisFragment[0])

            }
            R.id.imgbtn_mes -> {
                imgbtn_home.setImageResource(R.drawable.ic_group_7)
                imgbtn_mes.setImageResource(R.drawable.mes_black)

                showFrag(lisFragment[1])
            }
        }
    }


    private val fragmentManager: FragmentManager = supportFragmentManager
    private fun showFrag(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        if (fragment.isAdded) {
            fragmentTransaction.show(fragment)
            lisFragment.forEach {
                if (it != fragment) fragmentTransaction.hide(it)
            }
        } else {
            fragmentTransaction.add(R.id.fr_content, fragment)
        }
        fragmentTransaction.commit()
    }

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
        var mAccountDatabase: AccountDatabase?=null
        mAccountDatabase= AccountDatabase.getDatabase(this)
        var mNumber: Int = -1
        when {
            lisFragment[0].isVisible -> {
                imgbtn_home.setImageResource(R.drawable.ic_group_7)
                imgbtn_mes.setImageResource(R.drawable.mes_black)
                showFrag(lisFragment[1])
                (lisFragment[1] as? MessageFragment)?.let {
                    it.mList.forEachIndexed { index, mesData ->
                        if (it.mList[index].name.equals(feedData.mName)) {
                            mNumber = index
                        }
                    }
                    if (mNumber != -1) {
                        it.mList.removeAt(mNumber)
                    }
                    it.mList.add(
                        0,
                        MesData(
                            feedData.mName,
                            feedData.mImageProfile
                        )
                    )
                   // var m=mAccountDatabase.accountDAO().getMes()
                   // var n= mAccountDatabase.accountDAO().findInboxByName(feedData.mName)
                    //it.mList.add(MesData(n?.name,n?.image))
                    it.mAdapter?.setList(it.mList)
                   launch { mAccountDatabase.accountDAO().insertInbox(Messenger(image = feedData.mImageProfile,name = feedData.mName)) }
                }
            }
        }
    }
}
