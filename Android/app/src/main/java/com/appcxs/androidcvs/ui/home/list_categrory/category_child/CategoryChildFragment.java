package com.appcxs.androidcvs.ui.home.list_categrory.category_child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.appcxs.androidcvs.Constance;
import com.appcxs.androidcvs.R;
import com.appcxs.androidcvs.api.model.Category;
import com.appcxs.androidcvs.databinding.CategoryChildFragmentBinding;
import com.appcxs.androidcvs.ui.adapter.CategoryChildAdapter;
import com.appcxs.androidcvs.ui.home.news_by_category.NewsByCategoryFragment;

import java.util.ArrayList;

public class CategoryChildFragment extends Fragment implements CategoryChildAdapter.OnClickCategory {
    private CategoryChildFragmentBinding viewBinding;
    CategoryChildAdapter adapter ;

    Category category ;
    ArrayList<Category> listCategory = new ArrayList<>();
    ArrayList<Category> dataAdapter = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.category_child_fragment, container,false);
        return  viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle data = getArguments();
        if (data != null) {
            category =  (Category) data.getSerializable(Constance.KEY_CATEGORY);

            listCategory = (ArrayList<Category>) data.getSerializable(Constance.KEY_LIST_CATEGORY);
        }
        dataAdapter.clear();
        if(category.getId() == 1) {
            dataAdapter.add(listCategory.get(18));
            dataAdapter.add(listCategory.get(19));
        }
        else if(category.getId() ==2) {
            dataAdapter.add(listCategory.get(20));
            dataAdapter.add(listCategory.get(21));
        }
        else if(category.getId() ==3) {
            dataAdapter.add(listCategory.get(22));
            dataAdapter.add(listCategory.get(23));
        }
        adapter = new CategoryChildAdapter(requireContext(),dataAdapter,this);
        viewBinding.rcvCategory.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewBinding.rcvCategory.setAdapter(adapter);
        onClickBack();
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

    @Override
    public void onClickCategory(Category category) {
        Bundle data = new Bundle();
        data.putSerializable(Constance.KEY_CATEGORY,category);
        replaceFragment(new NewsByCategoryFragment(),data);
    }

}
