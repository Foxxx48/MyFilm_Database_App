package com.fox.myfilmdatabaseapp.tabs

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.fox.myfilmdatabaseapp.R
import com.fox.myfilmdatabaseapp.db.Database
import com.fox.myfilmdatabaseapp.databinding.PanelEditMovieBinding
import com.fox.myfilmdatabaseapp.repositories.MovieRepository
import com.fox.myfilmdatabaseapp.viewModels.MovieFactory
import com.fox.myfilmdatabaseapp.viewModels.MovieViewModel

class PanelEditMovie : BottomSheetDialogFragment(),View.OnKeyListener, View.OnClickListener {

    private var binding: PanelEditMovieBinding? = null
    private var movieRepository: MovieRepository? = null
    private var movieViewModel: MovieViewModel? = null
    private var factory: MovieFactory? = null
    private var idMovie:Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.panel_edit_movie, container, false)

        idMovie = arguments?.getString("idMovie")?.toInt()
        binding?.editNameMovie?.setText(arguments?.getString("nameMovie").toString())
        binding?.editCategoryMovie?.setText(arguments?.getString("categoryMovie").toString())
        binding?.editDurationMovie?.setText(arguments?.getString("durationMovie").toString())


        val productDao = Database.getInstance((context as FragmentActivity).application).movieDAO
        movieRepository = MovieRepository(productDao)
       factory = MovieFactory(movieRepository!!)
        movieViewModel = ViewModelProvider(this, factory!!).get(MovieViewModel::class.java)

        binding?.editNameMovie?.setOnKeyListener(this)
        binding?.editCategoryMovie?.setOnKeyListener(this)
        binding?.editDurationMovie?.setOnKeyListener(this)

        binding?.buttonEditMovie?.setOnClickListener(this)


        return binding?.root
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {


            R.id.editNameMovie -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditNameMovie?.text = binding?.editNameMovie?.text
                    binding?.editNameMovie?.setText("")

                    return true
                }

            }

            R.id.editCategoryMovie -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditCategoryMovie?.text = binding?.editCategoryMovie?.text
                    binding?.editCategoryMovie?.setText("")

                    return true
                }

            }

            R.id.editPriceMovie -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditDurarionMovie?.text = binding?.editDurationMovie?.text
                    binding?.editDurationMovie?.setText("")

                    return true
                }

            }
        }

        return false
    }

    override fun onClick(view: View) {
        movieViewModel?.startUpdateProduct(idMovie.toString().toInt(), binding?.resEditNameMovie?.text?.toString()!!,
            binding?.resEditCategoryMovie?.text?.toString()!!, binding?.resEditPriceMovie?.text?.toString()!!)

        dismiss()

        (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, TabCategories()).commit()
    }

}