package com.bsuir.weather.utils.mapper

import android.location.Address
import com.bsuir.weather.data.dto.AddressDTO
import com.bsuir.weather.data.dto.LocationDTO
import com.bsuir.weather.domain.model.AddressModel
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
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
            address = AddressModel(
                countryName = this.address.countryName,
                locality = this.address.locality,
                subLocality = this.address.subLocality,
                adminArea = this.address.adminArea,
                subAdminArea = this.address.subAdminArea,
                thoroughfare = this.address.thoroughfare,
                subThoroughfare = this.address.subThoroughfare,
            ).also {
                it.alias = this.address.alias
            }
        )
    }

    fun LocationModel.toDTO(): LocationDTO {
        return LocationDTO(
            coordinates = this.coordinates.toDTO(),
            address = AddressDTO(
                countryName = this.address.countryName,
                locality = this.address.locality,
                subLocality = this.address.subLocality,
                adminArea = this.address.adminArea,
                subAdminArea = this.address.subAdminArea,
                thoroughfare = this.address.thoroughfare,
                subThoroughfare = this.address.subThoroughfare,
                alias = this.address.alias
            )
        )
    }
}