# 海瑞待办

目前只是一个demo。

## 编译

目前成功在以下平台成功编译

- Android
- Linux
- macOS

### 编译打包的坑

1. 编译要求jdk版本为java15。低于java15桌面版本无法正常打包，高于java15安卓版无法编译通过。
2. Linux下和macOS下package name带有点号时会导致无法启动。