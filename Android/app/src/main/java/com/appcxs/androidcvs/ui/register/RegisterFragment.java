package com.appcxs.androidcvs.ui.register;

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

import com.appcxs.androidcvs.R;
import com.appcxs.androidcvs.api.model.User;
import com.appcxs.androidcvs.databinding.RegisterFragmentBinding;
import com.appcxs.androidcvs.ui.login.LoginFragment;

public class RegisterFragment extends Fragment {
    private RegisterFragmentBinding viewBinding;
    private RegisterViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container,false);
        return  viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        onClickBack();
        onClickRegister();
        viewModel.isRegister.observe(getViewLifecycleOwner(), observerRegister-> {
            if(observerRegister) {
                replaceFragment(new LoginFragment(),null);
            }
        });
    }

    private void onClickRegister() {
        viewBinding.btnRegister.setOnClickListener(v -> {
            String fullname = viewBinding.editTextFullName.getText().toString();
            String username = viewBinding.editTextUsername.getText().toString();
            String password = viewBinding.editTextPassword.getText().toString();
            String rePass = viewBinding.editTextRepass.getText().toString();

            if (fullname.isEmpty()
                    || username.isEmpty()
                    || password.isEmpty()
                    || rePass.isEmpty()
                    || !password.equals(rePass)) {
                Toast.makeText(requireActivity(),"Thông tin bạn nhập không hợp lệ",Toast.LENGTH_SHORT).show();
            } else {

                User user = new User();
                user.setUsername(username);
                user.setFullName(fullname);
                user.setPassword(password);
                user.setBirthday("12/12/2002");
                viewModel.register(user);
            }
        });
    }

    private void onClickBack() {
        viewBinding.btnBack.setOnClickListener( v-> {
            requireActivity().getSupportFragmentManager().popBackStack();
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
