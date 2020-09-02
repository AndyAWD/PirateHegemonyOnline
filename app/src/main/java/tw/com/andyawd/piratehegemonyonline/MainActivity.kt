package tw.com.andyawd.piratehegemonyonline

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import tw.com.andyawd.andyawdlibrary.AWDConstants
import tw.com.andyawd.andyawdlibrary.AWDLog

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initListener()
        initClickListener()
    }

    private fun initComponent() {
        database = FirebaseDatabase.getInstance().reference

        AWDLog.setLogLevel(AWDConstants.LOG_VERBOSE)
    }


    private fun initListener() {
        database.addValueEventListener(databaseValueEventListener)
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

    private fun writeNewArms(name: String, level: String, count: Long) {
        val arms = ArmsBean().apply {
            this.name = name
            this.level = level
            this.count = count
        }

        database.setValue(arms)
    }

    private val databaseValueEventListener: ValueEventListener = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {

        }

        override fun onDataChange(snapshot: DataSnapshot) {
            val armsBean = snapshot.getValue(ArmsBean::class.java)

            mtvAmGetLineInfantryCount.text = resources.getString(
                R.string.line_infantry_already_get_count,
                armsBean?.count.toString()
            )
        }
    }
}
