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

## 配置存储位置

macOS和Linux下配置存储在`/Users/{your user name}/.config/hrtodo`

## 安装
### macOS安装

```shell
curl -L -o /tmp/hrtodo.dmg "https://github.com/rfkhx/todocompose/releases/download/macos-latest/-1.0.0.dmg"
hdiutil attach "/tmp/hrtodo.dmg" -nobrowse
rm -rf /Applications/海瑞待办.app
cp -rf /Volumes/海瑞待办/海瑞待办.app /Applications
hdiutil detach /Volumes/海瑞待办
rm /tmp/hrtodo.dmg
#sudo xattr -rd com.apple.quarantine /Applications/海瑞待办.app
osascript -e 'do shell script "xattr -rd com.apple.quarantine /Applications/海瑞待办.app" with administrator privileges'
```
