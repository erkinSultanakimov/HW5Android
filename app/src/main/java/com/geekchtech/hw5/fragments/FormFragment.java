package com.geekchtech.hw5.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekchtech.hw5.MainActivity;
import com.geekchtech.hw5.R;
import com.geekchtech.hw5.databinding.FragmentFormBinding;
import com.geekchtech.hw5.databinding.FragmentHomeBinding;


public class FormFragment extends Fragment {
    private MainActivity activity;
    private String name,number;

    private FragmentFormBinding binding;

    public FormFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) requireActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater,container, false);
        activity.hideBottomNav();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(v -> {
            name = binding.name.getText().toString().trim();
            number = binding.number.getText().toString().trim();
            if(name.equals("")){
                binding.name.setError("Enter your name");
            }
            else if (number.equals("")){
                binding.number.setError("Enter number");
            }else {
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("number",number);
                getActivity().getSupportFragmentManager().setFragmentResult("form",bundle);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.visibleBottomNav();
    }
}