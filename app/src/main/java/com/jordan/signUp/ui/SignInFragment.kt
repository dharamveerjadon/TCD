package com.jordan.signUp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jordan.LoginActivity
import com.jordan.MainActivity
import com.jordan.R
import com.jordan.home.viewmodel.CommonViewModel
import com.jordan.signUp.model.LoginDataModel
import com.jordan.utils.ManageFragment
import com.jordan.utils.Utils
import kotlinx.android.synthetic.main.sign_in_fragment.*

class SignInFragment : Fragment(), View.OnClickListener {


    companion object {
        fun newInstance() = SignInFragment()
    }

    private val viewModel: SignInViewModel by lazy { ViewModelProvider(this)[SignInViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_log_in.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_log_in -> {
                val dataModel = LoginDataModel(
                    edt_mail.text.toString(),
                    edt_password.text.toString(),
                    ""
                )

                viewModel.validate(model = dataModel, requireContext())
                viewModel.validationData.observe(this, {
                    if (it != null && it.inValidField == "") {
                        requireContext().startActivity(Intent(requireContext(), MainActivity::class.java))
                        activity?.finish()

                    } else {
                        if (it.inValidField == getString(R.string.text_empty_email_invalid)) {
                            Utils.validateEditText(
                                s = "id",
                                view = edt_mail,
                                inputLayout = til_mail,
                                context = requireContext()
                            )
                        } else {
                            Utils.validateEditText(
                                s = "pass",
                                view = edt_password,
                                inputLayout = til_password,
                                context = requireContext()
                            )
                        }
                    }
                })
                viewModel.validationData.removeObservers(this)
            }
        }

    }
}