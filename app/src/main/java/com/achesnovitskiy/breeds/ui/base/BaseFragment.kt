package com.achesnovitskiy.breeds.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.achesnovitskiy.breeds.R
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId) {

    open var disposable: Disposable = Disposables.disposed()

    override fun onPause() {
        disposable.dispose()

        super.onPause()
    }

    override fun onDestroy() {
        disposable.dispose()

        super.onDestroy()
    }

    fun setupToolbar(
        title: String,
        isBackButtonEnable: Boolean,
        backButtonText: String?
    ) {
        with (requireActivity()) {
            main_toolbar_title.text = title

            main_toolbar_back_button.isVisible = isBackButtonEnable
            main_toolbar_back_button.text = backButtonText ?: ""
        }
    }
}