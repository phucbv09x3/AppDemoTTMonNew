package com.monstar_lab_lifetime.appdemottmon.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.monstar_lab_lifetime.appdemottmon.adapter.MesAdapter
import com.monstar_lab_lifetime.appdemottmon.`object`.MesData
import com.monstar_lab_lifetime.appdemottmon.R
import kotlinx.android.synthetic.main.fragment_message.view.*


class MessageFragment : Fragment() {


    var mAdapter = MesAdapter()
    var mList = mutableListOf<MesData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rcy_mes.layoutManager = LinearLayoutManager(activity)
        view.rcy_mes.setHasFixedSize(true)
        view.rcy_mes.adapter = mAdapter
        mAdapter.setList(mList)
    }


}
