package com.example.nycschools.utils

import android.net.Uri
import com.example.nycschools.model.School
import com.google.gson.Gson

object Routes {
    const val SCHOOL_LIST = "school_list"
    const val SCHOOL_DETAIL = "school_detail/{schoolJson}"
    const val SCHOOL_DETAIL_ARG = "schoolJson"

    fun schoolDetailRoute(school: School) : String {
        val schoolJson = Uri.encode(Gson().toJson(school))

        return SCHOOL_DETAIL.replace("{schoolJson}", schoolJson)
    }
}