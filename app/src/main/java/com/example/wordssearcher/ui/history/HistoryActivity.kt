package com.example.wordssearcher.ui.history

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.wordssearcher.R
import com.example.wordssearcher.databinding.ActivityHistoryBinding
import com.example.wordssearcher.databinding.LoadingLayoutBinding
import com.example.wordssearcher.model.data.AppState
import com.example.wordssearcher.model.data.DataModel
import com.example.wordssearcher.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Observer


class HistoryActivity  : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var bindingLayout : LoadingLayoutBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        bindingLayout = LoadingLayoutBinding.inflate(layoutInflater)
        model.getData("", false)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    Toast.makeText(applicationContext,
                        getString(R.string.empty_server_response_on_success),
                        Toast.LENGTH_LONG).show()
                } else {
                    showViewSuccess()
                    adapter.setData(dataModel)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    bindingLayout.progressBarHorizontal.visibility = View.VISIBLE
                    bindingLayout.progressBarRound.visibility = View.GONE
                    bindingLayout.progressBarHorizontal.progress = appState.progress
                } else {
                    bindingLayout.progressBarHorizontal.visibility = View.GONE
                    bindingLayout.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
               Toast.makeText(applicationContext,appState.error.toString(),Toast.LENGTH_LONG)
            }
        }
    }


    private fun showViewSuccess() {
        binding.historyActivityRecyclerview.visibility = View.VISIBLE
        bindingLayout.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.historyActivityRecyclerview.visibility = View.GONE
        bindingLayout.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun showViewError() {
        binding.historyActivityRecyclerview.visibility = View.GONE
        bindingLayout.loadingFrameLayout.visibility = View.GONE
    }


}