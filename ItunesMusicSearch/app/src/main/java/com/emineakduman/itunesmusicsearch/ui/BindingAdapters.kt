package com.emineakduman.itunesmusicsearch.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.emineakduman.itunesmusicsearch.R
import com.emineakduman.itunesmusicsearch.data.Track
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_baseline_music_note_24)
            .error(R.drawable.ic_baseline_error_24)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("price")
fun bindPrice(view: TextView, track: Track?) {
    track?.let {
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.currency = Currency.getInstance(track.currency)
        view.text = format.format(track.trackPrice)
    }
}

@BindingAdapter("date")
fun bindDate(view: TextView, date: Date?) {
    date?.let {
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getDefault()
        view.text = dateFormat.format(date)
    }
}

@BindingAdapter("time")
fun bindTime(view: TextView, time: Long?) {
    time?.let {
        view.text = String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(time),
            TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(time)
            )
        )
    }
}

@BindingAdapter("explicitness")
fun bindExplicitness(view: TextView, explicit: String?) {
    explicit?.let {
        if (explicit == "explicit") {
            view.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_explicit_music,
                0
            )
        }
    }
}

@BindingAdapter("externalLink")
fun bindExternalLink(view: TextView, externalLink: String?) {
    externalLink?.let {
        view.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(externalLink)
            startActivity(view.context, intent, null)
        }
    }
}