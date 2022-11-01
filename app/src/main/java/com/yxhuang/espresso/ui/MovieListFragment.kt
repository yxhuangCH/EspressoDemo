package com.yxhuang.espresso.ui

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yxhuang.espresso.R
import com.yxhuang.espresso.data.FakeMovieData.FAKE_NETWORK_DELAY
import com.yxhuang.espresso.data.Movie
import com.yxhuang.espresso.data.source.MoviesDataSource
import com.yxhuang.espresso.util.EspressoIdlingResource
import com.yxhuang.espresso.util.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by yxhuang
 * Date: 2022/10/14
 * Description:
 */
class MovieListFragment(
        val moviesDataSource: MoviesDataSource
) : Fragment(),
    MoviesListAdapter.Interaction
{

    private val TAG: String = "AppDebug"

    override fun onItemSelected(position: Int, item: Movie) {
        activity?.run {
            val bundle = Bundle()
            bundle.putInt("movie_id", item.id)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                .addToBackStack("MovieDetailFragment")
                .commit()
        }
    }

    lateinit var listAdapter: MoviesListAdapter
    lateinit var uiCommunicationListener: UICommunicationListener

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        uiCommunicationListener = context as UICommunicationListener

        getData()
    }


    private fun getData(){
        EspressoIdlingResource.increment()
        uiCommunicationListener.loading(true)
        val job = GlobalScope.launch(Dispatchers.IO) { // 模拟网络耗时
            delay(FAKE_NETWORK_DELAY)
        }
        job.invokeOnCompletion{
            lifecycleScope.launch {
                uiCommunicationListener.loading(false)
                listAdapter.submitList(moviesDataSource.getMovies()) // 回来刷新数据
                EspressoIdlingResource.decrement()
            }
        }
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            removeItemDecoration(TopSpacingItemDecoration(30))
            addItemDecoration(TopSpacingItemDecoration(30))
            listAdapter = MoviesListAdapter(this@MovieListFragment)
            adapter = listAdapter
        }
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        try{
//            uiCommunicationListener = context as UICommunicationListener
//        }catch (e: ClassCastException){
//            Log.e(TAG, "Must implement interface in $activity: ${e.message}")
//        }
//    }
}