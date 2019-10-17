package com.perros_felices.infrastructure.binding_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.perros_felices.R
import com.perros_felices.infrastructure.commons.InteractionListener
import com.perros_felices.models.Pet
import kotlinx.android.synthetic.main.item_board.view.*

class BoardRecyclerViewAdapter(private val mValues: List<*>,
                               private val mListener: InteractionListener<Pet>?,
                               private val context: Context):
    RecyclerView.Adapter<BoardRecyclerViewAdapter.ViewHolder>()  {

    private val mOnClickListener: View.OnClickListener
    override fun getItemCount(): Int = mValues.size

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Pet
            mListener?.onListenerFragmentInteraction(item)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        lateinit var item: Any
        when {
            mValues[position] is Pet -> {
                item = mValues[position] as Pet
                /*Glide.with(parentActivity.baseContext) .load(item.photos[0]).centerCrop().transition(withCrossFade())
                    .thumbnail(0.35f)
                    .into(holder.thumbnailImageView)*/
                holder.thumbnailImageView.setImageResource(R.drawable.dummy_dog) // .setOnClickListener(ViewOnClickListener(parentActivity, item))
                holder.titleTextView.text = item.name
            }
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_board, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val thumbnailImageView: ImageView = mView.thumbnailImageView
        val titleTextView: TextView = mView.titleTextView
    }
}