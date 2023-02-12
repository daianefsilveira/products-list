package br.com.products.presenter.fragments

import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.products.data.local.Products
import br.com.products.databinding.FragmentHomeBinding
import br.com.products.presenter.adapter.ProductsAdapter
import br.com.products.presenter.viewmodel.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : Fragment(), ProductsAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var productsAdapter: ProductsAdapter

    private val viewModel: ProductsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getAll()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.productsList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                viewModel.addAllProducts()
            } else {
                setupRecyclerView(it)
            }
        }
    }

    private fun setupRecyclerView(productsList: List<Products> = mutableListOf()) {
        productsAdapter = ProductsAdapter(productsList, this)
        binding.recyclerViewHome.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewHome.setHasFixedSize(true)
        binding.recyclerViewHome.adapter = productsAdapter
    }

    override fun delete(position: Int) {
        createAndShowAlertDialog(position)
    }

    private fun createAndShowAlertDialog(position: Int) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Are you sure you want to delete this product?")
        builder.setPositiveButton(
            R.string.ok
        ) { dialog, _ ->
            this@HomeFragment.onResume()
            viewModel.remove(position)
            dialog.dismiss()
        }
        builder.setNegativeButton(
            R.string.cancel
        ) { dialog, _ ->
            dialog.cancel()
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

}
