package com.android.libreapps.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.libreapps.databinding.ChooseAppCardBinding

class ChooseAppAdapter(
    private var mApps: List<ChooseAppDialog.ChooseApp>,
    private val mListener: Listener
) : RecyclerView.Adapter<ChooseAppAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ChooseAppCardBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private var mApp: ChooseAppDialog.ChooseApp? = null

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            mApp?.let {
                mListener.onAppClicked(mApp!!)
            }
        }

        fun bind(app: ChooseAppDialog.ChooseApp) {
            mApp = app
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChooseAppCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding.appName.text = mApps[position].name
        binding.appIcon.setImageDrawable(mApps[position].icon)
        binding.appIcon.setOnClickListener {
            mListener.onAppClicked(mApps[position])
        }

        holder.bind(mApps[position])
    }

    override fun getItemCount(): Int {
        return mApps.size
    }

    fun interface Listener {
        fun onAppClicked(app: ChooseAppDialog.ChooseApp)
    }
}
