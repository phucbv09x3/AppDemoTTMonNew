package com.monstar_lab_lifetime.appdemottmon.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.monstar_lab_lifetime.appdemottmon.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(),View.OnClickListener {

    private var mEmail =
        "^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$"
    private var mPassword = "[a-zA-Z0-9]+[@#$%^&*]+[a-zA-A-z0-9]+"
    private val REQUEST_CODE = 0// REQUEST_CODE là một giá trị int dùng để định danh mỗi request.
    // Khi nhận được kết quả, hàm callback sẽ trả về cùng REQUEST_CODE này để ta có thể xác định và xử lý kết quả. */

    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREF_MAILS = "PREF_MAILS"
        private const val PREF_PASSS = "PREF_PASSS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        button4.setOnClickListener(this)
        button33.setOnClickListener(this)
        loadDataSignIn()
        hint_pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (hint_pass.length() >= 15) {
                    hint_pass.error = "Khong duoc qua 15 ki tu"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun loadDataSignIn() {
        sharedPreferences = this.getSharedPreferences("signin", Context.MODE_PRIVATE)
        hint_email.setText(sharedPreferences.getString(PREF_MAILS, ""))
        hint_pass.setText(sharedPreferences.getString(PREF_PASSS, ""))
    }
    fun delay() {
        object : CountDownTimer(2500, 1000) {
            override fun onFinish() {
                var t = "0"
            }

            override fun onTick(millisUntilFinished: Long) {
                var t = "" + millisUntilFinished / 1000
                if (t.toInt() == 0) {
                    button4.isEnabled = true
                }
            }
        }.start()
    }

    override fun onClick(v: View?) {

        // button4.isEnabled=false
        when (v?.id) {
            R.id.button4 -> {
                checkSignIn()
                //delay()
            }
            R.id.button33 -> {
                onBackPressed()
            }

        }


    }

    private fun checkSignIn() {
        var getpass = hint_pass.text.toString()
        var getemail = hint_email.text.toString()
        if (!getpass.isEmpty() && getpass.matches(mPassword.toRegex()) && !getemail.isEmpty() && getemail.matches(
                mEmail.toRegex()
            )
        ) {

            sharedPreferences = this.getSharedPreferences("signin", Context.MODE_PRIVATE)
            var ed = sharedPreferences.edit()
            ed.putString(PREF_MAILS, hint_email.text.toString())
            ed.putString(PREF_PASSS, hint_pass.text.toString())

            ed.commit()
            val intentF = Intent(this, ContentActivity::class.java)
            intentF.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intentF.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intentF)
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        } else if (getemail.isEmpty()) {
            Toast.makeText(this, "Email rỗng ! ", Toast.LENGTH_SHORT)
        } else if (!getemail.matches(mEmail.toRegex())) {
            hint_email.error = "Email sai !"

        } else if (!getpass.matches(mPassword.toRegex())) {
            hint_pass.error = "Password sai!"
            //Toast.makeText(this, "Password chứa kí tự đặc biệt ! ", Toast.LENGTH_SHORT)
            // .show()

        } else if (getpass.isEmpty()) {
            //hint_pass.error = "Password không được trống !"
            Toast.makeText(this, "Password rỗng ! ", Toast.LENGTH_SHORT).show()
        }

    }

    fun clicktv(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        );
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                var resultMail = data!!.getStringExtra(SignUpActivity.DATA_MAIL)
                var resultPass = data.getStringExtra(SignUpActivity.DATA_PASS)
                //hint_email.setText(requireNotNull(data.getStringExtra(SignupActivity.DATA_MAIL),{"miss"}))
                //hint_pass.setText(requireNotNull(data.getStringExtra(SignupActivity.DATA_PASS),{" loi"}))
                hint_pass.setText(resultPass.toString())
                hint_email.setText(resultMail.toString())
            }
        }

    }

    fun clickFogot(view: View) {
        val intentView = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
        startActivity(intentView)
    }
}