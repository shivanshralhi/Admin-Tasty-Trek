package com.plcoding.admintastytrek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.plcoding.admintastytrek.adapter.AddItemAdapter
import com.plcoding.admintastytrek.databinding.ActivityAllItemBinding

class AllItemActivity : AppCompatActivity() {
    private val binding : ActivityAllItemBinding  by lazy{
        ActivityAllItemBinding.inflate(layoutInflater)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val menufoodName = listOf("Burger","Pizza","Momos","Noodles")
        val menuItemPrice = listOf("$12","$2","$13","$45")
        val menuImage = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4
        )
        val adapter = AddItemAdapter(ArrayList(menufoodName), ArrayList(menuItemPrice),
            ArrayList(menuImage)
        )
        binding.backButton.setOnClickListener {
            finish()
        }
        binding.MenuRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter = adapter

    }
}