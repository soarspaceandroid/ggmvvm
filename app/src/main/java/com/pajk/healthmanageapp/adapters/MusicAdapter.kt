package com.pajk.healthmanageapp.adapters

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pajk.healthmanageapp.R
import com.pajk.healthmanageapp.model.Music

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/29
 *※ Time : 11:22
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.adapters
 *----------------------------------------------------
 */
class MusicAdapter:BaseQuickAdapter<Music.SongDetail , BaseViewHolder>(R.layout.item_music) {

    override fun convert(holder: BaseViewHolder, item: Music.SongDetail) {
        holder.setText(R.id.musicId , item.id)
        holder.setText(R.id.musicName , item.name)
    }
}