package com.example.todoapplication.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.todoapplication.R;
import com.example.todoapplication.databinding.ActivityAddNewBinding;
import com.example.todoapplication.factory.ViewModelFactory;
import com.example.todoapplication.pojo.TodoData;
import com.example.todoapplication.viewmodel.AddNewViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static android.view.View.VISIBLE;
import static com.example.todoapplication.utility.Utility.capitalCharAt;
import static com.example.todoapplication.utility.Utility.hideSoftKeyBoard;

/* ------------------------------------------------------------- *
 * Add New TodoTask Activity
 * ------------------------------------------------------------- */
public class AddNewActivity extends AppCompatActivity implements View.OnClickListener {

    /* ------------------------------------------------------------- *
     * Variable Declaration
     * ------------------------------------------------------------- */
    @Inject
    ViewModelFactory viewModelFactory;
    private AddNewViewModel addNewViewModel;
    private ActivityAddNewBinding binding;

    /* ------------------------------------------------------------- *
     * Initialize the view and view model inside the on create
     * ------------------------------------------------------------- */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new);
        addNewViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AddNewViewModel.class);
        intiView();
    }

    /* ------------------------------------------------------------- *
     * Defining the visibility & on click for the views
     * ------------------------------------------------------------- */
    private void intiView() {
        binding.topBarItem.textActivityTitle.setText(R.string.new_to_do);
        binding.topBarItem.backButton.setVisibility(VISIBLE);
        binding.topBarItem.backButton.setOnClickListener(this);
        binding.textCancel.setOnClickListener(this);
        binding.textDone.setOnClickListener(this);
    }

    /* ------------------------------------------------------------- *
     * On click method for the view
     * ------------------------------------------------------------- */
    @Override
    public void onClick(View view) {
        hideSoftKeyBoard(view, this);
        switch (view.getId()) {
            case R.id.backButton:
            case R.id.textCancel:
                onBackPressed();
                break;
            case R.id.textDone:
                getData(view);
                break;
        }
    }

    /* ------------------------------------------------------------- *
     * check the validation of the data collected from the edit text
     * and the send it to data base through viewModel and repository.
     * ------------------------------------------------------------- */
    private void getData(View view) {
        final String id = UUID.randomUUID().toString();
        String title = binding.editTextTitle.getText().toString().trim();
        String description = binding.editTextDescription.getText().toString().trim();
        if (!title.isEmpty()) {
            String titleName = capitalCharAt(title, 0);
            if (!description.isEmpty()) {
                binding.progressBar.setVisibility(VISIBLE);
                new Handler().postDelayed(() -> {
                    addNewViewModel.insertData(new TodoData(id, titleName, description));
                    onBackPressed();
                }, 1000);

            } else {
                showSnackBar(view, "Add Description and it cannot be blank!!");
            }
        } else {
            showSnackBar(view, "Title Cannot be Blank!!");
        }
    }

    /* ------------------------------------------------------------- *
     * Used for showing some error in validation
     * ------------------------------------------------------------- */
    private void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    /* ------------------------------------------------------------- *
     * Destroy is called where to clear view model when activity is
     * not active
     * ------------------------------------------------------------- */
    @Override
    protected void onDestroy() {
        addNewViewModel.onCleared();
        super.onDestroy();
    }
}