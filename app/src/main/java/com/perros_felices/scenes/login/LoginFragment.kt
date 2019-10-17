package com.perros_felices.scenes.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.perros_felices.R
import com.perros_felices.scenes.commons.LoginDialog
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener  {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            if (authenticationState == LoginViewModel.AuthenticationState.AUTHENTICATED) {
                //Hide keyboard.
                val imm =  context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)

                findNavController().navigate(R.id.action_loginFragment_to_main_board_fragment)
            }
            else if (authenticationState == LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION) showInvalidAuthenticationMessage()
        })
        regularLoginButton.setOnClickListener(this)
    }

    private fun showInvalidAuthenticationMessage() {
        Snackbar.make(this@LoginFragment.view!!, "Credentials are invalids", Snackbar.LENGTH_SHORT).show()
    }

    override fun onClick(p0: View?) {
        val loginDialog = LoginDialog()
        loginDialog.show(this.activity!!.supportFragmentManager, "LoginFragment")
    }
}