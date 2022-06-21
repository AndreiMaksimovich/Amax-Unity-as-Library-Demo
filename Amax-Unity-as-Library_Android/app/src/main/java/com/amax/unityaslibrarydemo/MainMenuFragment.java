package com.amax.unityaslibrarydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.amax.unityaslibrarydemo.databinding.FragmentMainMenuBinding;

public class MainMenuFragment extends Fragment {

    private FragmentMainMenuBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMainMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSimpleFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainMenuFragment.this)
                        .navigate(R.id.action_MainMenuFragment_to_SimpleFragment);
            }
        });

        binding.buttonUnityFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UnityFullscreenActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonUnityAsDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UnityAsDialogActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonUnityAsViewArFaceRecognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UnityAsViewActivity.class);
                intent.putExtra(UnityAsViewActivity.EXTRA_KEY__UNITY_SCENE, UnityNavigationManager.EUnityScene.ARFaceRecognition);
                startActivity(intent);
            }
        });

        binding.buttonUnityAsViewArObjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UnityAsViewActivity.class);
                intent.putExtra(UnityAsViewActivity.EXTRA_KEY__UNITY_SCENE, UnityNavigationManager.EUnityScene.ARObjects);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}