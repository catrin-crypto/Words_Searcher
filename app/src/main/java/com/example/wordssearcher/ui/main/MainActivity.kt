package com.example.wordssearcher.ui.main

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.model.data.AppState
import com.example.model.data.DataModel
import com.example.wordssearcher.R
import com.example.wordssearcher.databinding.ActivityMainBinding
import com.example.wordssearcher.ui.base.BaseActivity
import com.example.wordssearcher.ui.history.HistoryActivity
import com.example.wordssearcher.ui.main.adapter.MainAdapter
import org.koin.android.scope.currentScope
import kotlin.properties.Delegates

private const val SLIDE_RIGHT_DURATION = 2000L
private const val COUNTDOWN_DURATION = 2000L
private const val COUNTDOWN_INTERVAL = 1000L
private const val BLUR_RAD_X = 30f
private const val BLUR_RAD_Y = 0f
const val GIFCAT =
    "https://upload.wikimedia.org/wikipedia/ru/archive/6/6b/20210505175821%21NyanCat.gif"

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainActivityViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSplashScreen()
        initViewModel()
        initViews()
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    model.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

//        binding.gifImage.load(GIFCAT){
//            crossfade(1500)
//            transformations(BlurTransformation(this@MainActivity, 5f, 7f),
//                RoundedCornersTransformation(10f))
//        }
        val reguest = ImageRequest.Builder(this)
            .data(GIFCAT)
            .crossfade(1500)
            .target(binding.gifImage)
            .build()
        val imageLoader = ImageLoader.Builder(this)
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(this@MainActivity))
                } else {
                    add(GifDecoder())
                }
            }.build()

        imageLoader.enqueue(reguest)

        binding.gifImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                it.setRenderEffect(RenderEffect.createBlurEffect(BLUR_RAD_X, BLUR_RAD_Y, Shader.TileMode.DECAL))
            }
        }

        VetoDelegate.vetoValue = 5
        println("CCC ${VetoDelegate.vetoValue}")

        VetoDelegate.vetoValue = 32
        println("CCC ${VetoDelegate.vetoValue}")

        VetoDelegate.vetoValue = 9
        println("CCC ${VetoDelegate.vetoValue}")

        VetoDelegate.vetoValue = 90
        println("CCC ${VetoDelegate.vetoValue}")

        VetoDelegate.vetoValue = 1
        println("CCC ${VetoDelegate.vetoValue}")

        VetoDelegate.vetoValue = 22
        println("CCC ${VetoDelegate.vetoValue}")

    }

    private fun setSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setSplashScreenAnimation()
        }
        setSplashScreenDuration()
    }

    @RequiresApi(31)
    private fun setSplashScreenAnimation() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideRight = ObjectAnimator.ofFloat(
                splashScreenView, View.TRANSLATION_X, 0f,
                splashScreenView.height.toFloat()
            ).apply {
                interpolator = AnticipateInterpolator()
                duration = SLIDE_RIGHT_DURATION
                doOnEnd { splashScreenView.remove() }

            }.start()
        }
    }

    private fun setSplashScreenDuration() {
        var isHideSplashScreen = false

        object : CountDownTimer(COUNTDOWN_DURATION, COUNTDOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                isHideSplashScreen = true
            }
        }.start()

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isHideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    adapter.setData(dataModel)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress!!
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.toString())
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainActivityViewModel by currentScope.inject()
        model = viewModel
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}

object VetoDelegate {
    var vetoValue by Delegates.vetoable(22) { property, oldValue, newValue ->
        newValue >= oldValue
    }
}