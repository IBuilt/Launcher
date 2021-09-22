package com.example.launcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_package.view.*

class PackageAdapter(private val packageList: List<PackageInformation>, private val onClicked : (PackageInformation) -> Unit) : RecyclerView.Adapter<PackageAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onClicked(packageList[adapterPosition])
            }
        }

        fun bind(packageInfo: PackageInformation) {
            itemView.textView.text = packageInfo.appName
            itemView.image.setImageDrawable(packageInfo.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_package, null, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(packageList[position])
    }

    override fun getItemCount(): Int {
        return packageList.size
    }
}