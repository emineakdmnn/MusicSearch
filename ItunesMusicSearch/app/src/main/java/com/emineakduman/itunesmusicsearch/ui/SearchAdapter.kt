package com.emineakduman.itunesmusicsearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emineakduman.itunesmusicsearch.data.Track
import com.emineakduman.itunesmusicsearch.databinding.ListItemTrackBinding

class SearchAdapter(
    private val searchResults: List<Track>,
    private val onClickListener: TrackOnClickListener
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemTrackBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener = onClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(searchResults[position])
    }

    override fun getItemCount(): Int = searchResults.size

    class ViewHolder(
        private val binding: ListItemTrackBinding,
        private val onClickListener: TrackOnClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.track?.let { onClickListener.onTrackClicked(it) }
            }
        }

        fun bind(item: Track) {
            with(binding) {
                track = item
                executePendingBindings()
            }
        }
    }
}

interface TrackOnClickListener {
    fun onTrackClicked(track: Track)
}