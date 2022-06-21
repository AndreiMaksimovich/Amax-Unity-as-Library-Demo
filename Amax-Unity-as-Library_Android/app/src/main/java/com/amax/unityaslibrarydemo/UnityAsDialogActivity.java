package com.amax.unityaslibrarydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.unity3d.player.UnityPlayer;

public class UnityAsDialogActivity extends Activity {

    private UnityPlayer unityPlayer;
    private FrameLayout frameLayoutForUnity;
    private UnityNavigationManager unityManager;

    protected void onCreate(Bundle savedInstanceStateBundle) {
        super.onCreate(savedInstanceStateBundle);

        unityPlayer = new UnityPlayer(this);
        setContentView(R.layout.activity_unity_as_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        findViewById(R.id.button_close).setOnClickListener(view -> finish());

        this.frameLayoutForUnity = (FrameLayout) findViewById(R.id.unityFrameLayout);
        this.frameLayoutForUnity.addView(unityPlayer.getView(),
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        unityPlayer.windowFocusChanged(true);

        unityManager = new UnityNavigationManager(unityPlayer);
        unityManager.configure(UnityNavigationManager.EUnityNavigationMode.Default, UnityNavigationManager.EUnityScene.MainMenu, false);
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
