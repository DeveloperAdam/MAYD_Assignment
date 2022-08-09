package com.adam.mayd_assignment.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adam.mayd_assignment.R
import com.adam.mayd_assignment.data.Shortly
import com.adam.mayd_assignment.utils.SharePreferenceUtils
import com.adam.mayd_assignment.utils.ShortlyExtension.copyToClipboard


class ShortlyAdapter(
    arrayListOfUrls: ArrayList<Shortly>,
    private val context: Context
) : RecyclerView.Adapter<ShortlyAdapter.ViewHolder>() {

    var mArrayList = arrayListOfUrls

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var tvOriginalUrl = view.findViewById(R.id.tvOriginalUrl) as TextView
        private var tvShortUrl = view.findViewById(R.id.tvShortenUrl) as TextView
        private var ivDelete = view.findViewById(R.id.ivDelete) as ImageView
        private var btnCopy = view.findViewById(R.id.btnCopy) as Button

        fun bind(record: Shortly, index: Int) {
            tvOriginalUrl.text = record.originalLink
            tvShortUrl.text = record.fullShortLink

            ivDelete.setOnClickListener {
                mArrayList.remove(record)
                SharePreferenceUtils.savePreference(context = context, mArrayList)
                mArrayList = SharePreferenceUtils.readFromPreference(context = context)
                notifyItemChanged(index)
            }
            btnCopy.setOnClickListener {
                context.copyToClipboard(tvShortUrl.text.toString().trim())
                btnCopy.text = context.getString(R.string.copied)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mArrayList[position]
        holder.bind(record = item, index = position)
    }

    override fun getItemCount(): Int {
        return mArrayList.size
    }

    fun updateList(list: ArrayList<Shortly>) {
        mArrayList = list
        notifyDataSetChanged()

    }

}