package com.devlab.tamboon.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.devlab.tamboon.R;
import com.devlab.tamboon.data.Charity;
import com.devlab.tamboon.databinding.CharityListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CharityListAdapter extends RecyclerView.Adapter<CharityListAdapter.CharityListViewHolder> {


    private Context context;
    private List<Charity> charityList;
    private View.OnClickListener onItemClickListener;

    public CharityListAdapter(Context context, List<Charity> charityList){
        this.context = context;
        this.charityList = charityList;
    }

    @NonNull
    @Override
    public CharityListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharityListItemBinding charityListItemBinding = CharityListItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CharityListViewHolder(charityListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharityListViewHolder holder, int position) {
        Charity charity = charityList.get(position);
        holder.tvCharityName.setText(charity.getName());
        Picasso picasso = Picasso.get();
//        picasso.setIndicatorsEnabled(true);
//        picasso.setLoggingEnabled(true);
        picasso.load(charity.getLogoUrl()).error(R.drawable.ic_broken_image).fit().into(holder.imglogo);
        Log.d("Adapter","ImageUrl: "+charity.getLogoUrl());

    }

    @Override
    public int getItemCount() {
        return charityList.size();
    }

    public void setOnItemClickListener(View.OnClickListener clickListener){
        onItemClickListener = clickListener;
    }

    public class CharityListViewHolder extends RecyclerView.ViewHolder {

        ImageView imglogo;
        TextView tvCharityName;

        public CharityListViewHolder(CharityListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            itemBinding.getRoot().setTag(this);
            itemBinding.getRoot().setOnClickListener(onItemClickListener);
            imglogo = itemBinding.charityLogo;
            tvCharityName = itemBinding.charityName;
        }
    }

}
