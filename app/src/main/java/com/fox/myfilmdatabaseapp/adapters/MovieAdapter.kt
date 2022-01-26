package com.fox.myfilmdatabaseapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fox.myfilmdatabaseapp.R
import com.fox.myfilmdatabaseapp.databinding.MovieItemBinding
import com.fox.myfilmdatabaseapp.models.MovieModel

class MovieAdapter(private val deleteMovie:(MovieModel)->Unit,
                   private val editMovie:(MovieModel)->Unit) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private val MoviesList = ArrayList<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MovieItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.movie_item, parent, false)
        return MovieHolder(binding)
    }

    override fun getItemCount(): Int {
        return MoviesList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(MoviesList[position], deleteMovie, editMovie)
    }

    fun setList(movies: List<MovieModel>) {
       MoviesList.clear()
        MoviesList.addAll(movies)

    }


    class MovieHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movieModel: MovieModel,
            deleteMovie: (MovieModel) -> Unit,
            editMovie: (MovieModel) -> Unit

        ) {

            binding.idMovie.text = movieModel.id.toString()
            binding.nameMovie.text = movieModel.name
            binding.categoryMovie.text = movieModel.category
            binding.durationMovie.text = movieModel.duration

            binding.editMovie.setOnClickListener(View.OnClickListener {
                editMovie(movieModel)
            })

            binding.deleteMovie.setOnClickListener(View.OnClickListener {
                deleteMovie(movieModel)
            })

        }




    }

}
