package com.example.todoapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplication.R;
import com.example.todoapplication.databinding.ActivityHomeBinding;
import com.example.todoapplication.factory.ViewModelFactory;
import com.example.todoapplication.pojo.TodoData;
import com.example.todoapplication.view.adapter.TodoAdapter;
import com.example.todoapplication.viewmodel.HomeViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/* ------------------------------------------------------------- *
 * Home Activity
 * ------------------------------------------------------------- */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    /* ------------------------------------------------------------- *
     * Variable Declaration
     * ------------------------------------------------------------- */
    @Inject
    ViewModelFactory viewModelFactory;
    private HomeViewModel homeViewModel;
    private ActivityHomeBinding binding;
    private ArrayList<TodoData> originalList, searchList;
    private TodoAdapter todoAdapter;

    /* ------------------------------------------------------------- *
     * Initialize the view and view model inside the on create
     * ------------------------------------------------------------- */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homeViewModel = ViewModelProviders
                .of(this, viewModelFactory).get(HomeViewModel.class);
        intiView();
    }

    /* ------------------------------------------------------------- *
     * Defining the visibility & on click for the views
     * ------------------------------------------------------------- */
    private void intiView() {
        binding.topBarHome.textAdd.setVisibility(VISIBLE);
        binding.topBarHome.textActivityTitle.setText(R.string.home);
        binding.topBarHome.textAdd.setOnClickListener(this);
        binding.editSearch.setEnabled(false);
        binding.editSearch.addTextChangedListener(this);
        binding.swipeRefresh.setOnRefreshListener(this::onResume);
    }

    /* ------------------------------------------------------------- *
     * On resume Fetching data from database, setting data to views
     * ------------------------------------------------------------- */
    @Override
    protected void onResume() {
        binding.recyclerTodo.setVisibility(GONE);
        binding.labelStatus.setVisibility(VISIBLE);
        binding.swipeRefresh.setRefreshing(true);
        new Handler().postDelayed(this::getUserDatabase, 3000);
        binding.editSearch.setEnabled(true);
        super.onResume();
    }

    /* ------------------------------------------------------------- *
     * Get updated data from database
     * ------------------------------------------------------------- */
    private void getUserDatabase() {
        homeViewModel.getTodoListData().observe(this, todoData -> {
            this.originalList = new ArrayList<>(todoData);
            searchList = new ArrayList<>(originalList);
            setAdapter();
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.toString().trim().length() > 0) {
            filter(charSequence.toString().trim());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().trim().length() == 0) {
            if (originalList != null && searchList != null) {
                searchList.clear();
                searchList.addAll(originalList);
                todoAdapter.notifyDataSetChanged();
            }
        }
    }

    /* ------------------------------------------------------------- *
     * Filter the value from search and notify the adapter
     * ------------------------------------------------------------- */
    private void filter(String word) {
        if (originalList != null && originalList.size() > 0) {
            if (searchList != null)
                searchList.clear();
            for (TodoData item : originalList) {
                if (item.getTitle().toLowerCase().contains(word.toLowerCase())) {
                    searchList.add(item);
                }
            }
            todoAdapter.notifyDataSetChanged();
        }
    }

    /* ------------------------------------------------------------- *
     * setting all the data to recycler view after fetching it from
     * database
     * ------------------------------------------------------------- */
    private void setAdapter() {
        binding.swipeRefresh.setRefreshing(false);
        if (originalList.size() > 0 && searchList.size() > 0) {
            binding.recyclerTodo.setVisibility(VISIBLE);
            binding.labelStatus.setVisibility(GONE);
            binding.recyclerTodo.setHasFixedSize(true);
            binding.recyclerTodo.setLayoutManager(new
                    LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            todoAdapter = new TodoAdapter(this, searchList);
            binding.recyclerTodo.setAdapter(todoAdapter);
        } else {
            binding.recyclerTodo.setVisibility(GONE);
            binding.labelStatus.setText(getResources().getText(R.string.no_data_list_found));
            binding.labelStatus.setVisibility(VISIBLE);
        }
    }

    /* ------------------------------------------------------------- *
     * On click method for the view
     * ------------------------------------------------------------- */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.textAdd) {
            startActivity(new Intent(this, AddNewActivity.class));
        }
    }

    /* ------------------------------------------------------------- *
     * Destroy is called where to clear view model when activity is
     * not active
     * ------------------------------------------------------------- */
    @Override
    protected void onDestroy() {
        homeViewModel.onCleared();
        super.onDestroy();
    }
}