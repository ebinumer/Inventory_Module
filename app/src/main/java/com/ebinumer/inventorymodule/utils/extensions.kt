package com.ebinumer.inventorymodule.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun String.showToast(context: Context){
    Toast.makeText(context,this,Toast.LENGTH_SHORT).show()
}
fun String.showSnackBar(view: View){
    Snackbar.make(view,this,Snackbar.LENGTH_SHORT).show()
}


