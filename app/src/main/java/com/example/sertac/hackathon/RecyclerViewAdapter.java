package com.example.sertac.hackathon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class adapt recycler view with the created view
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ArrayList<Hasta> hastalar = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<Hasta> hastalar) {
        this.hastalar          = hastalar;
        this.mContext           = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recyclerview_item_hastalar,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.hastaAdi.setText(hastalar.get(position).getTreatment());

    }

    @Override
    public int getItemCount() {
            return hastalar.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView hastaAdi;
        RelativeLayout oneItem;
        public ViewHolder(View itemView) {
            super(itemView);

            hastaAdi = itemView.findViewById(R.id.hastaAdi);
            oneItem = itemView.findViewById(R.id.oneItemLayout);

        }
    }

}
