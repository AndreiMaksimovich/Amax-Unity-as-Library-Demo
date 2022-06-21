import Foundation
import UnityFramework

class UnityWindowManager: NSObject, UnityFrameworkListener {

    static let instance = UnityWindowManager()
    
    public var mainWindow : UIWindow?
    public var unityFramework : UnityFramework?
    public var unityNavigationManager: UnityNavigationManager?

    private let dataBundleId: String = "com.unity3d.framework"
    private let frameworkPath: String = "/Frameworks/UnityFramework.framework"
    
    private var isInitialized: Bool {
        unityFramework?.appController() != nil
    }
    
    func getUnityView() -> UIView! {
        return unityFramework?.appController()?.rootViewController?.view
    }
    
    private func sendResetMessageToARFaceDemo() {
        unityFramework?.sendMessageToGO(withName: "ARFaceDemo", functionName: "ResetARSession", message: "")
    }
    
    func showUnityWindow(navigationMode: UnityNavigationManager.UnitynavigationMode, scene: UnityNavigationManager.UnityScene, showCloseButton: Bool) {
        if isInitialized {
            unityFramework?.showUnityWindow()
        } else {
            guard let unityFramework = loadUnityFramework() else {
                fatalError("ERROR: Unity Framework Load Failed")
            }
            self.unityFramework = unityFramework
            self.unityNavigationManager = UnityNavigationManager(ufm: unityFramework)
            unityFramework.setDataBundleId(dataBundleId)
            unityFramework.register(self)
            unityFramework.runEmbedded(withArgc: CommandLine.argc, argv: CommandLine.unsafeArgv, appLaunchOpts: nil)
        }
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) {
            self.unityNavigationManager?.setNavigation(mode: navigationMode)
            self.unityNavigationManager?.setShowCloseButton(value: showCloseButton)
            self.unityNavigationManager?.openScene(scene: scene)
            
        }
        
        // Reset AR Face Recognition
        if scene == .ARFaceRecognition {
            DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
                self.sendResetMessageToARFaceDemo()
            }
        }
    }

    private func loadUnityFramework() -> UnityFramework? {
        let bundle = Bundle(path: Bundle.main.bundlePath + frameworkPath)
        
        if bundle?.isLoaded == false {
            bundle?.load()
        }

        let unityFramework = bundle?.principalClass?.getInstance()
        if unityFramework?.appController() == nil {
            let machineHeader = UnsafeMutablePointer<MachHeader>.allocate(capacity: 1)
            machineHeader.pointee = _mh_execute_header
            unityFramework?.setExecuteHeader(machineHeader)
        }
        
        return unityFramework
    }
    
    func unityDidUnload(_ notification: Notification!) {
        unityFramework?.unregisterFrameworkListener(self)
        unityFramework = nil
        unityNavigationManager = nil
        mainWindow?.makeKeyAndVisible()
    }
    
    
    
}
