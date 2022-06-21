// Copyright (c) 2022 Andrei Maksimovich
// See LICENSE.md

#region

using Amax.Navigation;
using Amax.UI.ScreenTransition;
using UnityEngine;
using UnityEngine.EventSystems;

#endregion

namespace Amax.MobileARDemo.UI
{

    public class SceneNavigationOpenSceneOnClick : MonoBehaviour, IPointerClickHandler
    {

        [SerializeField] private NavigationManager.AppScene scene = NavigationManager.AppScene.MainMenu;

        public void OnPointerClick(PointerEventData eventData)
        {
            NavigationManager.Instance.OpenScene(scene);
        }
        
    }

}
