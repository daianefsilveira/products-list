package br.com.products.presenter.adapter

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.products.R
import br.com.products.data.local.Products
import br.com.products.databinding.ItemProductBinding
import com.bumptech.glide.Glide

class ProductsAdapter(var productsList: List<Products>, private var listener: OnItemClickListener) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    interface OnItemClickListener {
        fun delete(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ItemProductBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ProductsViewHolder(binding)
    }

    override fun getItemCount() = productsList.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        holder.binding.itemDelete.setOnClickListener {
            listener.delete(position)
        }

        holder.bind(productsList[position])
    }

    class ProductsViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(products: Products) = with(itemView) {
            binding.itemTitle.text = products.title
            binding.itemType.text = products.type
            binding.itemDescription.text = products.description
            binding.itemPrice.text = formatCurrency(products.price)
            binding.simpleRatingBar.rating = products.rating.toFloat()
            Glide.with(this)
                .load(Uri.parse("file:///android_asset/images/${products.filename}"))
                .into(binding.itemImage);

            val title = binding.itemTitle.text
            binding.root.setOnClickListener {
                findNavController().navigate(
                    R.id.updateProductFragment,
                    sendBundle(title.toString())
                )
            }
        }

        private fun sendBundle(title: String): Bundle {
            val bundle = Bundle()
            bundle.putString("data", title)
            return bundle
        }

        private fun formatCurrency(price: Double): String {
            return "R$ $price"
        }
    }
}

