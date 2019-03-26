package android.iut.jetpacklist.ui.detail;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.iut.jetpacklist.databinding.DetailFragmentBinding;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.iut.jetpacklist.R;

public class DetailFragment extends Fragment {

    private DetailViewModel viewModel;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // On inflate la vue avec le DataBindingUtil
        DetailFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false);
        // On instancie le ViewModel
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        // On valorise la propriété dans le binding
        binding.setViewmodel(viewModel);
        // On retourne la vue inflate dans le binding
        return binding.getRoot();
    }
}
