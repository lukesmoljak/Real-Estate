package com.lukesmoljak.realestate.framework.presentation.property_detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.lukesmoljak.realestate.databinding.FragmentPropertyDetailBinding
import com.lukesmoljak.realestate.databinding.FragmentPropertyListingsBinding
import com.lukesmoljak.realestate.di.AppComponent
import com.lukesmoljak.realestate.framework.BaseApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class PropertyDetailFragment(
        private val viewModelFactory: ViewModelProvider.Factory
): Fragment() {

    private val args: PropertyDetailFragmentArgs by navArgs()
    private var _binding: FragmentPropertyDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PropertyDetailViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPropertyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        if (args.id != 0) {
            viewModel.setId(args.id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun inject() {
        getAppComponent().inject(this)
    }

    private fun getAppComponent(): AppComponent {
        return activity?.run {
            (application as BaseApplication).appComponent
        } ?: throw Exception("AppComponent is null.")
    }

    private fun subscribeObservers() {
        viewModel.id.observe(viewLifecycleOwner, { id ->
            binding.idTextView.text = id.toString()
        })
    }
}