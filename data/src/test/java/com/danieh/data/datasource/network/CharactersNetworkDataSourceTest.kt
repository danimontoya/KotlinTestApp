package com.danieh.data.datasource.network

import com.danieh.data.datasource.CharactersApi
import com.danieh.data.model.CharactersConfigurationWS
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by danieh
 */
class CharactersNetworkDataSourceTest {

    @Mock
    lateinit var client: CharactersApi

    lateinit var charactersNetworkDataSource: CharactersNetworkDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        charactersNetworkDataSource = CharactersNetworkDataSource(client)
    }

    @Test
    fun `get characters configuration page should retrieve the list of characters `() {
        val page = 1
        val charactersConfigurationWS = CharactersConfigurationWS(1, null, null, emptyList())
        val fakeSingleCharactersConfigurationWS = Single.just<CharactersConfigurationWS>(charactersConfigurationWS)
        given(client.getCharacters(page)).willReturn(fakeSingleCharactersConfigurationWS)

        charactersNetworkDataSource.getCharactersConfiguration(page)

        verify(client).getCharacters(page)
    }

}