package com.lukesmoljak.realestate.framework.presentation.property_listings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lukesmoljak.realestate.R
import com.lukesmoljak.realestate.business.domain.model.Property
import com.lukesmoljak.realestate.databinding.LayoutPropertyListingItemBinding

class PropertyListingsAdapter(
    private val interaction: Interaction? = null,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Property>() {

        override fun areItemsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PropertyViewHolder(
            itemBinding = LayoutPropertyListingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            interaction = interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PropertyViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Property>) {
        differ.submitList(list, null)
    }

    class PropertyViewHolder
    constructor(
        private val itemBinding: LayoutPropertyListingItemBinding,
        private val interaction: Interaction?,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private lateinit var property: Property

        fun bind(item: Property) = with(itemView) {

            property = item

            itemView.setOnClickListener {
                interaction?.onItemSelected(property)
            }

            itemBinding.streetAddressLine1TextView.text = property.streetAddress
            val line2 = property.suburb + ", " + property.postcode
            itemBinding.streetAddressLine2TextView.text = line2

            Glide
                .with(itemView)
                .load(property.propertyImageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(itemBinding.propertyPhotoImageView)

            Glide
                .with(itemView)
                .load(property.agentAvatarUrl)
                .circleCrop()
                .placeholder(R.drawable.placeholder)
                .into(itemBinding.agentPhotoImageView)

            itemBinding.numBedsTextView.text = property.numBedrooms.toString()
            itemBinding.numBathroomsTextView.text = property.numBathrooms.toString()
            itemBinding.numCarSpacesTextView.text = property.numCarSpaces.toString()

            itemBinding.agentNameTextView.text = property.agentName
        }

    }

    interface Interaction {
        fun onItemSelected(report: Property)
    }
}