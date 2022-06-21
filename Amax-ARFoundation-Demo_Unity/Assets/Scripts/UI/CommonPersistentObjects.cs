// Copyright (c) 2022 Andrei Maksimovich
// See LICENSE.md

using UnityEngine;
using UnityEngine.SceneManagement;

namespace Amax.MobileARDemo.UI
{
    
    public class CommonPersistentObjects: MonoBehaviour
    {
        
        public static CommonPersistentObjects Instance { get; private set; }

        private void Awake()
        {
            if (Instance == null)
            {
                Debug.Log("CommonPersistentObjects Awake Instance == null");
                Instance = this;
                DontDestroyOnLoad(gameObject);
                Application.unloading += () =>
                {
                    Debug.Log("Application.unloading");
                    SceneManager.MoveGameObjectToScene(gameObject, SceneManager.GetActiveScene());
                    Instance = null;
                };
            }
            else
            {
                DestroyImmediate(gameObject);
            }

        }
        
    }
    
}