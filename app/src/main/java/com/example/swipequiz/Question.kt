package com.example.swipequiz

data class Question(
    var question: String,
    var isTrue: Boolean
) {
    companion object {
        val QUESTION_QUESTIONS = arrayOf(
            "1 + 1 = 2",
            "Java > Kotlin",
            "T && F = T",
            " F || F = T",
            "Pineapple is acceptable on pizza",
            "The earth is flat"
        )

        val QUESTION_IS_TRUE = arrayOf(
            true,
            false,
            false,
            false,
            true,
            false
        )
    }
}