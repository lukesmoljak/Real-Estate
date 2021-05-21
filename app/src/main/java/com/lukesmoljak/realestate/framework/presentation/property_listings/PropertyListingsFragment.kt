package com.lukesmoljak.realestate.framework.presentation.property_listings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukesmoljak.realestate.business.domain.model.Property
import com.lukesmoljak.realestate.databinding.FragmentPropertyListingsBinding
import com.lukesmoljak.realestate.di.AppComponent
import com.lukesmoljak.realestate.framework.BaseApplication
import com.lukesmoljak.realestate.framework.presentation.util.gone
import com.lukesmoljak.realestate.framework.presentation.util.visible
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class PropertyListingsFragment(
    private val viewModelFactory: ViewModelProvider.Factory
): Fragment(),
    PropertyListingsAdapter.Interaction {

    private var _binding: FragmentPropertyListingsBinding? = null
    private val binding get() = _binding!!

    private var listAdapter: PropertyListingsAdapter? = null

    private val viewModel: PropertyListingsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPropertyListingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        setupViews()
        subscribeObservers()
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        listAdapter = null
    }

    private fun getAppComponent(): AppComponent {
        return activity?.run {
            (application as BaseApplication).appComponent
        }?: throw Exception("AppComponent is null.")
    }

    private fun inject() {
        getAppComponent().inject(this)
    }

    private fun setupViews() {
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                listAdapter = PropertyListingsAdapter(
                    this@PropertyListingsFragment,
                )
                adapter = listAdapter
            }
        }
    }

    private fun subscribeObservers() {
        viewModel.propertyListings.observe(viewLifecycleOwner, { propertyListings ->
            if (!propertyListings.isNullOrEmpty()) {
                listAdapter?.submitList(viewModel.propertyListings.value!!)
                listAdapter?.notifyDataSetChanged()
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) binding.progressBar.visible()
            else binding.progressBar.gone()
        })
        viewModel.error.observe(viewLifecycleOwner, { message ->
            if(!message.isNullOrEmpty()) {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onItemSelected(property: Property) {
        val action = PropertyListingsFragmentDirections
                .actionPropertyListingsFragmentToPropertyDetailFragment(
                        id = property.id
                )
        findNavController().navigate(action)
    }

}