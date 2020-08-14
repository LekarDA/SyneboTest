package com.example.synebotest.ui.info;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.synebotest.R;
import com.example.synebotest.util.Constants;

import dagger.hilt.android.AndroidEntryPoint;
import static android.content.Context.MODE_PRIVATE;

@AndroidEntryPoint
public class MyInfoFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private MyInfoViewModel myInfoViewModel;
    private TextView date;
    private TextView title;
    private TextView readArticle;
    private SharedPreferences sharedPreferences;

    public static MyInfoFragment newInstance(int index) {
        MyInfoFragment fragment = new MyInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myInfoViewModel = new ViewModelProvider(this).get(MyInfoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_info, container, false);
        date = root.findViewById(R.id.tv_current_date);
        title = root.findViewById(R.id.tv_news_topic);
        readArticle = root.findViewById(R.id.tv_read_article);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        myInfoViewModel.getDate().removeObservers(getViewLifecycleOwner());
        myInfoViewModel.getDate().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                date.setText(s);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        title.setText(sharedPreferences.getString(Constants.SP_KEY,""));
        if(!title.getText().toString().equals(""))
            readArticle.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        myInfoViewModel.dispose();
        super.onPause();
    }
}