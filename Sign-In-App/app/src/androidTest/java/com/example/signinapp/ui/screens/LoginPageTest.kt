package com.example.signinapp.ui.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
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
class LoginPageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun loginScreen(){
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            Navigation(navController = navController)
        }
    }

    @Test
    fun loginPage_itemsShown() {
        composeTestRule.apply {
            onNodeWithContentDescription(TRANSPORT_FOR_LONDON_LOGO).assertIsDisplayed()
            onNodeWithText(TFL_ID).assertIsDisplayed()
            onNodeWithTag(ID_TEXT_FIELD_TAG).assertIsDisplayed()
            onNodeWithText(PASSWORD).assertIsDisplayed()
            onNodeWithTag(PW_TEXT_FIELD_TAG).assertIsDisplayed()
            onNodeWithText(SIGN_IN).assertIsDisplayed()
        }
    }

    @Test
    fun loginPage_failedLogin() {
        composeTestRule.apply {
            onNodeWithTag(ID_TEXT_FIELD_TAG).performTextInput(USER_NAME)
            onNodeWithTag(PW_TEXT_FIELD_TAG).performTextInput(WRONG_PASSWORD)
            onNodeWithText(SIGN_IN).performClick()
            onNode(isDialog()).assertExists()
            onNodeWithText(LOGIN_ERROR_DIALOG_TITLE).assertIsDisplayed()
            onNodeWithText(LOGIN_ERROR_DIALOG_ERROR_MESSAGE).assertIsDisplayed()
            onNodeWithText(DONE).assertIsDisplayed()
            onNodeWithText(DONE).performClick()
            onNode(isDialog()).assertDoesNotExist()
        }
    }

    @Test
    fun loginPage_successfulLogin() {
        composeTestRule.apply {
            onNodeWithTag(ID_TEXT_FIELD_TAG).performTextInput(USER_NAME)
            onNodeWithTag(PW_TEXT_FIELD_TAG).performTextInput(CORRECT_PASSWORD)
            onNodeWithText(SIGN_IN).performClick()
            onNode(isDialog()).assertDoesNotExist()
            assertEquals(Screen.MyTfLScreen.route, navController.currentBackStackEntry?.destination?.route)

            onNodeWithText(NAME_LABEL).assertIsDisplayed()
            onNodeWithText(ROLE_LABEL).assertIsDisplayed()
            onNodeWithText(OFFICE).assertIsDisplayed()
        }
    }

    private companion object{
        const val TRANSPORT_FOR_LONDON_LOGO = "Transport For London Logo"
        const val TFL_ID = "TFL ID"
        const val PASSWORD = "Password"
        const val SIGN_IN = "Sign in"
        const val USER_NAME = "emma@tfl.gov.uk"
        const val WRONG_PASSWORD = "password"
        const val CORRECT_PASSWORD = "password123"
        const val ID_TEXT_FIELD_TAG = "idTextFieldTag"
        const val PW_TEXT_FIELD_TAG = "passwordTextFieldTag"
        const val LOGIN_ERROR_DIALOG_TITLE = "Login Error"
        const val LOGIN_ERROR_DIALOG_ERROR_MESSAGE = "Looks like either your Username or Password is incorrect. Please try again."
        const val DONE = "Done"
        const val NAME_LABEL = "Emma Smith"
        const val ROLE_LABEL = "Senior Test Analyst"
        const val OFFICE = "Pier Walk"
    }
}