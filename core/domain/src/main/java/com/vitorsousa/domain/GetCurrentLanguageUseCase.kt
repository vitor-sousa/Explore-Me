package com.vitorsousa.domain

import java.util.Locale
import javax.inject.Inject

class GetCurrentLanguageUseCase @Inject constructor(
    private val locale: Locale
) {
    operator fun invoke(): String {
        val currentLanguage = locale.language

        return if (
            currentLanguage.equals(PT, true) ||
            currentLanguage.equals(EN, true)
        ) {
            currentLanguage
        } else {
            EN
        }
    }

    companion object {
        const val PT = "pt"
        const val EN = "en"
    }
}
