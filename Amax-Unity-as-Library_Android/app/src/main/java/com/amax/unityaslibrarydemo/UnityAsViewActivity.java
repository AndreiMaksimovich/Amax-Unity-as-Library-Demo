package com.amax.unityaslibrarydemo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.amax.unityaslibrarydemo.databinding.ActivityUnityAsViewBinding;
import com.unity3d.player.UnityPlayer;

public class UnityAsViewActivity extends AppCompatActivity {

    public static final String EXTRA_KEY__UNITY_SCENE = "UnityScene";

    private UnityPlayer unityPlayer;
    private FrameLayout frameLayoutForUnity;
    private ActivityUnityAsViewBinding binding;
    private UnityNavigationManager unityManager;

    protected void onCreate(Bundle savedInstanceStateBundle) {
        super.onCreate(savedInstanceStateBundle);

        binding = ActivityUnityAsViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        unityPlayer = new UnityPlayer(this);
        this.frameLayoutForUnity = (FrameLayout) findViewById(R.id.unityFrameLayout);
        this.frameLayoutForUnity.addView(unityPlayer.getView(),
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        unityPlayer.windowFocusChanged(true);

        unityManager = new UnityNavigationManager(unityPlayer);
        unityManager.setNavigationMode(UnityNavigationManager.EUnityNavigationMode.SingleScreen);
        unityManager.openScene((UnityNavigationManager.EUnityScene) getIntent().getSerializableExtra(EXTRA_KEY__UNITY_SCENE));
        unityManager.setShowCloseButton(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        unityPlayer.quit();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        unityPlayer.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        unityPlayer.resume();
    }

}
