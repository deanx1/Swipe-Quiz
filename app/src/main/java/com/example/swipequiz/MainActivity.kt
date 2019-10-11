package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    private fun initViews() {

        // Initialize the recycler view with a linear layout manager, adapter
        rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuestions)

        // Populate the questions list and notify the data set has changed.
        for (i in Question.QUESTION_QUESTIONS.indices) {
            questions.add(Question(Question.QUESTION_QUESTIONS[i], Question.QUESTION_IS_TRUE[i]))
        }
        questionAdapter.notifyDataSetChanged()

    }


    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down. Return false because it's not in use
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
//                questions.removeAt(position)
                checkAnswer(questions.get(position) ,direction)
                questionAdapter.notifyItemChanged(viewHolder.adapterPosition)

            }

            private fun checkAnswer(question: Question, direction: Int) {
                // If user swipes to the right
                if (direction == ItemTouchHelper.RIGHT) {
                    if (question.isTrue) {
                        snackbarCorrect(question.isTrue)
                    } else {
                        snackbarIncorrect(question.isTrue)
                    }
                    // Else if the user has swiped to the left
                } else {
                    if (!question.isTrue) {
                        snackbarCorrect(question.isTrue)
                    } else {
                        snackbarIncorrect(question.isTrue)
                    }
                }

            }

            private fun snackbarCorrect(isTrue: Boolean) {
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.correct_answer_text) + " " + isTrue, Snackbar.LENGTH_LONG).show();
            }

            private fun snackbarIncorrect(isTrue: Boolean) {
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.incorrect_answer_text) + " " + isTrue, Snackbar.LENGTH_LONG).show();
            }
        }
        return ItemTouchHelper(callback)
    }





}
