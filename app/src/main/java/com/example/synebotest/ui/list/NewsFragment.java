package com.example.synebotest.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.synebotest.R;
import com.example.synebotest.ui.main.MainActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewsFragment extends Fragment implements ItemClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "News Fragment";


    private NewsViewModel newsViewModel;
    private NewsAdapter adapter;
    private RecyclerView rvlistOfNews;

    public static NewsFragment newInstance(int index) {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        rvlistOfNews = root.findViewById(R.id.rvListOfNews);
        createList();
        observeData();
        return root;
    }

    private void observeData() {
        newsViewModel.getNewsList().observe(getViewLifecycleOwner(), news -> {
            Log.e(TAG, "onChanged: " + news.size() );
            adapter.setData(news);
            newsViewModel.saveData();
        });
    }

    public void createList(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvlistOfNews.setLayoutManager(layoutManager);
        adapter = new NewsAdapter();
        rvlistOfNews.setAdapter(adapter);
        rvlistOfNews.getItemAnimator().setChangeDuration(0);
        adapter.setItemClickListener(this);
        adapter.setSharedPreferences(getActivity().getPreferences(Context.MODE_PRIVATE));
    }

    @Override
    public void onItemClick(int id) {
        ((MainActivity)getActivity()).setChosenNews(id);
    }

    @Override
    public void onPause() {
        newsViewModel.dispose();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        newsViewModel.fetchData();
    }
}