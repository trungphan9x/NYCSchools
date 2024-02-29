package com.example.nycschools.model

import com.google.gson.annotations.SerializedName

data class School(
    @SerializedName("dbn") val dbn : String,
    @SerializedName("school_name") val name : String = "",
    @SerializedName("overview_paragraph") val overview : String = "",
    @SerializedName("primary_address_line_1") val primaryAddress : String = "",
    @SerializedName("city") val city : String = "",
    @SerializedName("state_code") val stateCode : String = "",
    @SerializedName("zip") val zip : String = ""
    ) {

    fun getAddress() : String {
        return "$primaryAddress, $city, $stateCode, $zip"
    }
}