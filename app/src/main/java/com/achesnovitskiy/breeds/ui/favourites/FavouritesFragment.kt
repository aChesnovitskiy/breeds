package com.achesnovitskiy.breeds.ui.favourites

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.achesnovitskiy.breeds.R
import com.achesnovitskiy.breeds.app.App
import com.achesnovitskiy.breeds.ui.base.BaseFragment
import com.achesnovitskiy.breeds.ui.favourites.di.DaggerFavouritesComponent
import com.achesnovitskiy.breeds.ui.favourites.di.FavouritesModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class FavouritesFragment : BaseFragment(R.layout.fragment_list) {

    @Inject
    lateinit var viewModel: FavouritesViewModel

    private val favouritesAdapter: FavouritesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        FavouritesAdapter {
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

        DaggerFavouritesComponent
            .builder()
            .appComponent(App.appComponent)
            .favouritesModule(
                FavouritesModule(viewModelStoreOwner = this)
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar(
            title = getString(R.string.title_favourites),
            isBackButtonEnable = false,
            backButtonText = null
        )

        with(list_recycler_view) {
            adapter = favouritesAdapter

            layoutManager = LinearLayoutManager(context)

            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun onResume() {
        super.onResume()

        disposable = CompositeDisposable(
            viewModel.favouritesObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { favourites ->
//                        favouritesAdapter.submitList(favourites)
                    },
                    { error ->
                        Log.e("My_Error", "Loading error", error)
                    }
                )
        )
    }
}