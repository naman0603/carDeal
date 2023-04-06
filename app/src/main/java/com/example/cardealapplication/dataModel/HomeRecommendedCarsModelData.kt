package com.example.cardealapplication.dataModel

import android.os.Parcel
import android.os.Parcelable

data class HomeRecommendedCarsModelData(val imgLogo : String,val imgCarImage:String,val txtName: String, val txtType :String,
                                        val txtPrice : String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imgLogo)
        parcel.writeString(imgCarImage)
        parcel.writeString(txtName)
        parcel.writeString(txtType)
        parcel.writeString(txtPrice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeRecommendedCarsModelData> {
        override fun createFromParcel(parcel: Parcel): HomeRecommendedCarsModelData {
            return HomeRecommendedCarsModelData(parcel)
        }

        override fun newArray(size: Int): Array<HomeRecommendedCarsModelData?> {
            return arrayOfNulls(size)
        }
    }
}
