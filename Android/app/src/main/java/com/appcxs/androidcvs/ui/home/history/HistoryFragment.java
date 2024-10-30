package com.appcxs.androidcvs.ui.home.history;

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

import com.appcxs.androidcvs.ActivityViewModel;
import com.appcxs.androidcvs.Constance;
import com.appcxs.androidcvs.R;
import com.appcxs.androidcvs.api.model.News;
import com.appcxs.androidcvs.databinding.HistoryFragmentBinding;
import com.appcxs.androidcvs.ui.adapter.NewsAdapter;
import com.appcxs.androidcvs.ui.home.web_view.NewsDetailWebViewFragment;

public class HistoryFragment extends Fragment implements NewsAdapter.OnClickNews {
    private HistoryFragmentBinding viewBinding;
    private ActivityViewModel activityViewModel;
    private HistoryViewModel viewModel;
    private NewsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        activityViewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.history_fragment, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new NewsAdapter(viewModel.listNews, requireActivity(), this);
        viewBinding.rcvNews.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewBinding.rcvNews.setAdapter(adapter);
        viewModel.getHistory(activityViewModel.userLogin.getData().getAccessToken());


        viewModel.isGetHistory.observe(getViewLifecycleOwner(), observerGetHistory -> {
            if (observerGetHistory) {
                adapter.notifyDataSetChanged();
                viewModel.isGetHistory.postValue(false);
            }
        });

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
        data.putSerializable(Constance.KEY_NEWS, news);
        replaceFragment(new NewsDetailWebViewFragment(), data);
    }
}
