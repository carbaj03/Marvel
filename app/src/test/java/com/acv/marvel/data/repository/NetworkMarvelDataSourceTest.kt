package com.acv.marvel.data.repository

import arrow.core.Either
import com.acv.marvel.data.MarvelClient
import com.acv.marvel.domain.NotInternetDomainError
import com.acv.marvel.domain.UnknownDomainError
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert
import okhttp3.mockwebserver.SocketPolicy
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.net.UnknownHostException

class RandomUserRetrofitShould : MockWebServerTest() {

    val networkModule: TestNetworkModule = TestNetworkModule()
    lateinit var gateway: NetworkMarvelDataSource


    @Before
    override fun setUp() {
        super.setUp()
        gateway = NetworkMarvelDataSource(networkModule.getApiClient(server))
    }

    @Test
    @Throws(Exception::class)
    fun shouldSendsGetAllRandomUserRequestToTheCorrectEndpoint() {
        enqueueMockResponse(200, "getMarvel.json")

        gateway.getAll()

        assertGetRequestSentTo("/bins/bvyob")
    }

    @Test
    @Throws(Exception::class)
    fun throwsUnknownErrorExceptionIfThereIsNotHandledErrorGettingAllTasks() {
        enqueueMockResponse(403)

        val all = gateway.getAll()

        when(all){
            is Either.Left -> Assert.assertEquals(all.a, UnknownDomainError())
            is Either.Right -> Assert.assertEquals(all.b, UnknownDomainError())
        }
    }

    @Test
    @Throws(Exception::class)
    fun throwsExceptionWhenWithoutInternetConnection() {
        val apiClient = Mockito.mock(MarvelClient::class.java)
        whenever(apiClient.getMarvel()).thenThrow(UnknownHostException::class.java)

        val all = gateway.getAll()

        when(all){
            is Either.Left -> Assert.assertEquals(all.a, UnknownDomainError())
            is Either.Right -> Assert.assertEquals(all.b, UnknownDomainError())
        }
    }

    @Test
    @Throws(Exception::class)
    fun throwsNetworkGatewayExceptionWhenInternalServerError() {
        enqueueMockResponse(500)

        val all = gateway.getAll()

        when(all){
            is Either.Left -> Assert.assertEquals(all.a, UnknownDomainError())
            is Either.Right -> Assert.assertEquals(all.b, UnknownDomainError())
        }
    }

    @Test()
    @Throws(Exception::class)
    fun throwsNetworkExceptionWhenTimeOut() {
        enqueueMockResponse(SocketPolicy.NO_RESPONSE)

        val all = gateway.getAll()

        when(all){
            is Either.Left -> Assert.assertEquals(all.a, NotInternetDomainError)
            is Either.Right -> Assert.assertEquals(all.b, NotInternetDomainError)
        }
    }
}