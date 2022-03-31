package com.rus.encard02.ui.fragment.onBoard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;

import com.rus.encard02.base.BaseFragment;
import com.rus.encard02.databinding.FragmentOnBoardBinding;
import com.rus.encard02.ui.Prefs;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OnBoardFragment extends BaseFragment<FragmentOnBoardBinding>
        implements OnBoardAdapter.Clickable {
    @Inject
    public Prefs prefs;

    @Override
    protected FragmentOnBoardBinding bind() {
        return FragmentOnBoardBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setupUI() {
        OnBoardAdapter adapter = new OnBoardAdapter(this);
        binding.boarPager.setAdapter(adapter);
        binding.wormDotsIndicator.setViewPager2(binding.boarPager);
    }

    @Override
    protected void setupObserver() {

    }

    @Override
    public void listener() {
        controller.navigateUp();
        prefs.saveBoardState();
    }
}