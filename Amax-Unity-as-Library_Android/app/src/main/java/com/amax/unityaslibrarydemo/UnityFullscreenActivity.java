package com.amax.unityaslibrarydemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayer;

public class UnityFullscreenActivity extends Activity {

    private UnityPlayer unityPlayer;
    private UnityNavigationManager unityManager;
    private FrameLayout frameLayoutForUnity;

    protected void onCreate(Bundle savedInstanceStateBundle) {
        super.onCreate(savedInstanceStateBundle);

        unityPlayer = new UnityPlayer(this);
        setContentView(R.layout.activity_unity_fullscreen);
        this.frameLayoutForUnity = (FrameLayout) findViewById(R.id.unityFrameLayout);
        this.frameLayoutForUnity.addView(unityPlayer.getView(),
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        unityPlayer.requestFocus();
        unityPlayer.windowFocusChanged(true);

        unityManager = new UnityNavigationManager(unityPlayer);
        unityManager.configure(UnityNavigationManager.EUnityNavigationMode.Default, UnityNavigationManager.EUnityScene.MainMenu, true);
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
