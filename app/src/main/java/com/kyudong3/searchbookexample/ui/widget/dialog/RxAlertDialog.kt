package com.kyudong3.searchbookexample.ui.widget.dialog

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.reactivex.rxjava3.core.Observable


class RxAlertDialog private constructor(context: Context) {

    private val builder = MaterialAlertDialogBuilder(context)

    private fun makeDialogBuilder() = builder

    fun setCancelable(cancelable: Boolean): RxAlertDialog {
        builder.setCancelable(cancelable)
        return this
    }

    fun setMessage(@StringRes resId: Int): RxAlertDialog {
        builder.setMessage(resId)
        return this
    }

    fun forObservable(
        @StringRes positiveResId: Int?,
        @StringRes negativeResId: Int? = null
    ): Observable<Boolean> = Observable.using(
        { makeDialogBuilder() },
        { builder ->
            Observable.create { emitter ->
                builder.setOnCancelListener {
                    emitter.onComplete()
                }
                builder.setOnDismissListener {
                    emitter.onComplete()
                }

                positiveResId?.let {
                    builder.setPositiveButton(it, null)
                }

                negativeResId?.let {
                    builder.setNegativeButton(it, null)
                }

                val alertDialog = builder.show()

                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { _ ->
                    emitter.onNext(true)
                    emitter.onComplete()
                    alertDialog.dismiss()
                }

                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener { _ ->
                    emitter.onNext(false)
                    emitter.onComplete()
                    alertDialog.dismiss()
                }

                emitter.setCancellable {
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(null)
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(null)
                }
            }
        },
        {
            Log.d("LOG", "disposed")
        }
    )

    companion object {
        fun from(context: Context) = RxAlertDialog(context)
    }
}
