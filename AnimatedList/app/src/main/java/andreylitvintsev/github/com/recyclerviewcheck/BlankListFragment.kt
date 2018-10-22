package andreylitvintsev.github.com.recyclerviewcheck

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast

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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.addItem -> {
                true
            }

            R.id.removeItem -> {
                true
            }

            R.id.addInRandomPosition -> {
                true
            }

            R.id.removeRandomItem -> {
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    inner class BlankListAdapter : RecyclerView.Adapter<BlankViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlankViewHolder {
            return BlankViewHolder(
                    layoutInflater.inflate(R.layout.item_list, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return this@BlankListFragment.itemCount
        }

        override fun onBindViewHolder(holder: BlankViewHolder, position: Int) {
            // Do nothing!
        }

    }

    class BlankViewHolder(parentView: View) : RecyclerView.ViewHolder(parentView)

}
