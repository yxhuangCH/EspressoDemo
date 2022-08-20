package com.yxhuang.espresso.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.yxhuang.espresso.ErrorFragment
import com.yxhuang.espresso.R

/**
 * Created by yxhuang
 * Date: 2022/8/15
 * Description:
 */
class StarActorsFragment : Fragment() {

    private val starActors: ArrayList<String> = ArrayList()
    private lateinit var star_actors_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            starActors.addAll(args.get("args_actors") as List<String>)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_star_actors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        star_actors_text = view.findViewById(R.id.star_actors_text)

        setActors()
    }

    private fun setActors() {
        star_actors_text.text = stringBuilderForStarActors(starActors)
    }


    companion object{
        fun stringBuilderForStarActors(actors: ArrayList<String>): String{
            val sb = StringBuilder()
            for(actor in actors){
                sb.append(actor + "\n")
            }
            return sb.toString()
        }
    }
}