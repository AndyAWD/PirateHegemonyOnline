package tw.com.andyawd.piratehegemonyonline

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initClickListener()
    }

    private fun initComponent() {
        database = FirebaseDatabase.getInstance().reference
    }

    private fun initClickListener() {

        mbAmGetLineInfantry.setOnClickListener {
            val getArmsDialogFragment = GetArmsDialogFragment()
            getArmsDialogFragment.show(
                supportFragmentManager,
                BaseConstants.GET_ARMS_DIALOG_FRAGMENT
            )
        }

        mbTaiwanTrilogy.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(BaseConstants.TAIWAN_TRILOGY)
            startActivity(intent)
        }

        mbAmMovieLink.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(BaseConstants.IGOTALLDAY_MOVIE_LINK)
            startActivity(intent)
        }

        mbAmExit.setOnClickListener {
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}