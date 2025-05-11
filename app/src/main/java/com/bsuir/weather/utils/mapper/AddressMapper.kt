package com.bsuir.weather.utils.mapper

import com.bsuir.weather.data.dto.AddressDTO
import com.bsuir.weather.domain.model.AddressModel

object AddressMapper {
    fun AddressModel.toDTO(): AddressDTO {
        return AddressDTO(
            countryName = this.countryName,
            locality = this.locality,
            subLocality = this.subLocality,
            adminArea = this.adminArea,
            subAdminArea = this.subAdminArea,
            thoroughfare = this.thoroughfare,
            subThoroughfare = this.subThoroughfare,
            alias = this.alias
        )
    }
    
    fun AddressDTO.toModel(): AddressModel {
        return AddressModel(
            countryName = this.countryName,
            locality = this.locality,
            subLocality = this.subLocality,
            adminArea = this.adminArea,
            subAdminArea = this.subAdminArea,
            thoroughfare = this.thoroughfare,
            subThoroughfare = this.subThoroughfare,
        ).also {
            it.alias = this.alias
        }
    }
}