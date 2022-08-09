package com.adam.mayd_assignment.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adam.mayd_assignment.R
import com.adam.mayd_assignment.data.Shortly
import com.adam.mayd_assignment.mvvm.ShortlyViewModel
import com.adam.mayd_assignment.utils.DataState
import com.adam.mayd_assignment.utils.SharePreferenceUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShortlyActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: ShortlyViewModel
    private lateinit var etURL: EditText
    private lateinit var btnShortenUrl: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutHistory: ConstraintLayout
    private lateinit var layoutIntroduction: ConstraintLayout
    private lateinit var progressBar: ProgressBar

    private lateinit var adapter: ShortlyAdapter

    private var listOfShortUrls: ArrayList<Shortly> = arrayListOf()

    private var urlToBeShorten = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shortly)

        viewModel = ViewModelProvider(this)[ShortlyViewModel::class.java]
        bindViews()
        readFromDB()
        subscribeObserver()
    }

    private fun bindViews() {
        etURL = findViewById(R.id.etURL)
        btnShortenUrl = findViewById(R.id.btnShortenUrl)
        recyclerView = findViewById(R.id.rvLinkHistory)
        layoutHistory = findViewById(R.id.layoutHistory)
        layoutIntroduction = findViewById(R.id.layoutIntroduction)
        progressBar = findViewById(R.id.progressBar)

        //RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ShortlyAdapter(listOfShortUrls,this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        clickListeners()
    }

    private fun subscribeObserver() {
        viewModel.liveData.observe(this) { dataSate ->
            when (dataSate) {
                DataState.Loading -> {
                    println("Loading")
                    progressBar.visibility = View.VISIBLE
                }
                is DataState.Success -> {
                    progressBar.visibility = View.GONE
                    etURL.text.clear()
                    dataSate.data?.let {

                        if (dataSate.data.result != null) {
                            //Save New Shorted URL
                            saveInDB(dataSate.data.result ?: Shortly())
                        }

                        //Read saved Shorted URL's
                        readFromDB()
                    }
                }
                is DataState.Error -> {
                    progressBar.visibility = View.GONE
                    etURL.text.clear()
                    println(dataSate.message)
                }
                else -> {
                    progressBar.visibility = View.GONE
                    etURL.text.clear()
                    println("Some Error")
                }
            }
        }
    }

    private fun readFromDB() {
        listOfShortUrls = SharePreferenceUtils.readFromPreference(this)
        if (listOfShortUrls.size > 0) {

            layoutIntroduction.visibility = View.GONE
            layoutHistory.visibility = View.VISIBLE

        } else {
            layoutHistory.visibility = View.GONE
            layoutIntroduction.visibility = View.VISIBLE
        }

        adapter.updateList(listOfShortUrls)
    }

    private fun saveInDB(data: Shortly) {
        if (!data.code.isNullOrBlank())
            listOfShortUrls.add(data)

        if (listOfShortUrls.size > 0 && !isUrlAlreadyShorted(data.code)) {
            SharePreferenceUtils.savePreference(this, listOfShortUrls)
        }

    }

    private fun isUrlAlreadyShorted(mCode: String?): Boolean {
        var status = false

        val listOfUrls = SharePreferenceUtils.readFromPreference(this)
        if (listOfUrls.size > 0)

            for (url in listOfUrls)
                status = url.code == mCode

        return status
    }


    private fun clickListeners() {

        btnShortenUrl.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.btnShortenUrl -> {

                if (validate()) {
                    getShortedUrl()
                }
            }
        }
    }

    private fun getShortedUrl() {
        viewModel.getShortUrl(url = urlToBeShorten)
    }

    private fun validate(): Boolean {

        return if (etURL.text.isEmpty()) {
            applyAddLinkBackground()
            false

        } else {
            urlToBeShorten = etURL.text.toString().trim()
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