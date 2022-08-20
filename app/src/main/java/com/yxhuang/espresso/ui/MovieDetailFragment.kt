package com.yxhuang.espresso.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.yxhuang.espresso.R
import com.yxhuang.espresso.data.Movie
import com.yxhuang.espresso.data.source.MoviesRemoteDataSource

/**
 * Created by yxhuang
 * Date: 2022/8/15
 * Description:
 */
class MovieDetailFragment : Fragment(){

    private var movie: Movie? = null

    private lateinit var movie_title: TextView
    private lateinit var movie_image: ImageView
    private lateinit var movie_directiors: TextView
    private lateinit var movie_star_actors: TextView
    private lateinit var movie_description: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            args.getInt("movie_id").let{ movieId ->
                MoviesRemoteDataSource.getMovie(movieId)?.let{ movieFromRemote ->
                    movie = movieFromRemote
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie_title = view.findViewById(R.id.movie_title)
        movie_image = view.findViewById(R.id.movie_image)
        movie_directiors = view.findViewById(R.id.movie_directiors)
        movie_star_actors = view.findViewById(R.id.movie_star_actors)
        movie_description = view.findViewById(R.id.movie_description)

        setMovieDetails()

        movie_directiors.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_directors", movie?.directors)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, DirectorsFragment::class.java, bundle)
                ?.addToBackStack("DirectorsFragment")
                ?.commit()
        }

        movie_star_actors.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_actors", movie?.star_actors)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, StarActorsFragment::class.java, bundle)
                ?.addToBackStack("StarActorsFragment")
                ?.commit()
        }
    }

    private fun setMovieDetails(){
        movie?.let{ nonNullMovie ->
            Glide.with(this)
                .load(nonNullMovie.image)
                .into(movie_image)
            movie_title.text = nonNullMovie.title
            movie_description.text = nonNullMovie.description
        }
    }

}
