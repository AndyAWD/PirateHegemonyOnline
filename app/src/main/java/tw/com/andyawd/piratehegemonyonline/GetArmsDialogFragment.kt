package tw.com.andyawd.piratehegemonyonline

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class GetArmsDialogFragment : DialogFragment() {

    private var activity: Activity? = null
    private var mView: View? = null
    private lateinit var sivDgaPictureBackground: ShapeableImageView

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
        sivDgaPictureBackground = view.findViewById(R.id.sivDgaPictureBackground)
        Glide.with(view).load(R.raw.janissary01).into(sivDgaPictureBackground)
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