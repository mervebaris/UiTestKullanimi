package com.example.test

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testInitialState() {
        rule.setContent {
            Anasayfa()
        }

        // Eğer Text bileşeni spesifik bir içerik içermiyorsa, bir ContentDescription kullanabilirsiniz.
        rule.onNodeWithContentDescription("Gösterilen metin").assertExists()
    }

    @Test
    fun testTextUpdateOnClick() {
        rule.setContent {
            Anasayfa()
        }

        // Test etmek için girdi metnini tanımlayın
        val input = "Hello World"

        // OutlinedTextField'e metin girin
        rule
            .onNodeWithText("Metin giriniz")
            .performTextInput(input)

        // Metni güncellemek için butona tıklayın
        rule
            .onNodeWithText("Gönder")
            .performClick()

        // Gösterilen metnin girdi metniyle güncellendiğini doğrulayın
        rule
            .onNodeWithContentDescription("Gösterilen metin")
            .assertTextEquals(input)
    }


    @Test
    fun testAnasayfaEmptyTextField() {
        rule.setContent {
            Anasayfa()
        }

        // İlk durumda Text bileşeni boş olmalı
        rule
            .onNodeWithContentDescription("Gösterilen metin")
            .assertTextEquals("")

        // TextField'e tıklayıp bir şeyler girin, boş olmalı
        rule
            .onNodeWithText("Metin giriniz")
            .performTextInput("")

        // Button'a tıkladıktan sonra Text bileşeni hala boş olmalı
        rule
            .onNodeWithText("Gönder")
            .performClick()

        rule
            .onNodeWithContentDescription("Gösterilen metin")
            .assertTextEquals("")
    }

}
