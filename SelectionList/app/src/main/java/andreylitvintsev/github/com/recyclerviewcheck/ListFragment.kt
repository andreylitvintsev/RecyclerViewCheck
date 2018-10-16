package andreylitvintsev.github.com.recyclerviewcheck

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


open abstract class ListFragment<VH : RecyclerView.ViewHolder> : Fragment() {

    private var recyclerView: RecyclerView? = null

    protected var adapter : RecyclerView.Adapter<in VH>? // TODO: what is 'in'
        get() = recyclerView?.adapter
        set(value) { recyclerView?.adapter = value }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false).also {
            recyclerView = it.findViewById(R.id.recyclerView)
            recyclerView?.setHasFixedSize(true)
            recyclerView?.layoutManager = LinearLayoutManager(context!!)

            adapter = ListAdapter()
        }
    }

    inner class ListAdapter : RecyclerView.Adapter<VH>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return this@ListFragment.onCreateViewHolder(parent, viewType)
        }

        override fun getItemCount(): Int {
            return this@ListFragment.getItemCount()
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            this@ListFragment.onBindViewHolder(holder, position)
        }
    }

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    abstract fun getItemCount(): Int

    abstract fun onBindViewHolder(holder: VH, position: Int)

}