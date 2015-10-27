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
                            (var context: Context, var list: List<Item>): BaseAdapter(){

        private var layoutInflater: LayoutInflater?
        private var ItemList: List<Item>

        init{
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            ItemList = list
        }

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
            var view = convertView

            val item = getItem(position) as Item

            //speeding up
            if (null == view) {
                view = layoutInflater!!.inflate(R.layout.list_view, null)
            }

            //set list view
            val name: TextView?
            name = view!!.findViewById(R.id.name) as TextView
            name.text = item.name

            val no: TextView?
            no = view!!.findViewById(R.id.no) as TextView
            no.text = item.no.toString()

            return view
        }
    }

}
