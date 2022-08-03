package com.adam.mayd_assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adam.mayd_assignment.R
import com.adam.mayd_assignment.adapters.ShortlyAdapter
import com.adam.mayd_assignment.data.ShortlyDataModel

class ShortlyActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etURL: EditText
    private lateinit var btnShortenUrl: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutHistory: LinearLayout
    private lateinit var layoutIntroduction: LinearLayout

    private lateinit var adapter: ShortlyAdapter

    var arrayListOfUrls: ArrayList<ShortlyDataModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shortly)

        bindViews()
    }

    private fun bindViews() {

        etURL = findViewById(R.id.etURL)
        btnShortenUrl = findViewById(R.id.btnShortenUrl)
        recyclerView = findViewById(R.id.rvLinkHistory)
        layoutHistory = findViewById(R.id.layoutHistory)
        layoutIntroduction = findViewById(R.id.layoutIntroduction)

        //RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = ShortlyAdapter(arrayListOfUrls, this)
        clickListeners()
    }

    private fun clickListeners() {

        btnShortenUrl.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.btnShortenUrl -> {
                validate()
            }
        }
    }

    private fun validate(): Boolean {

        return if (etURL.text.isEmpty()) {
            applyAddLinkBackground()
            false

        } else {
            applyDefaultBackground()
            true
        }
    }

    private fun applyDefaultBackground() {
        etURL.hint = getString(R.string.shorten_a_link_here)
        etURL.background = ContextCompat.getDrawable(this, R.drawable.edittext_default_bg)
        etURL.setHintTextColor(ContextCompat.getColor(this, R.color.hint_color))
    }

    private fun applyAddLinkBackground() {

        etURL.hint = getString(R.string.error_text)
        etURL.background = ContextCompat.getDrawable(this, R.drawable.edittext_error_bg)
        etURL.setHintTextColor(ContextCompat.getColor(this, R.color.shortly_error_color))
    }
}