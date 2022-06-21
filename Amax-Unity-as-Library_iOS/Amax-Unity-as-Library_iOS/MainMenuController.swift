//
//  ViewController.swift
//  UIKitPlayground
//
//  Created by Andrei Maksimovich on 14/06/2022.
//

import UIKit

class MainMenuController: UIViewController {

    @IBOutlet weak var button: UIButton!
    
    @IBAction func onButtonClick_ARFace(_ sender: Any) {
        UnityWindowManager.instance.showUnityWindow(navigationMode: .SingleScreen, scene: .ARFaceRecognition, showCloseButton: true)
    }
    
    
    @IBAction func onButtonClick_ARObjects(_ sender: Any) {
        UnityWindowManager.instance.showUnityWindow(navigationMode: .SingleScreen, scene: .ARObjects, showCloseButton: true)
    }
    
    
    @IBAction func onButtonClick_ARDemo(_ sender: Any) {
        UnityWindowManager.instance.showUnityWindow(navigationMode: .Default, scene: .MainMenu, showCloseButton: true)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

}

