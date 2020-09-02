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

            writeArmsCount()

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

    private fun writeArmsCount() {

        //物件為初始值0，表示資料還沒寫入，不執行
        if (arms.count == 0L) {
            return
        }

        var yourLineInfantryCount = sharedPreferences.getLong(BaseConstants.COUNT, 0)

        //sharedPreferences不為0，表示已經有資料，不執行
        if (yourLineInfantryCount != 0L) {
            return
        }

        yourLineInfantryCount = arms.count + 1

        sharedPreferences.edit().putLong(BaseConstants.COUNT, yourLineInfantryCount).apply()

        arms = ArmsBean().apply {
            this.name = resources.getString(R.string.line_infantry)
            this.level = resources.getString(R.string.capital_letters_s)
            this.count = yourLineInfantryCount
        }

        database.setValue(arms)
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
