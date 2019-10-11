package com.example.swipequiz

data class Question(
    var question: String,
    var isTrue: Boolean
) {
    companion object {
        val QUESTION_QUESTIONS = arrayOf(
            "Question 1",
            "Question 2"
        )

        val QUESTION_IS_TRUE = arrayOf(
            true,
            false
        )
    }
}