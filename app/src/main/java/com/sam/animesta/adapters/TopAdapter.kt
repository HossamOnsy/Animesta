package com.sam.animesta.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.sam.animesta.model.TopModel
import androidx.databinding.DataBindingUtil
import com.sam.animesta.databinding.TopItemBinding
import com.sam.animesta.databinding.TopItemBindingImpl
import kotlinx.android.synthetic.main.top_item.view.*


class TopAdapter(var c: FragmentActivity?, var list: ArrayList<TopModel>) :
    RecyclerView.Adapter<TopAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TopItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), com.sam.animesta.R.layout.top_item, parent, false)
        return ViewHolder(binding)
    }

    fun updateList(topModelList: List<TopModel>) {
        list.addAll(topModelList)
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(list.get(position))

    }

    override fun getItemCount() = list.size


    class ViewHolder(private val  topItemBinding: TopItemBinding) : RecyclerView.ViewHolder(topItemBinding.root) {

        fun bind(item: TopModel) {
                topItemBinding.topModel = item
            if (item.score != null) {
                itemView.rb_rating.rating = (item.score /2.0).toFloat()
            }
        }

    }

}