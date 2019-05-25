package com.tuesday_apps.poweradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tuesday_apps.poweradapter.item.ItemRenderer;
import com.tuesday_apps.poweradapter.item.RecyclerItem;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{

    private final ItemRenderer<RecyclerItem> renderer;

    public RecyclerViewHolder(ViewGroup parent, ItemRenderer<RecyclerItem> renderer) {
        super(renderer.createView(parent));
        this.renderer = renderer;
    }

    void bind(RecyclerItem item) {
        renderer.render(itemView, item);
    }
}
