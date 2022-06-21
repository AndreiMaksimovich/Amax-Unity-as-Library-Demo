using System;
using System.Collections;
using Amax.UI.ScreenTransition;
using UnityEngine;
using UnityEngine.SceneManagement;

namespace Amax.MobileARDemo.UI
{

    public class NavigationManager : Singleton<NavigationManager>
    {
        
        private const string LogTag = "NavigationManager:";

        private static NavigationMode navigationMode = NavigationMode.Default;
        private static bool showCloseButton = true;

        public const string SceneMainMenu = "ARDemo.Menu";
        public const string SceneARObjects = "ARDemo.Objects";
        public const string SceneARFaceRecognition = "ARDemo.Face";
        
        public static NavigationMode Mode
        {
            get => navigationMode;
            set
            {
                if (value == navigationMode) return;
                navigationMode = value;
                EventBus.RaiseEvent(new OnNavigationUIUpdateRequired());
            }
        }

        public static bool ShowCloseButton
        {
            get => showCloseButton;
            set
            {
                if (showCloseButton == value) return;
                showCloseButton = value;
                EventBus.RaiseEvent(new OnNavigationUIUpdateRequired());
            }
        }
        
        private void Start()
        {
            StartCoroutine(WaitForExternalMessagesCoroutine());
        }

        private IEnumerator WaitForExternalMessagesCoroutine()
        {
            yield return new WaitForSeconds(0.25f);
            ScreenFadeAnimation.FadeIn();
        }

        public bool IsCurrentSceneMainMenu => SceneManager.GetActiveScene().name == SceneMainMenu;
        
        public void OpenScene(AppScene appScene, bool animate = true)
        {
            var sceneName = GetSceneName(appScene);
            
            if (animate)
            {
                ScreenFadeAnimation.FadeOut(() => SceneManager.LoadScene(sceneName));
            }
            else
            {
                StopAllCoroutines();
                SceneManager.LoadScene(sceneName);
            }
            
        }
        
        public void OpenSceneByName(string appSceneName)
        {
            if (Enum.TryParse<AppScene>(appSceneName, out var appScene))
            {
                OpenScene(appScene, false);
            }
            else
            {
                Debug.LogWarning($"{LogTag} OpenScene Unknown scene name '{appSceneName}'");
            }
        }

        public void SetShowCloseButton(string value)
        {
            if (bool.TryParse(value, out var showButton))
            {
                ShowCloseButton = showButton;
            }
            else
            {
                Debug.LogWarning($"{LogTag} SetShowCloseButton Unknown bool value '{value}'");
            }
        }

        public void SetNavigationMode(NavigationMode mode)
        {
            Mode = mode;
        }
        
        public void SetNavigationModeByName(string modeName)
        {
            if (Enum.TryParse<NavigationMode>(modeName, out var mode))
            {
                SetNavigationMode(mode);
            }
            else
            {
                Debug.LogWarning($"{LogTag} SetNavigationMode Unknown mode name '{modeName}'");
            }
        }

        private string GetSceneName(AppScene appScene)
            => appScene switch
            {
                AppScene.MainMenu => SceneMainMenu,
                AppScene.ARObjects => SceneARObjects,
                AppScene.ARFaceRecognition => SceneARFaceRecognition,
                _ => throw new ArgumentOutOfRangeException(nameof(appScene), appScene, null)
            };
        
        public enum NavigationMode
        {
            Default,
            SingleScreen
        }
        
        public enum AppScene
        {
            MainMenu,
            ARObjects,
            ARFaceRecognition
        }
        
        public class OnNavigationUIUpdateRequired : EventBusBaseEvent {}

        
    }

}