package com.perros_felices.scenes.commons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.perros_felices.R
import com.perros_felices.scenes.login.LoginViewModel
import kotlinx.android.synthetic.main.dialog_login.*
import kotlinx.android.synthetic.main.dialog_login.view.*

class LoginDialog : DialogFragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.dialog_login, container, false)
        this.dialog!!.setCanceledOnTouchOutside(true)
        this.isCancelable = true

        view.okTextView.setOnClickListener {
            if (addressEditText.text.isNotBlank() && passwordEditText.text.isNotBlank()) {
                viewModel.authenticate(addressEditText.text.toString(), passwordEditText.text.toString())
                this.dismiss()
            }
        }

        return view
    }
}