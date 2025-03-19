package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.HourlyForecastModel
import com.bsuir.weather.domain.repository.HourlyForecastRepository
import javax.inject.Inject

class GetHourlyForecastUseCase @Inject constructor(
    private val hourlyForecastRepository: HourlyForecastRepository
) {
    fun execute(): List<HourlyForecastModel> {
        return hourlyForecastRepository.getHourlyForecastList()
    }
}
