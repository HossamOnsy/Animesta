package com.sam.animesta.di

import androidx.fragment.app.Fragment
import com.sam.animesta.navigation.TopAnimeFragment
import com.sam.animesta.navigation.TopMangaFragment
import com.sam.animesta.ui.MainActivity
import dagger.Component

@Component
interface ActivityComponent {
    fun inject(fragment: TopAnimeFragment)
    fun inject(fragment: TopMangaFragment)
    fun inject(mainActivity: MainActivity)
}