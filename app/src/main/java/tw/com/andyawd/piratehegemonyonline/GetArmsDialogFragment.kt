package tw.com.andyawd.piratehegemonyonline

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class GetArmsDialogFragment : DialogFragment() {

    private var activity: Activity? = null
    private var mView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as Activity
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.activity = activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val dialog = super.onCreateDialog(savedInstanceState)
        val window = dialog.window
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.dialogfragment_get_arms, container)
        mView?.let { initComponent(it) }

        setBackgroundTransparent()

        return mView
    }

    private fun initComponent(view: View) {

    }

    private fun setBackgroundTransparent() {
        val window = dialog?.window

        window?.setBackgroundDrawableResource(R.color.transparent)
        val attributes = window?.attributes
        window?.attributes = attributes
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            super.show(manager, tag)
        } catch (e: Exception) {
            return
        }
    }

    override fun dismiss() {
        try {
            super.dismiss()
        } catch (e: Exception) {
            return
        }
    }
}