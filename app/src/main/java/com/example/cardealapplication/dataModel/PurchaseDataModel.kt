package com.example.cardealapplication.dataModel

import android.os.Parcel
import android.os.Parcelable

class PurchaseDataModel(val img :String, val txtCarModel:String, val txtManYear:String, val txtCarPrice:String,
                        val txtPhone:String, val txtCarBrand:String,val txtCarVariant:String,val txtState:String,
                        val txtInsurance:String,val txtTransmission:String,val txtOwners:String,val txtColor:String,
                        val txtKms:String, val txtAddress:String,val txtName:String,val txtCity:String) : Parcelable {
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
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
        parcel.writeString(txtCarModel)
        parcel.writeString(txtManYear)
        parcel.writeString(txtCarPrice)
        parcel.writeString(txtPhone)
        parcel.writeString(txtCarBrand)
        parcel.writeString(txtCarVariant)
        parcel.writeString(txtState)
        parcel.writeString(txtInsurance)
        parcel.writeString(txtTransmission)
        parcel.writeString(txtOwners)
        parcel.writeString(txtColor)
        parcel.writeString(txtKms)
        parcel.writeString(txtAddress)
        parcel.writeString(txtName)
        parcel.writeString(txtCity)

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