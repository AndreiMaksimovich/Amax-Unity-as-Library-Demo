using System;
using UnityEngine;
using UnityEngine.UI;

namespace Amax.MobileARDemo.UI
{

    [RequireComponent(typeof(Button))]
    public class CloseApplicationButton : MonoBehaviour, IEventBusListener<NavigationManager.OnNavigationUIUpdateRequired>
    {

        [SerializeField] private bool innerScene = false;
        
        private void Awake()
        {
            EventBus.AddListener(this);
        }

        private void OnDestroy()
        {
            EventBus.RemoveListener(this);
        }

        private bool IsVisible
        {
            get => gameObject.activeSelf;
            set => gameObject.SetActive(value);
        }

        private void UpdateVisibility()
        {
            IsVisible = NavigationManager.ShowCloseButton && (!innerScene || NavigationManager.Mode == NavigationManager.NavigationMode.SingleScreen);
        }

        private void Start()
        {
            GetComponent<Button>().onClick.AddListener(CloseApplication);
            UpdateVisibility();
        }

        private void CloseApplication()
        {
            if (Application.platform == RuntimePlatform.IPhonePlayer)
            {
                Application.Unload();
            }
            else
            {
                Application.Quit();
            }
        }

        public void OnEvent(NavigationManager.OnNavigationUIUpdateRequired data)
        {
            UpdateVisibility();
        }
        
    }

}
