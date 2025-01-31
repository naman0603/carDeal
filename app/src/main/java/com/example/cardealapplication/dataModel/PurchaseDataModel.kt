package com.example.cardealapplication.dataModel

import android.os.Parcel
import android.os.Parcelable

    class PurchaseDataModel(val img :String, val Price:String, val Model:String, val Name:String,
                        val Phone:String, val txtCarName:String,val txtCity:String,val txtColor:String,
                        val txtFuelType:String,val txtInsurance:String,val txtKms:String,val txtOwners:String,
                        val txtRegisteredState:String, val txtTransmission:String,val txtId : String):Parcelable {
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
    )

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
        parcel.writeString(txtId)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PurchaseDataModel> {
        override fun createFromParcel(parcel: Parcel): PurchaseDataModel {
            return PurchaseDataModel(parcel)
        }

        override fun newArray(size: Int): Array<PurchaseDataModel?> {
            return arrayOfNulls(size)
        }
    }

}