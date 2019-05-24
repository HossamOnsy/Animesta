package com.sam.animesta


import android.content.Context
import com.sam.animesta.repository.MainRepository
import com.sam.animesta.ui.AnimeDetailsActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RepositoryUnitTest {

    @Mock
    lateinit var mainRepo:  MainRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun checkingFunctionBeingCalled() {
        mainRepo = Mockito.mock(MainRepository::class.java)

        var x =  Mockito.mock(Context::class.java)
        mainRepo.getDetails("1",x)


        verify(mainRepo, times(1)).getDetails("1",x);

        verifyNoMoreInteractions(mainRepo)

    }

    @Test
    fun DetailsTest() {
        var t= Mockito.mock(AnimeDetailsActivity::class.java)


        `when`(t.startX()).thenReturn(0)

        assertEquals(t.xyz,"3")


    }


}