package com.bsuir.weather.utils.mapper

import android.location.Address
import com.bsuir.weather.data.dto.LocationDTO
import com.bsuir.weather.domain.model.AddressModel
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.mapper.AddressMapper.toDTO
import com.bsuir.weather.utils.mapper.AddressMapper.toModel
import com.bsuir.weather.utils.mapper.CoordinatesMapper.toDTO
import com.bsuir.weather.utils.mapper.CoordinatesMapper.toModel

object LocationMapper {
    fun Address.toModel(): LocationModel {
        return LocationModel(
            coordinates = Coordinates(
                latitude = this.latitude,
                longitude = this.longitude
            ),
            address = AddressModel(
                countryName = this.countryName,
                locality = this.locality,
                subLocality = this.subLocality,
                adminArea = this.adminArea,
                subAdminArea = this.subAdminArea,
                thoroughfare = this.thoroughfare,
                subThoroughfare = this.subThoroughfare,
            )
        )
    }

    fun LocationDTO.toModel(): LocationModel {
        return LocationModel(
            coordinates = this.coordinates.toModel(),
            address = this.address.toModel()
        )
    }

    fun LocationModel.toDTO(): LocationDTO {
        return LocationDTO(
            coordinates = this.coordinates.toDTO(),
            address = this.address.toDTO()
        )
    }
}