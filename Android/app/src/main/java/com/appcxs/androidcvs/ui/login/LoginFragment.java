package com.appcxs.androidcvs.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.appcxs.androidcvs.ActivityViewModel;
import com.appcxs.androidcvs.R;
import com.appcxs.androidcvs.api.model.User;
import com.appcxs.androidcvs.databinding.LoginFragmentBinding;
import com.appcxs.androidcvs.ui.home.HomeFragment;
import com.appcxs.androidcvs.ui.register.RegisterFragment;

public class LoginFragment extends Fragment {
    private LoginFragmentBinding viewBinding;

    User user;
    ActivityViewModel viewModel ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container,false);
        return  viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);
        onClickLogin();
        onClickSignUp();
        viewModel.isLogin.observe(getViewLifecycleOwner(), observerLogin -> {
            if(observerLogin) {
                replaceFragment(new HomeFragment(), null);
                viewModel.isLogin.postValue(false);
            }
        });
    }

    private void onClickSignUp() {
        viewBinding.tvMoveRegister.setOnClickListener( v-> {
            replaceFragment(new RegisterFragment(),null);
        });
    }

    private void onClickLogin() {
        viewBinding.btnLogin.setOnClickListener( v -> {
            String userName = viewBinding.editTextUsername.getText().toString();
            String pass = viewBinding.editTextPassword.getText().toString();
            if(userName.isEmpty() || pass.isEmpty()) {
                Toast.makeText(LoginFragment.this.requireActivity(),"Chưa nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            }
            else {
                User userLogin = new User();
                userLogin.setUsername(userName);
                userLogin.setPassword(pass);
                viewModel.login(userLogin);
            }
        });

        viewBinding.btnCancel.setOnClickListener( v-> {
            replaceFragment(new HomeFragment(),null);
        });
    }

    private void replaceFragment(Fragment fragment, Bundle data) {
        fragment.setArguments(data);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment, fragment)
                .commit();
    }
}

