// Copyright (c) 2022 Andrei Maksimovich
// See LICENSE.md

using System;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.XR.ARFoundation;

namespace Amax.MobileARDemo
{
    [RequireComponent(typeof(Button))]
    public class ARFaceDemoResetSessionButton: MonoBehaviour
    {

        [SerializeField] private ARSession arSession;

        private void Start()
        {
            GetComponent<Button>().onClick.AddListener(() => arSession.Reset());
        }
    }
}