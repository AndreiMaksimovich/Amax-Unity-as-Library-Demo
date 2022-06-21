//
//  UnityNavigationManager.swift
//  UIKitPlayground
//
//  Created by Andrei Maksimovich on 20/06/2022.
//

import Foundation
import UnityFramework

class UnityNavigationManager {
    
    private var unityFramework : UnityFramework?
    
    private let GameObjectName: String = "NavigationManager";
    private let MethodNameOpenScene: String = "OpenSceneByName";
    private let MethodNameSetNavigationMode: String = "SetNavigationModeByName";
    private let MethodNameSetShowCloseButton: String = "SetShowCloseButton";
    
    init(ufm: UnityFramework) {
        unityFramework = ufm
    }
    
    func openScene(scene: UnityScene) {
        unityFramework?.sendMessageToGO(withName: GameObjectName, functionName: MethodNameOpenScene, message: scene.rawValue)
    }
    
    func setNavigation(mode: UnitynavigationMode) {
        unityFramework?.sendMessageToGO(withName: GameObjectName, functionName: MethodNameSetNavigationMode, message: mode.rawValue)
    }
    
    func setShowCloseButton(value: Bool) {
        unityFramework?.sendMessageToGO(withName: GameObjectName, functionName: MethodNameSetShowCloseButton, message: value ? "true" : "false")
    }
        
    enum UnityScene: String {
        case MainMenu
        case ARObjects
        case ARFaceRecognition
    }
        
    enum UnitynavigationMode: String {
        case Default
        case SingleScreen
    }
    
}
