package com.example.cardealapplication.dataModel

import android.os.Parcel
import android.os.Parcelable

data class InfoCarDetailsDataModel(val txtCompanyName : String,val txtCarName : String,val txtPriceRange : String,
                                   val txtMileage : String,val txtEngine : String,val txtSeat : String,
                                   val txtFuelCapacity : String,val txtFuelType : String,val txtTransmission : String,
                                   val txtType : String,val txtLength : String,val txtWidth : String,
                                   val txtHeight : String,val txtDetails : String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(txtCompanyName)
        parcel.writeString(txtCarName)
        parcel.writeString(txtPriceRange)
        parcel.writeString(txtMileage)
        parcel.writeString(txtEngine)
        parcel.writeString(txtSeat)
        parcel.writeString(txtFuelCapacity)
        parcel.writeString(txtFuelType)
        parcel.writeString(txtTransmission)
        parcel.writeString(txtType)
        parcel.writeString(txtLength)
        parcel.writeString(txtWidth)
        parcel.writeString(txtHeight)
        parcel.writeString(txtDetails)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InfoCarDetailsDataModel> {
        override fun createFromParcel(parcel: Parcel): InfoCarDetailsDataModel {
            return InfoCarDetailsDataModel(parcel)
        }

        override fun newArray(size: Int): Array<InfoCarDetailsDataModel?> {
            return arrayOfNulls(size)
        }
    }
}