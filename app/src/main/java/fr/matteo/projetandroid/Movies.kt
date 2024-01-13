package fr.matteo.projetandroid
import android.os.Parcel
import android.os.Parcelable

class Movies(
    val id: Int,
    val title: String,
    val description: String,
    val runTime: Int,
    val graphicUrl: String,
    val backdropUrl: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(runTime)
        parcel.writeString(graphicUrl)
        parcel.writeString(backdropUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movies> {
        override fun createFromParcel(parcel: Parcel): Movies {
            return Movies(parcel)
        }

        override fun newArray(size: Int): Array<Movies?> {
            return arrayOfNulls(size)
        }
    }
}
