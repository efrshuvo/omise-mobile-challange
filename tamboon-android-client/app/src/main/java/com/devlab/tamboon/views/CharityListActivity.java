package com.devlab.tamboon.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.devlab.tamboon.adapter.CharityListAdapter;
import com.devlab.tamboon.data.Charity;
import com.devlab.tamboon.databinding.ActivityCharityListBinding;
import com.devlab.tamboon.factory.CharityListViewModelFactory;
import com.devlab.tamboon.network.RetrofitService;
import com.devlab.tamboon.repositories.TamboonRemoteRepository;
import com.devlab.tamboon.viewmodels.CharityListViewModel;

import java.util.ArrayList;

public class CharityListActivity extends AppCompatActivity {

    private CharityListAdapter charityListAdapter;
    private CharityListViewModel charityListViewModel;
    private CharityListViewModelFactory charityListViewModelFactory;
    private TamboonRemoteRepository tamboonRemoteRepository;
    private ArrayList<Charity> charityList;

    private ActivityCharityListBinding binding;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView charityListView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCharityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initViewModel();
        configureCharityListObserver();
    }

    private void initView(){
        charityList = new ArrayList<>();
        swipeRefreshLayout = binding.pulToRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(charityListViewModel != null) {
                    charityListViewModel.requestingCharityList();
                }
            }
        });
        charityListView = binding.charityListView;
        charityListAdapter = new CharityListAdapter(this,charityList);
        charityListView.setHasFixedSize(true);
        charityListView.setLayoutManager(new LinearLayoutManager(this));
        charityListView.setAdapter(charityListAdapter);
        charityListView.setItemAnimator(new DefaultItemAnimator());
        charityListView.setNestedScrollingEnabled(true);
        charityListAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();
                // viewHolder.getItemId();
                // viewHolder.getItemViewType();
                // viewHolder.itemView;
                Charity charity = charityList.get(position);
                Intent intent = new Intent(CharityListActivity.this,DonationActivity.class);
                intent.putExtra("name",charity.getName());
                intent.putExtra("logoUrl",charity.getLogoUrl());
                startActivity(intent);
            }
        });
    }

    private void initViewModel() {
        tamboonRemoteRepository = new TamboonRemoteRepository(RetrofitService.getInstance());
        charityListViewModelFactory = new CharityListViewModelFactory(tamboonRemoteRepository);
        charityListViewModel = ViewModelProviders.of(this,charityListViewModelFactory).get(CharityListViewModel.class);
        charityListViewModel.requestingCharityList();
    }

    private void configureCharityListObserver(){
        charityListViewModel.getCharityList().observe(this, charityListResponse->{
            Throwable throwable = charityListResponse.getThrowable();
            if( throwable == null){
                charityList.clear();
                charityList.addAll(charityListResponse.getCharityList());
                charityListAdapter.notifyDataSetChanged();
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }else{
                showLoadingErrorMessage(throwable);
            }
        });
    }

    private void showLoadingErrorMessage(Throwable t){
        Toast.makeText(this,t.getMessage().toString(),Toast.LENGTH_LONG).show();
    }
}
