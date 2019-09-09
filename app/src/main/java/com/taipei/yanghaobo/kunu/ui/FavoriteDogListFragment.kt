package com.taipei.yanghaobo.kunu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

import com.taipei.yanghaobo.kunu.R

import java.util.Objects

/**
 * @author yanghaobo
 */
class FavoriteDogListFragment : Fragment() {

  private var mToolbar: Toolbar? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_favorite_dog_list, container, false)
    setUpToolbar(view)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    NavigationUI.setupWithNavController(mToolbar!!, Navigation.findNavController(view))
  }

  private fun setUpToolbar(view: View) {
    setHasOptionsMenu(true)
    mToolbar = view.findViewById(R.id.kunu_basic_toolbar)
    (Objects.requireNonNull<FragmentActivity>(activity) as AppCompatActivity).setSupportActionBar(
      mToolbar
    )
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.kunu_basic_menu, menu)
  }
}// Required empty public constructor
