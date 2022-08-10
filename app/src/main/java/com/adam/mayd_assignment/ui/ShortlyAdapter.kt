package com.adam.mayd_assignment.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.adam.mayd_assignment.R
import com.adam.mayd_assignment.data.Shortly
import com.adam.mayd_assignment.utils.SharePreferenceUtils
import com.adam.mayd_assignment.utils.ShortlyExtension.applyText
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

            if (record.isCopied)
            {
                btnCopy.applyText(context.getString(R.string.copied))
                btnCopy.setBackgroundResource(R.drawable.copied_btn_bg)
            }
            else
            {
                btnCopy.applyText(context.getString(R.string.copy))
                btnCopy.setBackgroundResource(R.drawable.default_btn_bg)
            }

            ivDelete.setOnClickListener {
                mArrayList.remove(record)
                SharePreferenceUtils.savePreference(context = context, mArrayList)
                mArrayList = SharePreferenceUtils.readFromPreference(context = context)
                notifyItemChanged(index)
            }
            btnCopy.setOnClickListener {

                if (!record.isCopied) {
                    record.isCopied = true
                    context.copyToClipboard(tvShortUrl.text.toString().trim())
                    restoreStates(item = record)
                }

            }
        }

        fun restoreStates(item : Shortly){
            for(url  in mArrayList)
            {
                url.isCopied = item.code == url.code
            }
            notifyDataSetChanged()
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