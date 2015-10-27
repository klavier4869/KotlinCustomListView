package com.klavier.kotlincustomlistview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import java.util.*

class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListView()
    }

    private fun setListView(){
        var ItemList = ArrayList<Item>()

        //set list
        for(i in 1..10)
            ItemList.add(Item("name", i))

        var adapter = CustomListView(this, ItemList)
        var listview: ListView = findViewById(R.id.listview) as ListView
        listview.adapter = adapter

    }

    private inner class Item(name: String, no: Int){
        var name = name
        var no = no
    }

    private inner class CustomListView
                            (var context: Context, var ItemList: List<Item>): BaseAdapter(){

        private var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return ItemList.size()
        }

        override fun getItem(position: Int): Any {
            return ItemList.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView

            val item = getItem(position) as Item

            //speeding up
            if (null == convertView) {
                convertView = layoutInflater!!.inflate(R.layout.list_view, null)
            }

            //set list view
            val doName: TextView
            doName = convertView!!.findViewById(R.id.name) as TextView
            doName.text = item.name

            val totalNo: TextView
            totalNo = convertView.findViewById(R.id.no) as TextView
            totalNo.text = java.lang.String.valueOf(item.no)

            return convertView
        }
    }

}
