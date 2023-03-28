package com.example.cardealapplication.dataModel

import android.os.Parcel
import android.os.Parcelable

data class InfoCarModelDataModel(val imgLogo : String,val txtCompanyName : String):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imgLogo)
        parcel.writeString(txtCompanyName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InfoCarModelDataModel> {
        override fun createFromParcel(parcel: Parcel): InfoCarModelDataModel {
            return InfoCarModelDataModel(parcel)
        }

        override fun newArray(size: Int): Array<InfoCarModelDataModel?> {
            return arrayOfNulls(size)
        }
    }
}
