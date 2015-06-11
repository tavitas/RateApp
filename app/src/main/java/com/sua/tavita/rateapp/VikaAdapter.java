package com.sua.tavita.rateapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Teuila on 27/05/15.
 */
public class VikaAdapter extends RecyclerView.Adapter<VikaAdapter.MyViewHolder> {
    private static final String TAG = "Vika";
    private LayoutInflater inflater;
    List<Feature> data = Collections.emptyList();
    private Context context;
    private ItemClickListener clickListener;

    public VikaAdapter(Context context, List<Feature> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        Log.d("Vika", "onCreateViewHolder called ");
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {
        Feature currentFeature = data.get(position);
        Log.d(TAG, "onBindViewHolder called " + position);
        holder.title.setText(currentFeature.featureTitle);
        holder.icon.setImageResource(currentFeature.iconID);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), "Item clicked at " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
//        delete(getLayoutPosition());
//        context.startActivity(new Intent(context, TestReview.class));
            if(clickListener!=null){
                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener{
        public void itemClicked(View view, int position);
    }
}