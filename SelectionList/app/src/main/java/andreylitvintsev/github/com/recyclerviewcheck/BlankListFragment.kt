package andreylitvintsev.github.com.recyclerviewcheck

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.ActionMode
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy


class BlankListFragment : ListFragment<BlankListFragment.BlankViewHolder>() {

    private lateinit var selectionTracker: SelectionTracker<Long>

    companion object {
        val ITEM_COUNT_ARGUMENT = "ItemCount"
    }

    private var count: Int = 0

    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            count = arguments!!.getInt(ITEM_COUNT_ARGUMENT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState).also {
            selectionTracker = SelectionTracker.Builder(
                    "selection",
                    recyclerView!!,
                    KeyProvider(),
                    DetailsLookup(),
                    StorageStrategy.createLongStorage()
            ).build()

            selectionTracker.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    if (actionMode == null) {
                        actionMode = (activity as? AppCompatActivity)?.startSupportActionMode(SelectionActionModeCallback())
                    }

                    actionMode?.title = "Selected: ${selectionTracker.selection.size()}" // TODO: вынести в отдельный файл
                }
            })
        }
    }

    inner class BlankViewHolder(parentView: View) : RecyclerView.ViewHolder(parentView) {

        private var cardView: CardView = parentView.findViewById(R.id.cardView)
        private var numberLabel: TextView = parentView.findViewById(R.id.numberItemView)

        init {
            cardView.setCardBackgroundColor(createCardViewColorState())
        }

        fun bind(position: Int) {
            numberLabel.text = position.toString()

        }

        fun setActivatedState(isActivated: Boolean) {
            cardView.isActivated = isActivated
        }

        private fun createCardViewColorState(): ColorStateList {
            val activatedStateColor = ContextCompat.getColor(context!!, R.color.colorPrimaryLight)

            val states = arrayOf(
                    intArrayOf(android.R.attr.state_activated),
                    intArrayOf(-android.R.attr.state_activated)
            )
            val statesColors = intArrayOf(activatedStateColor, Color.WHITE)

            return ColorStateList(states, statesColors)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlankViewHolder {
        return BlankViewHolder(
                layoutInflater.inflate(R.layout.item_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(holder: BlankViewHolder, position: Int) {
        holder.apply {
            bind(position)
            setActivatedState(selectionTracker.isSelected(position.toLong()))
        }
    }

    class KeyProvider : ItemKeyProvider<Long>(ItemKeyProvider.SCOPE_MAPPED) {

        override fun getKey(position: Int): Long? = position.toLong()

        override fun getPosition(key: Long): Int = key.toInt()

    }

    inner class DetailsLookup : ItemDetailsLookup<Long>() {

        override fun getItemDetails(motionEvent: MotionEvent): ItemDetails<Long>? {
            return recyclerView?.findChildViewUnder(motionEvent.x, motionEvent.y)?.let {
                val childViewHolder = recyclerView!!.getChildViewHolder(it)
                configureItemDetails(childViewHolder)
            }
        }

        private fun configureItemDetails(viewHolder: RecyclerView.ViewHolder): ItemDetails<Long> {
            return object : ItemDetails<Long>() {
                override fun getSelectionKey(): Long? {
                    return viewHolder.adapterPosition.toLong()
                }

                override fun getPosition(): Int {
                    return viewHolder.adapterPosition
                }
            }
        }

    }

    class Predicate : SelectionTracker.SelectionPredicate<Long>() {

        override fun canSelectMultiple(): Boolean = true

        override fun canSetStateForKey(p0: Long, p1: Boolean): Boolean = true

        override fun canSetStateAtPosition(p0: Int, p1: Boolean): Boolean = true

    }

    inner class SelectionActionModeCallback : ActionMode.Callback {

        override fun onCreateActionMode(p0: ActionMode?, p1: Menu?): Boolean = true

        override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean = false

        override fun onDestroyActionMode(p0: ActionMode?) {
            selectionTracker.clearSelection()
            actionMode = null
        }

        override fun onActionItemClicked(p0: ActionMode?, p1: MenuItem?): Boolean = true

    }

}
