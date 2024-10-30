package com.appcxs.androidcvs.ui.home.web_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.appcxs.androidcvs.ActivityViewModel;
import com.appcxs.androidcvs.Constance;
import com.appcxs.androidcvs.R;
import com.appcxs.androidcvs.api.model.News;
import com.appcxs.androidcvs.databinding.NewsDetailWebViewFragmentBinding;

public class NewsDetailWebViewFragment extends Fragment {
    private NewsDetailWebViewFragmentBinding viewBinding;
    NewsDetailsViewModel viewModel ;

    ActivityViewModel activityViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NewsDetailsViewModel.class);
        activityViewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);
        Bundle data = getArguments();
        if (data != null) {
            viewModel.setNews((News) data.getSerializable(Constance.KEY_NEWS));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.news_detail_web_view_fragment, container,false);
        return  viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView();
        onClickBack();
        try {
            String token = activityViewModel.userLogin.getData().getAccessToken();
            viewModel.saveNewsWatch(token);
        }
        catch (Exception ignored) {}
    }

    private void onClickBack() {
        viewBinding.btnBack.setOnClickListener(v -> {
            NewsDetailWebViewFragment.this.getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    private void setUpView() {
        String title = viewModel.getNews().getTitle();
        String date = viewModel.getNews().getCreateDate();
        String des = viewModel.getNews().getDescription();
        String thumbnailUrl = viewModel.getNews().getThumbnail();
        String content = viewModel.getNews().getContent();
        String htmlContent = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><style>"
                + "img { max-width: 100%; height: auto; }"
                + "body { max-width: 100%; overflow-x: hidden; }"
                + "</style></head><body>"
                + "<h1>" + title + "</h1>"
                + "<p>" + date + "</p>"
                + "<img src=\"" + thumbnailUrl + "\" />"
                + "<h2>" + des + "</h2>"
                + content
                + "</body></html>";
        viewBinding.webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
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
