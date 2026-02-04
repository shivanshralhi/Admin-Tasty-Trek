package com.plcoding.admintastytrek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.plcoding.admintastytrek.adapter.DeliveryAdapter
import com.plcoding.admintastytrek.adapter.PendingOrderAdapter
import com.plcoding.admintastytrek.databinding.ActivityPendingOrderBinding

class PendingOrderActivity : AppCompatActivity() {
    private val binding : ActivityPendingOrderBinding by lazy{
        ActivityPendingOrderBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish()
        }
        val orderedcustomerName = arrayListOf("Shivansh","Anurag","Pradeep","Kusharag")
        val orderedquantity = arrayListOf("8","7","2","3")
        val orderedFoodImage = arrayListOf(R.drawable.menu1,R.drawable.menu4,R.drawable.menu3,R.drawable.menu2)
        val adapter = PendingOrderAdapter(orderedcustomerName, orderedquantity,orderedFoodImage)
        binding.pendingOrderRecyclerView.adapter = adapter
        binding.pendingOrderRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}