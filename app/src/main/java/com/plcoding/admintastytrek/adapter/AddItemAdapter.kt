package com.plcoding.admintastytrek.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.admintastytrek.databinding.ItemItemBinding
import java.util.ArrayList

class AddItemAdapter(private val MenuItemName : ArrayList<String>, private val MenuItemPrice :ArrayList<String>, private val MenuItemImage :ArrayList<Int>) : RecyclerView.Adapter<AddItemAdapter.AddItemViewHolder>(){
    private val itemQuantitites = IntArray(MenuItemName.size){1}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {

        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddItemViewHolder(binding)
    }



    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {

        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return MenuItemName.size

    }
    inner class AddItemViewHolder(private val binding : ItemItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position : Int){
            binding.apply {
                val quantity = itemQuantitites[position]
                foodNameTextView.text = MenuItemName[position]
                PriceTextView.text = MenuItemPrice[position]
                foodImageView.setImageResource(MenuItemImage[position])
                quantityTextView.text = quantity.toString()

                minusButton.setOnClickListener {
                    decreaseQuantity(position)

                }
                addButton.setOnClickListener {
                    increaseQuantity(position)
                }
                deleteButton.setOnClickListener {
                    deleteQuantity(position)
                }
            }

        }

        private fun decreaseQuantity(position : Int) {
            if(itemQuantitites[position]>1){
                itemQuantitites[position]--
                binding.quantityTextView.text = itemQuantitites[position].toString()
            }

        }
        private fun increaseQuantity(position: Int) {
            if(itemQuantitites[position]<10){
                itemQuantitites[position]++
                binding.quantityTextView.text = itemQuantitites[position].toString()
            }

        }
        private fun deleteQuantity(position: Int) {
            MenuItemName.removeAt(position)
            MenuItemPrice.removeAt(position)
            MenuItemImage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,MenuItemName.size)

        }

    }


}