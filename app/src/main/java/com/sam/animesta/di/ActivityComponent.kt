package com.sam.animesta.di

import com.sam.animesta.navigation.TopAnimeBate5a5Fragment
import com.sam.animesta.ui.MainActivity
import dagger.Component

@Component
interface ActivityComponent {
    fun inject(topAnimeBate5a5Fragment: TopAnimeBate5a5Fragment)
    fun inject(mainActivity: MainActivity)
}