package com.taipei.yanghaobo.kunu.ui.list

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.appbar.CollapsingToolbarLayout
import com.taipei.yanghaobo.kunu.DrawerHost
import com.taipei.yanghaobo.kunu.InjectorUtils
import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.db.DogListEntry

import java.util.Objects

/**
 * 狗狗品種列表
 */
class DogGridFragment : Fragment(), DogCardPagedListedAdapter.DogCardOnClickedListener {

  private var mKunuDogRecycler: RecyclerView? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    setHasOptionsMenu(true)
    return inflater.inflate(R.layout.kunu_dog_grid_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    mKunuDogRecycler = view.findViewById(R.id.kunu_dog_recycler)
    mKunuDogRecycler!!.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    val adapter = DogCardPagedListedAdapter(context)
    adapter.setDogCardOnClickedListener(this)
    mKunuDogRecycler!!.adapter = adapter

    val dogCardVMFactory = InjectorUtils.provideDogCardVMFactory(context!!)
    val dogCardViewModel = ViewModelProviders
      .of(activity!!, dogCardVMFactory)
      .get(DogCardViewModel::class.java)

    dogCardViewModel.allDogList.observe(this, { dogEntries ->
      Toast.makeText(
        context,
        "DogLise size = " + Objects.requireNonNull<PagedList<DogListEntry>>(dogEntries).size,
        Toast.LENGTH_SHORT
      ).show()
      adapter.submitList(dogEntries)
    })

    setUpToolBar(view)
  }

  private fun setUpToolBar(view: View) {
    val kunuDoglistCTL =
      view.findViewById<CollapsingToolbarLayout>(R.id.kunu_doglist_collapsingToolbarLayout)
    val kunuToolBar = view.findViewById<Toolbar>(R.id.kunu_doglist_toolbar)
    //        替代預設的 ActionBar
    val activity = activity as AppCompatActivity?
    if (activity is DrawerHost) {
      activity.setSupportActionBar(kunuToolBar)
      //            專門for CollapsingToolbarLayout 的 Method
      NavigationUI.setupWithNavController(
        kunuDoglistCTL,
        kunuToolBar,
        Navigation.findNavController(view),
        (getActivity() as DrawerHost).drawer
      )
    } else activity?.setSupportActionBar(kunuToolBar)

  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.kunu_toolbar_menu, menu)
  }

  //    Toolbar 的 點擊事件callback
  override fun onOptionsItemSelected(item: MenuItem): Boolean {

    val itemId = item.itemId
    when (itemId) {
      R.id.kunu_actions_settings -> {

        Navigation.findNavController(Objects.requireNonNull<View>(view))
          .navigate(R.id.action_dogGridFragment_to_settingActivity)
        return true
      }

      R.id.kunu_menu_layout_type -> {
        if (mKunuDogRecycler!!.layoutManager is GridLayoutManager) {
          mKunuDogRecycler!!.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
          item.setIcon(R.drawable.kunu_grid_list_icon)
          mKunuDogRecycler!!.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
              outRect: Rect,
              view: View,
              parent: RecyclerView,
              state: RecyclerView.State
            ) {
              super.getItemOffsets(outRect, view, parent, state)
              outRect.left = resources.getDimensionPixelSize(R.dimen.basic_zero)
              outRect.right = resources.getDimensionPixelSize(R.dimen.basic_zero)
            }
          })
        } else {
          mKunuDogRecycler!!.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
          mKunuDogRecycler!!.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
              outRect: Rect,
              view: View,
              parent: RecyclerView,
              state: RecyclerView.State
            ) {
              super.getItemOffsets(outRect, view, parent, state)
              outRect.left = resources.getDimensionPixelSize(R.dimen.grid_spacing)
              outRect.right = resources.getDimensionPixelSize(R.dimen.grid_spacing)
            }
          })
          item.setIcon(R.drawable.kunu_linear_list_icon)
        }
        return true
      }
      else -> {
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onDogItemClick(dogListEntry: DogListEntry) {

    val actionToDogDetailFragment =
      DogGridFragmentDirections.actionDogGridFragmentToDogDetailFragment()
        .setId(dogListEntry.id)
    Navigation.findNavController(Objects.requireNonNull<View>(view))
      .navigate(actionToDogDetailFragment)
  }
}// Required empty public constructor
