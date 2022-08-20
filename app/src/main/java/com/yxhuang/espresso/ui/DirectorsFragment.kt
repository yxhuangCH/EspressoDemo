package com.yxhuang.espresso.ui

import android.os.Bundle
import android.view.LayoutInflater
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
class DirectorsFragment : Fragment() {

    private val directors: ArrayList<String> = ArrayList()
    private lateinit var mDirectorsText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            directors.addAll(args.get("args_directors") as List<String>)
        } ?: activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, ErrorFragment::class.java, null)?.commit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_directors, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDirectorsText = view.findViewById(R.id.directors_text)

        setDirectors()
    }

    private fun setDirectors() {
        mDirectorsText.text = stringBuilderForDirectors(directors)
    }

    companion object {

        fun stringBuilderForDirectors(directors: ArrayList<String>): String {
            val sb = StringBuilder()
            for (director in directors) {
                sb.append(director + "\n")
            }
            return sb.toString()
        }
    }
}
