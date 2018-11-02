package andreylitvintsev.github.com.recyclerviewcheck

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast


enum class ItemViewType(val value: Int) {
    HEADER(0), REGULAR(1)
}

class BlankListFragment : ListFragment<BlankListFragment.BlankViewHolder>() {

    companion object {
        val ITEM_COUNT_ARGUMENT = "ItemCount"
    }

    private var itemCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            itemCount = arguments!!.getInt(ITEM_COUNT_ARGUMENT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState).also {
            setHasOptionsMenu(true)

            adapter = BlankListAdapter()
        }
    }

    inner class BlankListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
            ItemViewType.HEADER.value -> HeaderViewHolder(
                    layoutInflater.inflate(R.layout.header_list, parent, false)
            )

            else -> BlankViewHolder(
                    layoutInflater.inflate(R.layout.item_list, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return this@BlankListFragment.itemCount
        }

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ItemViewType.HEADER.value

            else -> ItemViewType.REGULAR.value
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = Unit

    }

    class HeaderViewHolder(parentView: View) : RecyclerView.ViewHolder(parentView)
    class BlankViewHolder(parentView: View) : RecyclerView.ViewHolder(parentView)

}
