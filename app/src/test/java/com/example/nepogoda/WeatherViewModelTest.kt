@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.nepogoda

import com.example.nepogoda.features.weather.WeatherViewModel
import com.example.nepogoda.features.weather.model.WeatherBackground
import com.example.nepogoda.features.weather.model.WeatherModel
import com.example.nepogoda.features.weather.usecase.IWeatherUC
import com.example.nepogoda.infrastructure.async.Fail
import com.example.nepogoda.infrastructure.async.Loading
import com.example.nepogoda.infrastructure.async.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.orbitmvi.orbit.test
import java.time.LocalDate
import kotlin.random.Random

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class WeatherViewModelTest {

    @Mock
    private lateinit var useCase: IWeatherUC

    @InjectMocks
    private lateinit var viewModel: WeatherViewModel

    @Test
    fun `test ViewModel Success`() = runTest {
        val response = WeatherModel(
            visibility = Random.nextInt().toString(),
            wind = Random.nextInt().toString(),
            city = Random.nextInt().toString(),
            desc = Random.nextInt().toString(),
            date = LocalDate.now(),
            humidity = Random.nextInt().toString(),
            temp = Random.nextInt().toString(),
            backgroundColor = WeatherBackground.Blue,
        )
        whenever(useCase.invoke(any())).thenReturn(response)

        val middleware = viewModel.test()

        middleware.testIntent {
            getWeather()
        }

        middleware.assert(WeatherViewModel.WeatherState()) {
            states(
                { copy(weatherState = Loading()) },
                { copy(weatherState = Success(response)) },
            )
        }
    }

    @Test
    fun `test ViewModel Error`() = runTest {
        val error = MockitoException("Exception")

        whenever(useCase.invoke(any())).thenThrow(error)

        val middleware = viewModel.test()

        middleware.testIntent {
            getWeather()
        }

        middleware.assert(WeatherViewModel.WeatherState()) {
            states(
                { copy(weatherState = Loading()) },
                { copy(weatherState = Fail(error = error)) },
                { copy(weatherState = Loading()) },
            )
        }
    }
}