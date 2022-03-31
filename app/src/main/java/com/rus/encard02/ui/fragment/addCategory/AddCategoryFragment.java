package com.rus.encard02.ui.fragment.addCategory;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;

import com.rus.encard02.base.BaseBottomSheetDialogFragment;
import com.rus.encard02.data.model.roomModel.CategoryModel;
import com.rus.encard02.databinding.FragmentAddCategoryBinding;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddCategoryFragment extends BaseBottomSheetDialogFragment<FragmentAddCategoryBinding> {
    private AddCategoryViewModel viewModel;

    @Override
    protected FragmentAddCategoryBinding bind() {
        return FragmentAddCategoryBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClicker();
    }

    private void initClicker() {
        binding.btnSaveCategory.setOnClickListener(view -> {
            String category = binding.etCategory.getText().toString();
            viewModel.insert(new CategoryModel(category));
            dismiss();
        });
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(requireActivity()).get(AddCategoryViewModel.class);
    }

    @Override
    protected void setupObserver() {

    }
}