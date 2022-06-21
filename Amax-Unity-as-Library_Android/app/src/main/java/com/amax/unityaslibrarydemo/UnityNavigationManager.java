package com.amax.unityaslibrarydemo;

import com.unity3d.player.UnityPlayer;

public class UnityNavigationManager {

    private UnityPlayer unityPlayer;

    private final String GO_NAME = "NavigationManager";
    private final String METHOD_NAME__OPEN_SCENE = "OpenSceneByName";
    private final String METHOD_NAME__SET_NAVIGATION_MODE = "SetNavigationModeByName";
    private final String METHOD_NAME__SET_SHOW_CLOSE_BUTTON = "SetShowCloseButton";

    public UnityNavigationManager(UnityPlayer unityPlayer) {
        this.unityPlayer = unityPlayer;
    }

    public void openScene(EUnityScene scene) {
        unityPlayer.UnitySendMessage(GO_NAME, METHOD_NAME__OPEN_SCENE, scene.toString());
    }

    public void setNavigationMode(EUnityNavigationMode mode) {
        unityPlayer.UnitySendMessage(GO_NAME, METHOD_NAME__SET_NAVIGATION_MODE, mode.toString());
    }

    public void setShowCloseButton(boolean value) {
        unityPlayer.UnitySendMessage(GO_NAME, METHOD_NAME__SET_SHOW_CLOSE_BUTTON, String.valueOf(value));
    }

    public void configure(EUnityNavigationMode navigationMode, EUnityScene scene, boolean showCloseButton) {
        setNavigationMode(navigationMode);
        openScene(scene);
        setShowCloseButton(showCloseButton);
    }

    public enum EUnityNavigationMode
    {
        Default,
        SingleScreen
    }

    public enum EUnityScene
    {
        MainMenu,
        ARObjects,
        ARFaceRecognition
    }

}
