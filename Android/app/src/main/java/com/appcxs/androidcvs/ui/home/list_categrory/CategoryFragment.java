package com.appcxs.androidcvs.ui.home.list_categrory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.appcxs.androidcvs.Constance;
import com.appcxs.androidcvs.R;
import com.appcxs.androidcvs.api.model.Category;
import com.appcxs.androidcvs.databinding.CategoryFragmentBinding;
import com.appcxs.androidcvs.ui.adapter.CategoryAdapter;
import com.appcxs.androidcvs.ui.home.HomeViewModel;
import com.appcxs.androidcvs.ui.home.list_categrory.category_child.CategoryChildFragment;
import com.appcxs.androidcvs.ui.home.news_by_category.NewsByCategoryFragment;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements CategoryAdapter.OnClickCategory {
    private CategoryFragmentBinding viewBinding;
    HomeViewModel viewModel;
    CategoryAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.category_fragment, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new CategoryAdapter(requireContext(), viewModel.getCategory(), this);
        viewBinding.rcvCategory.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewBinding.rcvCategory.setAdapter(adapter);
        viewModel.getAllCategory();
        viewModel.isGetCategory.observe(getViewLifecycleOwner(), observerCategory -> {
            if (observerCategory) {
                adapter.notifyDataSetChanged();
            }
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


    @Override
    public void onClickCategoryParent(Category category, ArrayList<Category> list) {
        Bundle data = new Bundle();
        data.putSerializable(Constance.KEY_CATEGORY, category);
        data.putSerializable(Constance.KEY_LIST_CATEGORY, list);
        replaceFragment(new CategoryChildFragment(), data);
    }

    @Override
    public void onClickCategory(Category category) {
        Bundle data = new Bundle();
        data.putSerializable(Constance.KEY_CATEGORY, category);
        replaceFragment(new NewsByCategoryFragment(), data);
    }
}
