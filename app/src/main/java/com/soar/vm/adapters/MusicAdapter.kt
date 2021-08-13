package com.soar.vm.adapters

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.soar.vm.R
import com.soar.vm.model.Music

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