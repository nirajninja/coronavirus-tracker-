package com.example.niraj_loser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_list.view.*


class StateAdapter(val list:List<StatewiseItem>):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
 val view=convertView?:LayoutInflater.from(parent.context)
     .inflate(R.layout.item_list,parent,false)
        val item=list[position]
        view.confirmedTv.apply {
            text = Spannable_delta(
                "${item.confirmed}\n ↑ ${item.deltaconfirmed ?: "0"}",
                "#D757DC",
                item.confirmed?.length ?: 0
            )
        }
        view.activeTv.text = Spannable_delta(
            "${item.active}\n ↑ ${item.deltaactive ?: "0"}",
            "#0091EA",
            item.confirmed?.length ?: 0
        )
        view.recoveredTv.text = Spannable_delta(
            "${item.recovered}\n ↑ ${item.deltarecovered ?: "0"}",
            "#13DCC0",
            item.recovered?.length ?: 0
        )
        view.deceasedTv.text = Spannable_delta(
            "${item.deaths}\n ↑ ${item.deltadeaths ?: "0"}",
            "#AEEA00",
            item.deaths?.length ?: 0
        )
        view.stateTv.text = item.state
        return view
    }

    override fun getItem(position: Int)=list[position]
    override fun getItemId(position: Int)=position.toLong()
    override fun getCount()=list.size
}