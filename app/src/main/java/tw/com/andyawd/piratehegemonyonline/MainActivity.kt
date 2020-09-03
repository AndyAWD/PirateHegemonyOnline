package tw.com.andyawd.piratehegemonyonline

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import tw.com.andyawd.andyawdlibrary.AWDConstants
import tw.com.andyawd.andyawdlibrary.AWDLog

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var sharedPreferences: SharedPreferences
    private var arms = ArmsBean()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initListener()
        initClickListener()
    }

    private fun initComponent() {
        AWDLog.setLogLevel(AWDConstants.LOG_VERBOSE)

        database = FirebaseDatabase.getInstance().reference
        sharedPreferences =
            getSharedPreferences(BaseConstants.PIRATE_HEGEMONY_ONLINE, Context.MODE_PRIVATE)

        setGetLineInfantryCountText(arms.count)
    }


    private fun initListener() {
        database.addValueEventListener(databaseValueEventListener)
    }

    private fun initClickListener() {

        mbAmGetLineInfantry.setOnClickListener {

            val bundle = Bundle()
            bundle.putLong(BaseConstants.COUNT, sharedPreferences.getLong(BaseConstants.COUNT, 0))

            val getArmsDialogFragment = GetArmsDialogFragment()
            getArmsDialogFragment.arguments = bundle
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

    private fun setGetLineInfantryCountText(count: Long) {
        mtvAmGetLineInfantryCount.text = resources.getString(
            R.string.line_infantry_already_get_count,
            count.toString()
        )
    }

    private val databaseValueEventListener: ValueEventListener = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            setGetLineInfantryCountText(0L)
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                arms = snapshot.getValue(ArmsBean::class.java)!!

                setGetLineInfantryCountText(arms.count)
            } catch (e: Exception) {
                setGetLineInfantryCountText(0L)
            }
        }
    }
}
