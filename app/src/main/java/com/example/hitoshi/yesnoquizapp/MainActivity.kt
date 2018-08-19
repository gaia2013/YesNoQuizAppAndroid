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
/*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("判定")
        alertDialogBuilder.setMessage("")
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public fun onClick(DialogInterface dialog, int which) {

                    }
                })
        alertDialogBuilder.show()
*/
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

//    private fun showToast(msg: String) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//    }

}

//protected fun onCreate(savedInstanceState: Bundle) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_top)

//    // ダイアログを表示する
//    val newFragment = TestDialogFragment()
//    newFragment.show(getFragmentManager(), "test")
//}


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




/*
class JudgeDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }
}
*/

/*
class TestDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("ダイアログ")
                .setPositiveButton("はい") { dialog, id ->
                    // FIRE ZE MISSILES!
                }
                .setNegativeButton("キャンセル") { dialog, id ->
                    // User cancelled the dialog
               }
        // Create the AlertDialog object and return it
        return buildert.create()
    }
}
*/

/*
@override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState)
    setContentView(R.lyaout.activity_main)
    AlertDialog.Builder alert = new AlertDialog.Builder(this)
    alert.setTitle("こんにちは")
    alert.setMessage("ダイアログです。")
    alert.show()
}
*/

/*
public static class MyAlertDialogFragment extends DialogFragment {
    public static MyAlertDialogFragment newInstance(int title) {
        MyAlertDialogFragment frag = new MyAlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title,", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.alert_dialog_icon)
                .setTitle(title)
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((FragmentAlertDialog) getActivity ()).doNegativeClick();
                            }
                        }
                )
                .create();
    }

    void showDialog() {
        DialogFragment newFragment = MyAlertDialogFragment.newInstance(
                R.string.alert_dialog_two_buttons_title);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void doPositiveClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Positive click!");
    }

    public void doNegativeClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Negative click!");
    }
}
*/


/*
class ConfirmDialog : DialogFragment() {

    var title = "title"
    var msg = "msg"
    var okText = "OK"
    var cancelText = "cancel"
    /** ok押下時の挙動 */
    var onOkClickListener : DialogInterface.OnClickListener? = null
    /** cancel押下時の挙動 デフォルトでは何もしない */
    var onCancelClickListener : DialogInterface.OnClickListener? = DialogInterface.OnClickListener { _, _ -> }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton(okText, onOkClickListener)
                .setNegativeButton(cancelText, onCancelClickListener)
        // Create the AlertDialog object and return it
        return builder.create()
    }

    override fun onPause() {
        super.onPause()
        // onPause でダイアログを閉じる場合
        dismiss()
    }
}
*/
