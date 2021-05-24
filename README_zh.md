## 涂鸦安卓 Iot App SDK 示例
[中文版](README_zh.md)|[English](README.md)

涂鸦安卓Iot App SDK示例项目分为了几个功能模块，让开发者清晰的了解不同功能的实现，包括用户注册流程、不同用户的家庭管理、设备配网、设备控制等。

本示例演示了使用涂鸦安卓Iot App SDK，通过手机号或者邮箱来实现登录和注册。您可以在登录后管理您的信息，如重置密码或更改昵称。
## 准备工作
  - [X] Android Studio 4.1.2
  - [X] minSdkVersion 21

## 开始
1. 克隆或者下载项目，用AndroidStudio打开它。
2. 从[涂鸦Iot平台](https://iot.tuya.com/?_source=github)获取**Appkey** 、**AppSecret** 和对应的安全图片。如果您还没有注册过，请参考下面步骤：
  
   1. 登录到[涂鸦IoT平台](https://iot.tuya.com/?_source=github)，在左侧导航栏选择**App** > **SDK 开发**；
   2. 点击**创建**创建一个app；
   3. 按要求填写信息，确保您输入的包名是正确的，一旦确定了不可更改；
   4. 点击创建的应用，点击**获取秘钥**您就可以看到AppKey和AppSecret，安全图片.

3. 打开AndroidStudio项目，替换 app 目录下 **build.gradle** 文件中的 **applicationId** 为您的应用包名。
4. 在**AndroidManifest.xml**中填入AppKey和AppSecret
5. 下载安全图片，命名为 t_s.bmp，放置到工程目录的 assets 文件夹下。

    **注意**: 应用的AppKey、AppSecret和安全图片，必须跟您在 [涂鸦 IoT 平台](https://iot.tuya.com/?_source=github)申请的一致. 否则，示例项目将请求不了相关接口.

## 参考

更多详细信息, 请参考 [App SDK](https://developer.tuya.com/en/docs/app-development?_source=github).


示例图片
---
<img src="https://images.tuyacn.com/app/liya/tuya-app-sdk-sample/3.png" alt="main_page" width="30%" />
<img src="https://images.tuyacn.com/app/liya/tuya-app-sdk-sample/4.png" alt="main_page" width="30%" />


问题反馈
---

您可以通过**Github Issue** 或通过[**工单**](https://service.console.tuya.com)来进行反馈您所碰到的问题

LICENSE
---
Tuya Android Home SDK Sample是在MIT许可下提供的。更多信息请参考[LICENSE](LICENSE)文件
