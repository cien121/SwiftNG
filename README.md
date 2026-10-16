# SwiftNG

基于 [v2rayNG](https://github.com/2dust/v2rayNG) 的定制版 Android 代理客户端。

## 定制内容

- **iOS 小组件风主页**：深色底 + 圆角卡片，状态、速度、节点信息一目了然
- **状态卡即开关**：底部状态卡点击即可连接/断开
- **自动选择最快节点**：一键测速，自动切换延迟最低的节点
- **节点底部弹窗**：节点列表收进底部弹窗，不占主页空间
- **实时速率显示**：上下行速度实时刷新
- **IPv4/IPv6 标识**：当前节点动态显示走 v4 还是 v6
- **精简菜单**：去掉推广、关于、订阅分组

## 构建

GitHub Actions 自动构建 arm64-v8a 调试包，见 [Releases](https://github.com/cien121/SwiftNG/releases)。

## 致谢

- 上游项目：[2dust/v2rayNG](https://github.com/2dust/v2rayNG)
- 核心：[XTLS/Xray-core](https://github.com/XTLS/Xray-core)
