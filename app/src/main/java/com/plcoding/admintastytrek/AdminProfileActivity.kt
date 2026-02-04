package com.plcoding.admintastytrek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.plcoding.admintastytrek.databinding.ActivityAdminProfileBinding

class AdminProfileActivity : AppCompatActivity() {
    private val binding: ActivityAdminProfileBinding by lazy {
        ActivityAdminProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish()
        }
        binding.name.isEnabled = false;
        binding.password.isEnabled = false;
        binding.email.isEnabled = false;
        binding.phoneNumber.isEnabled = false;
        binding.address.isEnabled = false;

        var isEnable = false;
        binding.editButton.setOnClickListener {
            isEnable  = ! isEnable
            binding.name.isEnabled = isEnable;
            binding.password.isEnabled = isEnable;
            binding.email.isEnabled = isEnable;
            binding.phoneNumber.isEnabled = isEnable;
            binding.address.isEnabled = isEnable;
            if(isEnable){
                binding.name.requestFocus()
            }

        }


    }
}