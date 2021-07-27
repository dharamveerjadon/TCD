package com.jordan.utils


import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jordan.R
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class Utils {

    companion object {

        const val BASE_URL: String = "https://newsapi.org/v2/"


        fun checkPermissions(context: Context, permission: String): Boolean {

            return ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED

        }



        private fun requestFocus(editText: View, activity: Activity) {
            try {
                if (editText.requestFocus()) {
                    activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
                }
            } catch (e: Exception) {
            }
        }

        fun hideKeyBoard(context: Context, view: View) {
            val imm: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }


        fun saveBitmapToFile(file: File): File? {
            try {

                // BitmapFactory options to downsize the image
                val o = BitmapFactory.Options()
                o.inJustDecodeBounds = true
                o.inSampleSize = 6
                // factor of downsizing the image

                var inputStream = FileInputStream(file)
                //Bitmap selectedBitmap = null;
                BitmapFactory.decodeStream(inputStream, null, o)
                inputStream.close()

                // The new size we want to scale to
                val requiredSize = 75

                // Find the correct scale value. It should be the power of 2.
                var scale = 1
                while (o.outWidth / scale / 2 >= requiredSize && o.outHeight / scale / 2 >= requiredSize) {
                    scale *= 2
                }

                val o2 = BitmapFactory.Options()
                o2.inSampleSize = scale
                inputStream = FileInputStream(file)

                val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
                inputStream.close()

                // here i override the original image file
                file.createNewFile()
                val outputStream = FileOutputStream(file)

                selectedBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)

                return file
            } catch (e: Exception) {
                return null
            }
        }

        fun sizeInKB(file: File?): Float {
            val size: Long = file!!.length() // length in bytes
            val sizeKB = (size / 1024).toFloat()
            return sizeKB
        }

        fun isNetworkAvailable(context: Context?): Boolean {
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager?.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        fun decodeFile(path: String?): Bitmap? {
            try { // Decode image size
                val o: BitmapFactory.Options = BitmapFactory.Options()
                o.inJustDecodeBounds = true
                BitmapFactory.decodeFile(path, o)
                // The new size we want to scale to
                val REQUIRED_SIZE = 100
                // Find the correct scale value. It should be the power of 2.
                var scale = 1
                while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE) scale *= 2
                // Decode with inSampleSize
                val o2: BitmapFactory.Options = BitmapFactory.Options()
                o2.inSampleSize = scale
                return BitmapFactory.decodeFile(path, o2)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            return null
        }

        fun sizeInMb(file: File?): Float {
            val size: Long = file!!.length() // length in bytes
            val sizeKB = size / 1024
            val sizeMB = (sizeKB / 1024).toFloat()
            return sizeMB
        }

        fun validateEditText(
            s: String,
            view: TextInputEditText,
            inputLayout: TextInputLayout,
            context: Context
        ): Boolean {

            if (view.text.toString().isEmpty()) {
                when (s) {
                    "id" -> inputLayout.error = context.getString(R.string.error_empty_email)
                    "pass" -> inputLayout.error = context.getString(R.string.error_empty_password)
                    "firstName" -> inputLayout.error =
                        context.getString(R.string.error_empty_first_name)
                    "lastName" -> inputLayout.error =
                        context.getString(R.string.error_empty_last_name)
                    "fullName" -> inputLayout.error =
                        context.getString(R.string.error_empty_full_name)
                    "mobile" -> inputLayout.error = context.getString(R.string.error_empty_mobile)
                    "newPass" -> inputLayout.error = context.getString(R.string.error_empty_newPass)
                    "confirmPass" -> inputLayout.error =
                        context.getString(R.string.error_empty_confirmPass)
                    "message" -> inputLayout.error =
                        context.getString(R.string.error_empty_message)
                }
                requestFocus(view, context as Activity)
                return false
            }
            inputLayout.isErrorEnabled = false
            return true
        }
    }


}