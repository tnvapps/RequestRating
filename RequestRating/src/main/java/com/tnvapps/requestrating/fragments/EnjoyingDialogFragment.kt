package com.tnvapps.requestrating.fragments

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.tnvapps.requestrating.APP_NAME
import com.tnvapps.requestrating.R
import com.tnvapps.requestrating.databinding.LayoutEnjoyingDialogFragmentBinding
import com.tnvapps.requestrating.interfaces.EnjoyingDialogFragmentListener
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.ios.IosEmojiProvider
import java.lang.IllegalStateException

class EnjoyingDialogFragment : DialogFragment(R.layout.layout_enjoying_dialog_fragment),
    View.OnClickListener {

    private var _binding: LayoutEnjoyingDialogFragmentBinding? = null
    private val binding: LayoutEnjoyingDialogFragmentBinding get() = _binding!!

    private lateinit var appName: String

    private var listener: EnjoyingDialogFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? EnjoyingDialogFragmentListener
        if (listener == null) {
            throw IllegalStateException("")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EmojiManager.install(IosEmojiProvider())

        appName = arguments?.getString(APP_NAME, "") ?: ""

        setStyle(STYLE_NO_TITLE, R.style.TransparentDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = LayoutEnjoyingDialogFragmentBinding.bind(view)

        binding.yesButton.setOnClickListener(this)
        binding.noButton.setOnClickListener(this)

        binding.titleTextView.text = getString(R.string.enjoying_app_name, appName)
        binding.messageTextView.text = getString(R.string.enjoying_message, appName)

        dialog?.setCancelable(false)
    }

    override fun onResume() {
        super.onResume()

        dialog?.let { dialog: Dialog ->
            val layoutParams = dialog.window?.attributes
            layoutParams?.apply {
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                dialog.window?.attributes = this
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.yes_button -> {
                dismiss()
                listener?.onDismissedEnjoyingDialogFragment(Activity.RESULT_OK)
            }
            R.id.no_button -> {
                dismiss()
                listener?.onDismissedEnjoyingDialogFragment(Activity.RESULT_CANCELED)
            }
        }
    }

    companion object {
        fun start(
            fragmentManager: FragmentManager,
            tag: String? = null,
            appName: String
            ) {
            val dialog = EnjoyingDialogFragment()
            dialog.arguments = bundleOf(
                APP_NAME to appName
            )
            dialog.show(fragmentManager, tag)
        }
    }

}