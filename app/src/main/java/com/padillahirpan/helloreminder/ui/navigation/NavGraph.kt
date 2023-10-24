package com.padillahirpan.helloreminder.ui.navigation

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.padillahirpan.helloreminder.ui.page.home.HomeDestination
import com.padillahirpan.helloreminder.ui.page.home.HomeScreen
import com.padillahirpan.helloreminder.ui.page.note.CreateNoteDestination
import com.padillahirpan.helloreminder.ui.page.note.CreateNoteScreen
import com.padillahirpan.helloreminder.ui.page.note.DetailNoteScreen
import com.padillahirpan.helloreminder.ui.page.note.ItemDetailsDestination

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */


@Composable
fun NoteNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(CreateNoteDestination.route)
                },
                navigateToItemUpdate = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = CreateNoteDestination.route) {
            CreateNoteScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailNoteScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToEditNote = {
//                    navController.navigateUp()
                }
            )
        }
    }

}

data class NavigationItemContent(
    val homeMenu: HomeMenu,
    val icon: ImageVector,
    val text: String
)