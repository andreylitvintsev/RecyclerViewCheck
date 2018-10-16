package andreylitvintsev.github.com.recyclerviewcheck

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class BlankListFragment : ListFragment<BlankListFragment.BlankViewHolder>() {

    companion object {
        val ITEM_COUNT_ARGUMENT = "ItemCount"
    }

    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            count = arguments!!.getInt(ITEM_COUNT_ARGUMENT)
        }
    }

    class BlankViewHolder(parentView: View) : RecyclerView.ViewHolder(parentView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlankViewHolder {
        return BlankViewHolder(
                layoutInflater.inflate(R.layout.item_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(holder: BlankViewHolder, position: Int) {
        // Do nothing!
    }

}