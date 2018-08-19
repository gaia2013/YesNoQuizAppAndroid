package com.example.hitoshi.yesnoquizapp


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.support.v4.app.DialogFragment
import android.app.Dialog
import android.app.AlertDialog
import android.content.DialogInterface

class MainActivity : AppCompatActivity() {

    companion object {
        private val questions: List<Pair<String, Boolean>> = listOf(
                "Androidアプリを開発する統合環境は、Android Garageである" to false,
                "Android Studioには、Logcat Windowがある" to true,
                "TextViewは、ユーザーが編集可能なテキストフィールド機能を提供する" to false
        )
    }

    private var currentQuestionIdx: Int = 0

    private lateinit var questionLabel: TextView
    private lateinit var yesButton: Button
    private lateinit var noButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionLabel = findViewById(R.id.question_label)
        yesButton = findViewById(R.id.yes_button)
        noButton = findViewById(R.id.no_button)

        yesButton.setOnClickListener {
            checkQuestion(true)
        }

        noButton.setOnClickListener {
            checkQuestion(false)
        }

        showQuestion()
    }

    private fun checkQuestion(yourAnswer: Boolean) {
        val question = questions[currentQuestionIdx]
        // アクティビティではインスタンスを生成して、showメソッドを実施するだけ
        var dialog: TestDialog = TestDialog()

        if (yourAnswer == question.second) {
            // 正解
            currentQuestionIdx++ // 次の問題へ進める
            // showToast("正解!")
            dialog.show(supportFragmentManager, "正解！")
//            JudgeDialogFragment().show(supportFragmentManager, "JudgeFragement")
        } else {
            // 不正解
            // showToast("不正解...")
            dialog.show(supportFragmentManager, "不正解…")
//            JudgeDialogFragment().show(supportFragmentManager, "JudgeFragement")
        }

        // 最後の問題だった場合、最初の問題へ戻す
        if (currentQuestionIdx >= questions.size) {
            currentQuestionIdx = 0
        }

        showQuestion()

    }

    private fun showQuestion() {
        val question = questions[currentQuestionIdx]
        questionLabel.text = question.first
    }

    /*
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    */

}

// DialogFragmentを継承したクラスを作る
class TestDialog() : DialogFragment() {
    override fun onCreateDialog(saveInstanceState: Bundle?): Dialog {
        // ここでAlertDialogを利用してダイアログを作る
        // AlertDialog.Builder(this.requireContext())
        // val dialogBuilder = AlertDialog.Builder(activity!!)
        val dialogBuilder =
                AlertDialog.Builder(this.requireContext())
        // AlertDialog.Builder(this)
                .setTitle("判定")
                .setMessage(tag)
                .setPositiveButton("ok"){ dialog, which ->
                }
        // 引数にnullableは適用できないので!!でnot nullであることを指定しておく
        // ここでAlertDialogに関する処理をしてCreateを返り値で渡してあげるだけ
        return dialogBuilder.create()
    }
}