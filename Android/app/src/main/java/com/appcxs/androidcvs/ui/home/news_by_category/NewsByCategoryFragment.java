package com.appcxs.androidcvs.ui.home.news_by_category;

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
import com.appcxs.androidcvs.api.model.News;
import com.appcxs.androidcvs.databinding.NewsByCategoryFragmentBinding;
import com.appcxs.androidcvs.ui.home.HomeViewModel;
import com.appcxs.androidcvs.ui.adapter.NewsAdapter;
import com.appcxs.androidcvs.ui.home.web_view.NewsDetailWebViewFragment;

public class NewsByCategoryFragment extends Fragment implements NewsAdapter.OnClickNews {
    private NewsByCategoryFragmentBinding viewBinding;

    private HomeViewModel viewModel;
    private NewsAdapter adapter;
    private Category category;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        Bundle data = getArguments();
        if (data != null) {
            category = ((Category) data.getSerializable(Constance.KEY_CATEGORY));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.news_by_category_fragment, container,false);
        return  viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new NewsAdapter(viewModel.getListNewsByCategory(),requireActivity(),this);
        viewBinding.rcvNews.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewBinding.rcvNews.setAdapter(adapter);
        viewModel.getNewsByCategory(category.getId());
        viewModel.isGetNewsByCategory.observe(getViewLifecycleOwner(), ob -> {
            if(ob) {
                adapter.notifyDataSetChanged();
                viewModel.isGetNewsByCategory.postValue(false);
            }
        });
        viewBinding.category.setText(category.getCategoryName());
        onClickBack();
    }

    private void onClickBack() {
        viewBinding.btnBack.setOnClickListener(v -> {
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

    @Override
    public void onClick(News news) {
        Bundle data = new Bundle();
        data.putSerializable(Constance.KEY_NEWS,news);
        replaceFragment(new NewsDetailWebViewFragment(),data);
    }

}
