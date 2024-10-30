package com.appcxs.androidcvs.ui.home.all_news;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.appcxs.androidcvs.Constance;
import com.appcxs.androidcvs.R;
import com.appcxs.androidcvs.api.model.News;
import com.appcxs.androidcvs.databinding.NewsFragmentBinding;
import com.appcxs.androidcvs.ui.adapter.NewsAdapter;
import com.appcxs.androidcvs.ui.home.HomeViewModel;
import com.appcxs.androidcvs.ui.home.history.HistoryFragment;
import com.appcxs.androidcvs.ui.home.web_view.NewsDetailWebViewFragment;

public class NewsFragment extends Fragment implements NewsAdapter.OnClickNews {
    private NewsFragmentBinding viewBinding;

    private HomeViewModel viewModel;
    private NewsAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       viewBinding = DataBindingUtil.inflate(inflater,R.layout.news_fragment, container,false);
       return  viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new NewsAdapter(viewModel.getListNews(),requireActivity(),this);
        viewBinding.rcvNews.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewBinding.rcvNews.setAdapter(adapter);
        viewModel.getAllNews();
        viewModel.isGetNews.observe(getViewLifecycleOwner(), observerGetNews -> {
            if(observerGetNews) {
                adapter.notifyDataSetChanged();
                viewModel.isGetNews.postValue(false);
            }
        });
        search();
        viewBinding.popupMenu.setOnClickListener(v -> {
            showPopUpMenu();
        });
    }

    private void showPopUpMenu() {
        PopupMenu p = new PopupMenu(requireContext(), viewBinding.popupMenu, Gravity.END, 0, R.style.Widget_AppCompat_PopupMenu);
        p.inflate(R.menu.menu_news);
        p.setOnMenuItemClickListener(v -> {
            if (v.getItemId() == R.id.all_news) {
                viewModel.getAllNews();
            } else if (v.getItemId() == R.id.least_news) {
                viewModel.getLeastNews();
            } else {
                viewModel.getFavoriteNews();
            }
            return false;
        });
        p.show();
    }
    private void search() {
        viewBinding.searchNews.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.search(newText);
                return true;
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
    public void onClick(News news) {
        Bundle data = new Bundle();
        data.putSerializable(Constance.KEY_NEWS,news);
        replaceFragment(new NewsDetailWebViewFragment(),data);
    }
}
