package com.sam.animesta.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sam.animesta.databinding.ActivityMangaDetailsBinding
import com.sam.animesta.model.TopModel
import kotlinx.android.synthetic.main.activity_manga_details.*
import android.content.Intent
import android.net.Uri


class MangaDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityMangaDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, com.sam.animesta.R.layout.activity_manga_details)

        if (intent.hasExtra("Model")) {
            binding.topManga = intent.getParcelableExtra<TopModel>("Model")
            setlistener()
        }
    }

    private fun setlistener() {
        iv_website.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(binding.topManga?.url)
            startActivity(i)
        }


    }
}
