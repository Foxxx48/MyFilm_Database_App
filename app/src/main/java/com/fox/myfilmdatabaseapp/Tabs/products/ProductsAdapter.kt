package com.fox.myfilmdatabaseapp.Tabs.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fox.myfilmdatabaseapp.Products
import com.fox.myfilmdatabaseapp.R
import com.fox.myfilmdatabaseapp.databinding.ProductsItemBinding


class ProductsAdapter(private val deleteProduct:(Products)->Unit,
                      private val editProduct:(Products)->Unit): RecyclerView.Adapter<ProductsAdapter.ProductsHolder>() {

    private val productsList = ArrayList<Products>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProductsItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.products_item, parent, false)
        return ProductsHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        holder.bind(productsList[position],
            deleteProduct, editProduct)
    }

    fun setList(products: List<Products>) {
        productsList.clear()
        productsList.addAll(products)

    }


    class ProductsHolder(val binding: ProductsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            products: Products, deleteProduct: (Products) -> Unit, editProduct: (Products) -> Unit
        ) {

            binding.idProduct.text = products.id.toString()

            binding.nameProduct.text = products.name
            binding.categoryProduct.text = products.category
            binding.priceProduct.text = products.price.toString()


            binding.editProduct.setOnClickListener(View.OnClickListener {
                editProduct(products)
            })

            binding.deleteProduct.setOnClickListener(View.OnClickListener {
                deleteProduct(products)
            })
        }

    }

}
