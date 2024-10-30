package com.appcxs.androidcvs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.appcxs.androidcvs.ui.login.LoginFragment;

public class MainActivity extends AppCompatActivity {
    ActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ActivityViewModel.class);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new LoginFragment()).commit();

    }
}