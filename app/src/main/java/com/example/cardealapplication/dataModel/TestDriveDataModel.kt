package com.example.cardealapplication.dataModel

import android.os.Parcel
import android.os.Parcelable

data class TestDriveDataModel(val img : String,val txtCarName : String,val price:String,val address : String,val phone : String,
                              val date : String,val time : String,val uid : String,val city :String,val state : String,val id : String,
                              val collectionId : String):Parcelable {
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
        parcel.readString()!!

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
        parcel.writeString(txtCarName)
        parcel.writeString(price)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeString(uid)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(id)
        parcel.writeString(collectionId)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TestDriveDataModel> {
        override fun createFromParcel(parcel: Parcel): TestDriveDataModel {
            return TestDriveDataModel(parcel)
        }

        override fun newArray(size: Int): Array<TestDriveDataModel?> {
            return arrayOfNulls(size)
        }
    }
}
