package com.sam.animesta.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sam.animesta.R
import com.sam.animesta.databinding.ActivityMangaDetailsBinding
import com.sam.animesta.model.TopModel

class MangaDetails : AppCompatActivity() {
    lateinit var binding: ActivityMangaDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_manga_details)

        if (intent.hasExtra("Model")) {
            binding.topManga = intent.getParcelableExtra<TopModel>("Model")
        }

    }
}
