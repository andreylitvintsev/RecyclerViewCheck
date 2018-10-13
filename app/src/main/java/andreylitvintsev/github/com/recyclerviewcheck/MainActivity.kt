package andreylitvintsev.github.com.recyclerviewcheck

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val blankListFragment = BlankListFragment().apply {
            val bundle = Bundle()
            bundle.putInt(BlankListFragment.ITEM_COUNT_ARGUMENT, 20)
            arguments = bundle
        }

        supportFragmentManager.beginTransaction()
                .add(android.R.id.content, blankListFragment)
                .addToBackStack(null)
                .commit()
    }

}
