package com.example.cardealapplication.dataModel

import android.os.Parcel
import android.os.Parcelable

class MyCarsDataModel(val img :String, val Price:String, val Model:String, val Name:String,
                      val Phone:String, val txtCarName:String,val txtCity:String,val txtColor:String,
                      val txtFuelType:String,val txtInsurance:String,val txtKms:String,val txtOwners:String,
                      val txtRegisteredState:String, val txtTransmission:String,val txtDocumentId : String, ) : Parcelable {
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
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
        parcel.writeString(Price)
        parcel.writeString(Model)
        parcel.writeString(Name)
        parcel.writeString(Phone)
        parcel.writeString(txtCarName)
        parcel.writeString(txtCity)
        parcel.writeString(txtColor)
        parcel.writeString(txtFuelType)
        parcel.writeString(txtInsurance)
        parcel.writeString(txtKms)
        parcel.writeString(txtOwners)
        parcel.writeString(txtRegisteredState)
        parcel.writeString(txtTransmission)
        parcel.writeString(txtDocumentId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyCarsDataModel> {
        override fun createFromParcel(parcel: Parcel): MyCarsDataModel {
            return MyCarsDataModel(parcel)
        }

        override fun newArray(size: Int): Array<MyCarsDataModel?> {
            return arrayOfNulls(size)
        }
    }
}