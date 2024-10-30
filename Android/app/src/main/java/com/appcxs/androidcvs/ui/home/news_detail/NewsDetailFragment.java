//package com.appcxs.androidcvs.ui.home.news_detail;
//
//import android.os.Build;
//import android.os.Bundle;
//import android.text.Html;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.databinding.DataBindingUtil;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.lifecycle.ViewModelStore;
//
//import com.appcxs.androidcvs.Constance;
//import com.appcxs.androidcvs.R;
//import com.appcxs.androidcvs.api.model.News;
//import com.appcxs.androidcvs.databinding.HomeFragmentBinding;
//import com.appcxs.androidcvs.databinding.NewsDetailFragmentBinding;
//import com.appcxs.androidcvs.ui.home.HomeViewModel;
//import com.appcxs.androidcvs.ui.home.HomeViewPager;
//import com.bumptech.glide.Glide;
//import com.google.android.material.tabs.TabLayoutMediator;
//
//public class NewsDetailFragment extends Fragment {
//    private NewsDetailFragmentBinding viewBinding;
//    NewsDetailsViewModel viewModel ;
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        viewModel = new ViewModelProvider(this).get(NewsDetailsViewModel.class);
//        Bundle data = getArguments();
//        if (data != null) {
//            viewModel.setNews((News) data.getSerializable(Constance.KEY_NEWS));
//        }
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        viewBinding = DataBindingUtil.inflate(inflater, R.layout.news_detail_fragment, container,false);
//        return  viewBinding.getRoot();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        setUpView();
//
//    }
//
//    private void setUpView() {
//        Glide.with(requireContext())
//                .load(viewModel.getNews().getThumbnail())
//                .centerCrop()
//                .into(viewBinding.thumbnail);
//        viewBinding.titleNews.setText(viewModel.getNews().getTitle());
//        viewBinding.timeNews.setText(viewModel.getNews().getCreateDate());
//
//        String htmlContent = viewModel.getNews().getContent();
//       // viewBinding.content.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
//        viewBinding.content.setText(Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_COMPACT));
//    }
//
//    private void replaceFragment(Fragment fragment, Bundle data) {
//        fragment.setArguments(data);
//        requireActivity()
//                .getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(null)
//                .replace(R.id.fragment, fragment)
//                .commit();
//
//    }
//}
