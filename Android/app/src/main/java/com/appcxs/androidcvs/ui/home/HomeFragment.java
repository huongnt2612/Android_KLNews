package com.appcxs.androidcvs.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.appcxs.androidcvs.ActivityViewModel;
import com.appcxs.androidcvs.R;
import com.appcxs.androidcvs.api.model.respon.UserLogin;
import com.appcxs.androidcvs.databinding.HomeFragmentBinding;
import com.appcxs.androidcvs.ui.home.history.HistoryFragment;
import com.appcxs.androidcvs.ui.login.LoginFragment;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment{
    private HomeFragmentBinding viewBinding;
    HomeViewPager viewPager ;
    ActivityViewModel activityViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityViewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       viewBinding = DataBindingUtil.inflate(inflater,R.layout.home_fragment, container,false);
       return  viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = new HomeViewPager(this);
        viewBinding.containerView.setAdapter(viewPager);
        new TabLayoutMediator(viewBinding.tabLayout, viewBinding.containerView, (tab, position) -> {
            if(position==0) {
                tab.setText("Tất cả bài viết");
            }
            else  {
                tab.setText("Danh mục");
            }
        }).attach();

        viewBinding.btnHistory.setOnClickListener(v-> {
           showPopUpMenu();
        });
    }


    private void showPopUpMenu() {
        PopupMenu p = new PopupMenu(requireContext(), viewBinding.btnHistory, Gravity.END, 0, R.style.Widget_AppCompat_PopupMenu);
        p.inflate(R.menu.menu);
        if (activityViewModel.userLogin==null ) {
            p.getMenu().getItem(0).setTitle("Đăng Nhập");
        } else {
            p.getMenu().getItem(0).setTitle("Đăng Xuất");
        }
        p.setOnMenuItemClickListener(v -> {
            if (v.getItemId() == R.id.logout) {
                    replaceFragment(new LoginFragment(),null);
                    activityViewModel.userLogin = null;
            } else {
                if (activityViewModel.userLogin==null ) {
                    Toast.makeText(requireContext(), "Đăng nhập để xem lịch sử", Toast.LENGTH_SHORT).show();
                }
                else {
                    replaceFragment(new HistoryFragment(),null);
                }
            }
            return false;
        });
        p.show();
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
