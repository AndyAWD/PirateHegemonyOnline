package tw.com.andyawd.piratehegemonyonline

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAIWAN_TRILOGY = "https://taiwantrilogy.com/"
        const val IGOTALLDAY_MOVIE_LINK = "https://www.youtube.com/watch?v=LUaYe_7cmxQ"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initClickListener()
    }

    private fun initClickListener() {

        mbTaiwanTrilogy.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(TAIWAN_TRILOGY)
            startActivity(intent)
        }

        mbAmMovieLink.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(IGOTALLDAY_MOVIE_LINK)
            startActivity(intent)
        }

        mbAmExit.setOnClickListener {
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}