package br.com.products.presenter.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.products.R
import br.com.products.databinding.FragmentUpdateProductBinding
import br.com.products.presenter.viewmodel.ProductsViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class UpdateProductFragment : Fragment() {

    private var _binding: FragmentUpdateProductBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateProductBinding.inflate(inflater, container, false)
        viewModel.getItemByTitle(arguments?.getString("data")!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButton()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.product.observe(viewLifecycleOwner) {
            binding.etTitle.editText?.setText(it.title)
            binding.etType.editText?.setText(it.type)
            binding.etDescription.editText?.setText(it.description)
            binding.etPrice.editText?.setText(it.price.toString())
            binding.simpleRatingBar.rating = it.rating.toFloat()
            Glide.with(this)
                .load(Uri.parse("file:///android_asset/images/${it.filename}"))
                .into(binding.ivImage);
        }
    }

    private fun setupButton() {
        binding.btnSave.setOnClickListener {
            val product = viewModel.product.value
            product?.title = binding.etTitle.editText?.text.toString()
            product?.type = binding.etType.editText?.text.toString()
            product?.description = binding.etDescription.editText?.text.toString()
            product?.price = binding.etPrice.editText?.text.toString().toDouble()
            product?.rating = binding.simpleRatingBar.rating.toString().replace(".0", "").toInt()

            viewModel.update(product!!)
            findNavController().navigate(
                R.id.homeFragment
            )
        }
    }
}



