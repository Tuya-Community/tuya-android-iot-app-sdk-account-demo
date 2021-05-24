Tuya Android Iot App SDK Sample
===
[中文版](README_zh.md)|[English](README.md)

Tuya Android IoT App SDK is divided into several function groups to give developers a clear insight into the implementation for different features, including the user registration process, home management for different users, device network configuration, and controls. For device network configuration, the EZ mode and AP mode are implemented. This allows developers to pair devices over Wi-Fi and control the devices over LAN and MQTT. For device control, a common panel is used to send and receive any type of data points.

This sample demonstrates the use of Tuya Android IoT App SDK to  login and register by your phone or email . You could manage your information after  login, like reset the password or change the nick name .


## Prerequisites
  - [X] Android Studio 4.1.2
  - [X] minSdkVersion 21

Get Started
---

- Clone or download this sample,open it or import it by AndroidStudio.
- Get a pair of keys and a security image from the [Tuya IoT Platform](https://iot.tuya.com/?_source=github), and register a developer account if you don't have one. Then, perform the following steps:

    1. Log in to the [Tuya IoT Platform](https://iot.tuya.com/?_source=github). In the left-side navigation pane, choose **App** > **SDK Development**.
    2. Click **Create** to create an app.
    3. Fill in the required information. Make sure that you enter the valid package name. It cannot be changed afterward.
    4. Click the created app, and you will find the AppKey, AppSecret, and security image under the **Get Key** tag.

- Open the sample in the Android Studio,Choose app > build.gradle and change the value of applicationId to your app package name
- Fill in the AppKey and AppSecret in the **AndroidManifest.xml** file.
- Download the security image, rename it to `t_s.bmp`, and then drag it to the **assets**.

     **Note**: The package name, AppKey, AppSecret, and security image must be the same as your app on the [Tuya IoT Platform](https://iot.tuya.com/?_source=github). Otherwise, the sample cannot request the API.

## References

For more information, see [App SDK](https://developer.tuya.com/en/docs/app-development?_source=github).



App Images
---
<img src="https://images.tuyacn.com/app/liya/tuya-app-sdk-sample/3.png" alt="main_page" width="30%" />
<img src="https://images.tuyacn.com/app/liya/tuya-app-sdk-sample/4.png" alt="main_page" width="30%" />

Issue Feedback
---

You can provide feedback on your issue via **Github Issue** or [Technical Ticket](https://service.console.tuya.com).

License
---
Tuya Android Home SDK Sample is available under the MIT license. Please see the [LICENSE](LICENSE) file for more info.
