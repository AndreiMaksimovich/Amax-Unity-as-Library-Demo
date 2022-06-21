// Copyright (c) 2022 Andrei Maksimovich
// See LICENSE.md

#region

using System;
using Amax.Navigation;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

#endregion

namespace Amax.MobileARDemo.UI
{
    
    [RequireComponent(typeof(Button))]
    public class SceneNavigationOpenHomeSceneButton : MonoBehaviour, IEventBusListener<NavigationManager.OnNavigationUIUpdateRequired>
    {
        private void Awake()
        {
            EventBus.AddListener(this);
        }

        private void OnDestroy()
        {
            EventBus.RemoveListener(this);
        }

        public bool IsVisible
        {
            get => gameObject.activeSelf;
            set => gameObject.SetActive(value);
        }

        private void Start()
        {
            GetComponent<Button>().onClick.AddListener(OnButtonClick);
            UpdateVisibility();
        }


        private void UpdateVisibility()
        {
            IsVisible = !NavigationManager.Instance.IsCurrentSceneMainMenu && NavigationManager.Mode == NavigationManager.NavigationMode.Default;
        }

        private void OnButtonClick()
        {
            NavigationManager.Instance.OpenScene(NavigationManager.AppScene.MainMenu);
        }

        public void OnEvent(NavigationManager.OnNavigationUIUpdateRequired data)
        {
            UpdateVisibility();
        }
    }


}