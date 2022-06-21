# Amax-Unity-as-Library-Demo

Amax-Unity-as-Library-Demo - an example of using Unity as a library built into native Adnroid & iOS mobile applications. This approach allows you to embed mobile AR (ARFoundation) in any existing mobile application.

## Demo contains:
- Amax-ARFoundation-Demo - an Unity Demo project with an example of using Mobile AR (ARFoundation) for face recognition / modification, plane (environment) recognition and placement / control of 3D objects in augmented virtual space for Android (ARCore) and iOS (ARKit) devices
- Amax-ARFoundation-Demo_AndroidLibrary - exported Amax-ARFoundation-Demo as an Android project
- Amax-Unity-as-Library_Android - native android app that uses Amax-ARFoundation-Demo_AndroidLibrary as library
- Amax-Unity-as-Library_iOS - native iOS app (UIKit & Swift) that uses Amax-ARFoundation-Demo (Amax-Unity-as-Library_iOS/UnityLib) as library

## Notice
Due to file size limitations some folders with unity binaries have been added to .gitignore. You will need to re-export Amax-Unity-as-Library-Demo into Amax-ARFoundation-Demo_AndroidLibrary and Amax-Unity-as-Library_iOS/UnityLib

### Android
https://user-images.githubusercontent.com/60512133/174812015-8e3e6a0e-8b86-4048-8f92-9b7dd6941458.mp4

### iOS
https://user-images.githubusercontent.com/60512133/174812079-174638cb-0e53-41bf-afe1-87a65f139590.mp4
