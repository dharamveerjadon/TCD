package com.jordan.signUp.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jordan.R
import com.jordan.signUp.model.LoginDataModel

class SignInViewModel : ViewModel() {
    var validationData: MutableLiveData<LoginDataModel> = MutableLiveData()

    fun validate(model: LoginDataModel, context: Context) {
        if (model.emailId.isEmpty()) {
            model.inValidField = context.getString(R.string.text_empty_email_invalid)
        } else if (model.password.isEmpty()) {
            model.inValidField = context.getString(R.string.text_empty_password_invalid)
        }
        validationData.value = model

    }
}