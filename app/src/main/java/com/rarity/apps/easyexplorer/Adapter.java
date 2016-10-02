package com.rarity.apps.easyexplorer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by Premang on 01-Oct-16.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.CustomHolder>{

    private LinkedList<Item> list;

    protected class CustomHolder extends RecyclerView.ViewHolder{
        private TextView name;

        public CustomHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    public Adapter(LinkedList<Item> list){
        this.list = list;
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new CustomHolder( inflater.inflate(R.layout.list_row, parent, false) );
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        holder.name.setText( list.get(position).getName() );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Item getItem(int position){
        return list.get(position);
    }

    public void addItem(Item item){
        list.add(item);
        File f = new File(item.getPath()+"/"+item.getName());
        notifyItemInserted(list.size());
    }

    public void removeItem(int position){
        list.get(position).delete();
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    public void rename(String newName, int position){
        list.get(position).rename(newName);
    }
}
