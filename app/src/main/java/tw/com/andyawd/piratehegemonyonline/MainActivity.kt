package tw.com.andyawd.piratehegemonyonline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initClickListener()
    }

    private fun initClickListener() {

        mbAmExit.setOnClickListener {
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}