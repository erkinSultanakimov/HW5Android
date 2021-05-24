package com.geekchtech.hw5.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekchtech.hw5.Adapter;
import com.geekchtech.hw5.Model;
import com.geekchtech.hw5.R;
import com.geekchtech.hw5.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private Adapter adapter;
    private Model model;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new Adapter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentHomeBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().getSupportFragmentManager().setFragmentResultListener("form", this,((requestKey, result) ->  {
         model = new Model(result.getString("name"),result.getString("number"));
         adapter.addItems(model);
        }));
        binding.recycler.setAdapter(adapter);

        binding.fab.setOnClickListener(v -> {
            FragmentTransaction fm = requireActivity().getSupportFragmentManager().beginTransaction();
            fm.addToBackStack(null).replace(R.id.fm_container, new FormFragment()).commit();
        });
    }
}