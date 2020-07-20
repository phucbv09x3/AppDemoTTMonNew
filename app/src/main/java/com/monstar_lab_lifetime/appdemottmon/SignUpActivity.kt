package com.monstar_lab_lifetime.appdemottmon

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlin.String

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private var mEmail =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    private var mPassword = "[a-zA-Z0-9]+[@#$%^&*]+[a-zA-A-z0-9]+"
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val PREF_MAIL = "PREF_MAIL"
        const val PREF_PASS = "PREF_PASS"
        const val PREF_NAME = "PREF_NAME"
        const val DATA_MAIL = "DATA_MAIL"
        const val DATA_PASS = "DATA_PASS"
    }
//    companion object {
//        const val PREF_NAME="PREF_NAME"
//        const val PREF_MAIL="PREF_MAIL"
//        const val PREF_PASS="PREF_PASS"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        button_singup.setOnClickListener(this)
        buttonback.setOnClickListener(this)
        hint_passwordsignup.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (hint_passwordsignup.length()>=15){
                    hint_passwordsignup.error="Mật khẩu không được quá 15 kí tự"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
//       val sig=SignInActivity
//        val sharedPreferences:SharedPreferences
//        sharedPreferences = this.getSharedPreferences("test", Context.MODE_PRIVATE)
//        hint_emailsignup.setText(sharedPreferences.getString(sig.PREF_EMAIL,""))

        hint_passwordsignup.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (hint_passwordsignup.length() >= 14) {
                    hint_passwordsignup.error = "khong duoc qua 15 ki tu"
                }
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_singup -> {
                var getpass: String = hint_passwordsignup.text.toString()
                var getemail = hint_emailsignup.text.toString()
                var getfullName = hint_fullName.text.toString()
                if (!getpass.isEmpty() && getpass.matches(mPassword.toRegex()) && !getemail.isEmpty() && getemail.matches(
                        mEmail.toRegex()
                    ) && !getfullName.isEmpty()
                ) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    val data = Intent()
                    data.putExtra(DATA_MAIL, getemail)
                    data.putExtra(DATA_PASS, getpass)
                    setResult(Activity.RESULT_OK, data)
                    finish()

//                    sharedPreferences = this.getSharedPreferences("save_data", Context.MODE_PRIVATE)
//                    val ed = sharedPreferences.edit()
//                    ed.putString(PREF_NAME, getfullName)
//                    ed.putString(PREF_MAIL, getemail)
//                    ed.putString(PREF_PASS, getpass)
//                    ed.putString(PREF_NAME,getfullName)
//                    ed.commit()
                } else {
                    if (getfullName.isEmpty()) {
                        Toast.makeText(this, "Fullname không được trống !", Toast.LENGTH_SHORT)
                            .show()
                    } else if (getemail.isEmpty()) {
                        Toast.makeText(this, "Email không được trống ! ", Toast.LENGTH_SHORT).show()
                    } else if (!getemail.matches(mEmail.toRegex())) {
                        Toast.makeText(this, "Email sai cú pháp ! ", Toast.LENGTH_SHORT).show()
                    } else if (!getpass.matches(mPassword.toRegex())) {
                        Toast.makeText(this, "Password chứa kí tự đặc biệt ! ", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                setResult(Activity.RESULT_CANCELED)
            }
            R.id.buttonback -> {
                onBackPressed()
            }
        }
    }


}
