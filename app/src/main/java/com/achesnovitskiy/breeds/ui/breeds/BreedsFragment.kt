package com.achesnovitskiy.breeds.ui.breeds

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.achesnovitskiy.breeds.R
import com.achesnovitskiy.breeds.app.App.Companion.appComponent
import com.achesnovitskiy.breeds.ui.base.BaseFragment
import com.achesnovitskiy.breeds.ui.breeds.di.BreedsModule
import com.achesnovitskiy.breeds.ui.breeds.di.DaggerBreedsComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class BreedsFragment : BaseFragment(R.layout.fragment_list) {

    @Inject
    lateinit var viewModel: BreedsViewModel

    private val breedsAdapter: BreedsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BreedsAdapter {
            Toast
                .makeText(
                    requireContext(),
                    it.name,
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerBreedsComponent
            .builder()
            .appComponent(appComponent)
            .breedsModule(
                BreedsModule(viewModelStoreOwner = this)
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(breeds_recycler_view) {
            adapter = breedsAdapter

            layoutManager = LinearLayoutManager(context)

            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun onResume() {
        super.onResume()

        disposable = CompositeDisposable(
            viewModel.breedsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { breeds ->
                        breedsAdapter.submitList(breeds)
                    },
                    { error ->
                        Log.e("My_Error", "Loading error", error)
                    }
                )
        )
    }
}