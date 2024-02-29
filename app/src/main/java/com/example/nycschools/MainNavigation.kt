package com.example.nycschools

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nycschools.model.School
import com.example.nycschools.ui.schooldetail.SchoolDetailScreen
import com.example.nycschools.ui.schools.SchoolListScreen
import com.example.nycschools.utils.Routes.SCHOOL_DETAIL
import com.example.nycschools.utils.Routes.SCHOOL_DETAIL_ARG
import com.example.nycschools.utils.Routes.SCHOOL_LIST
import com.google.gson.Gson

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SCHOOL_LIST ) {
        composable(route = SCHOOL_LIST) {
            SchoolListScreen(navController)
        }
        composable(route = SCHOOL_DETAIL, arguments = listOf(navArgument(SCHOOL_DETAIL_ARG){ type = NavType.StringType})) {
            //Decode the Json string back to a School object
            val schoolJson = requireNotNull(it.arguments?.getString(SCHOOL_DETAIL_ARG))
            val school = Gson().fromJson(Uri.decode(schoolJson), School::class.java)

            // Pass the decoded School object to the SchoolDetailScreen
            SchoolDetailScreen(school = school, navController = navController)
        }
    }

}