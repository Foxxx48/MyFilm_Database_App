package com.fox.myfilmdatabaseapp.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fox.myfilmdatabaseapp.R
import com.fox.myfilmdatabaseapp.databinding.CategoryItemBinding
import com.fox.myfilmdatabaseapp.databinding.ProductItemBinding
import com.fox.myfilmdatabaseapp.models.CategoryModel
import com.fox.myfilmdatabaseapp.models.ProductModel

class ProductAdapter(private val deleteProduct:(ProductModel)->Unit,
                     private val editProduct:(ProductModel)->Unit) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    private val productsList = ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProductItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.product_item, parent, false)
        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productsList[position], deleteProduct, editProduct)
    }

    fun setList(products: List<ProductModel>) {
       productsList.clear()
        productsList.addAll(products)

    }


    class ProductHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            productsModel: ProductModel,
            deleteProduct: (ProductModel) -> Unit,
            editProduct: (ProductModel) -> Unit

        ) {

            binding.idProduct.text = productsModel.id.toString()
            binding.nameProduct.text = productsModel.name
            binding.categoryProduct.text = productsModel.category
            binding.priceProduct.text = productsModel.price

            binding.editProduct.setOnClickListener(View.OnClickListener {
                editProduct(productsModel)
            })

            binding.deleteProduct.setOnClickListener(View.OnClickListener {
                deleteProduct(productsModel)
            })

        }




    }

}
