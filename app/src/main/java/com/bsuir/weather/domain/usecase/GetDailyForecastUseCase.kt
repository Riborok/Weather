package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.DailyForecastModel
import com.bsuir.weather.domain.repository.DailyForecastRepository
import javax.inject.Inject

class GetDailyForecastUseCase @Inject constructor(
    private val dailyForecastRepository: DailyForecastRepository
) {
    fun execute(): List<DailyForecastModel> {
        return dailyForecastRepository.getDailyForecastList()
    }
}
