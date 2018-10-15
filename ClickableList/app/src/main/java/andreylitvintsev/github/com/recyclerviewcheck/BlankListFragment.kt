package andreylitvintsev.github.com.recyclerviewcheck

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
            adapter = BlankListAdapter()
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
            holder.bind(position)
        }

    }

    inner class BlankViewHolder(parentView: View) : RecyclerView.ViewHolder(parentView), View.OnClickListener {

        private var numblerItemView: TextView = parentView.findViewById(R.id.numberItemView)

        init {
            Log.d("TAG", "Hello from init")
            parentView.setOnClickListener(this)
        }

        fun bind(position: Int) {
            numblerItemView.text = position.toString()
        }

        override fun onClick(v: View?) {
            val toastMessage = "You clicked on ${numblerItemView.text}"
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }

    }

}