# 海瑞待办

目前只是一个demo。

## 编译

目前成功在以下平台成功编译并测试可以打开。

- Android
- Linux
- macOS

目前你可以在 [release](https://github.com/rfkhx/todocompose/releases) 下载应用包。

### 编译打包的坑

1. desktop运行要求jdk版本为11～16，打包要求jdk版本为java15/16。安卓要求jdk版本为11～15。
2. Linux下和macOS下package name带有点号时会导致无法启动。
3. 默认不会把java.sql等包打到产物，需要先执行`./gradlew suggestRuntimeModules`确定依赖，然后添加到`desktop/build.gradle.kts`

### macOS运行

macOS尽管没有像iOS一样要求所有应用从App Store下载，但其默认也不能安装未知来源应用。

对于macOS 10.13以上，需要从命令允许未知来源。

```shell
sudo spctl --master-disable
```

然后打开一次应用，然后在设置->安全性与隐私->通用中允许。
