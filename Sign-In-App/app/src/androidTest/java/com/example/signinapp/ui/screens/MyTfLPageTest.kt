package com.example.signinapp.ui.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.signinapp.navigation.Navigation
import com.example.signinapp.navigation.Screen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyTfLPageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun myTflScreen() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            Navigation(navController = navController)
        }
    }

        @Test
        fun loginPage_successfulLogin_signOut() {
            composeTestRule.apply {
                onNodeWithTag(ID_TEXT_FIELD_TAG).performTextInput(USER_NAME)
                onNodeWithTag(PW_TEXT_FIELD_TAG).performTextInput(CORRECT_PASSWORD)
                onNodeWithText(SIGN_IN).performClick()
                assertEquals(Screen.MyTfLScreen.route, navController.currentBackStackEntry?.destination?.route)

                onNodeWithText(NAME_LABEL).assertIsDisplayed()
                onNodeWithText(ROLE_LABEL).assertIsDisplayed()
                onNodeWithText(OFFICE).assertIsDisplayed()

                onNodeWithText(SIGN_OUT).performClick()

                onNode(isDialog()).assertExists()
                onNodeWithText(SIGN_OUT_ALERT_TITLE).assertIsDisplayed()
                onNodeWithText(SIGN_OUT_ALERT_MESSAGE).assertIsDisplayed()
                onNodeWithText(CANCEL).assertIsDisplayed()

                onAllNodesWithText(SIGN_OUT)
                    .filter(hasClickAction())
                    .apply {
                        fetchSemanticsNodes().forEachIndexed { i, _ ->
                            get(i).performClick()
                        }
                    }
                onNode(isDialog()).assertDoesNotExist()
                assertEquals(Screen.LoginScreen.route, navController.currentBackStackEntry?.destination?.route)
            }
        }


    private companion object{
        const val SIGN_IN = "Sign in"
        const val SIGN_OUT = "Sign Out"
        const val USER_NAME = "emma@tfl.gov.uk"
        const val CORRECT_PASSWORD = "password123"
        const val ID_TEXT_FIELD_TAG = "idTextFieldTag"
        const val PW_TEXT_FIELD_TAG = "passwordTextFieldTag"
        const val SIGN_OUT_ALERT_TITLE = "Sign Out?"
        const val SIGN_OUT_ALERT_MESSAGE = "Are you sure you want to sign out?"
        const val CANCEL = "Cancel"
        const val NAME_LABEL = "Emma Smith"
        const val ROLE_LABEL = "Senior Test Analyst"
        const val OFFICE = "Pier Walk"
    }
}