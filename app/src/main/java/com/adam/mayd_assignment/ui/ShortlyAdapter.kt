package com.adam.mayd_assignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adam.mayd_assignment.R
import com.adam.mayd_assignment.data.ShortlyDataModel

class ShortlyAdapter(
   private val arrayListOfUrls: ArrayList<ShortlyDataModel>,
    private val context: ShortlyActivity
) : RecyclerView.Adapter<ShortlyAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return arrayListOfUrls.size
    }

}