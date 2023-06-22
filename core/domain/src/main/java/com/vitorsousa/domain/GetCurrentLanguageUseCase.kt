package com.vitorsousa.domain

import java.util.Locale
import javax.inject.Inject

class GetCurrentLanguageUseCase @Inject constructor(
) {
    operator fun invoke(): String {
        val currentLanguage = Locale.getDefault().language

        return if (
            !currentLanguage.equals("pt", true) ||
            !currentLanguage.equals("en", true)
        ) {
            "EN"
        } else {
            currentLanguage
        }
    }

}
