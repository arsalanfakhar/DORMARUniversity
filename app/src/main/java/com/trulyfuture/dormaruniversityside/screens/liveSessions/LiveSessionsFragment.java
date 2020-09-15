package com.trulyfuture.dormaruniversityside.screens.liveSessions;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trulyfuture.dormaruniversityside.R;
import com.trulyfuture.dormaruniversityside.databinding.FragmentLiveSessionsBinding;

public class LiveSessionsFragment extends Fragment {
    private FragmentLiveSessionsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentLiveSessionsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews();
    }


    private void setupViews(){

    }
}