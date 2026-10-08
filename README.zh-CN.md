<p align="center"><img src="assets/logo.png" width="140" alt="OpenCarLink"></p>

# OpenCarLink

[English](README.md) | **简体中文**

![状态](https://img.shields.io/badge/status-%E6%97%A9%E6%9C%9F%E5%BC%80%E5%8F%91-orange)

OpenCarLink 是一款运行在车机端的 Android 应用，提供 CarPlay、Android Auto、ICCOA、CarLife、Carbit、HiCar、AirPlay、Miracast 协议代码支持，兼容 Android 4.4 - Android 17。

适用于开发者、车机厂商与终端车主。

> **早期开发：** OpenCarLink 正在积极开发中，暂无稳定版本，功能、行为和兼容性可能随时变化。

## 项目状态

OpenCarLink 目前处于早期开发阶段。

- 已提供 8 种协议代码支持，仍在持续完善中。
- 暂无稳定版本可供使用。
- 在首个稳定版本发布前，接口与行为可能发生不兼容变更。

## 特性

- 提供 8 种协议代码支持：CarPlay、Android Auto、ICCOA、CarLife、Carbit、HiCar、AirPlay、Miracast
- 运行于车机端，兼容 Android 4.4 - Android 17
- 基于 Apache License 2.0 开源

## 路线图

- [ ] 公开源代码
- [ ] 提供可测试的构建版本
- [ ] 逐项验证各协议兼容性
- [ ] 首个公开发布版本

## 常见问题

**支持哪些协议？**

CarPlay、Android Auto、ICCOA、CarLife、Carbit、HiCar、AirPlay、Miracast，共 8 种协议代码支持。

**支持哪些 Android 版本？**

车机端 Android 4.4 - Android 17。

**为什么选用 Java 而不是 Kotlin？**

汽车电子领域存量项目以 Java 为主，沿用 Java 可直接复用这些代码资产，降低车厂与方案商的接入和迁移成本。Kotlin 与 Java 可在同一工程内互操作，OpenCarLink 并不排斥 Kotlin 模块，选择 Java 是出于存量资产复用与维护成本的考虑，而非技术限制。

**现在可以用于生产环境吗？**

不建议。项目处于早期开发阶段，暂无稳定版本，功能、行为和兼容性可能随时变化。

**怎么获取源码或安装包？**

源码尚未公开发布，发布后会在此仓库更新，请关注路线图进度。

**是官方认证或授权项目吗？**

不是。OpenCarLink 是独立项目，与 CarPlay、Android Auto、HiCar 等商标权利人无隶属、背书或认证关系。

**可以商用吗？**

项目基于 Apache License 2.0 开源，允许商用。但各协议在商用硬件上的认证与授权要求由对应厂商规定，需要自行确认，与本项目许可证无关。

## 商标声明

CarPlay、Android Auto、ICCOA、CarLife、Carbit、HiCar、AirPlay、Miracast 均为其各自权利人的商标或注册商标。

OpenCarLink 是独立项目，与上述商标权利人无隶属、背书或认证关系。所有产品名称与品牌仅用于说明兼容范围。

## 联系方式

侵权通知、授权合作等事务请联系：[opencarlink@gmail.com](mailto:opencarlink@gmail.com)

一般问题与缺陷反馈请使用 [GitHub Issues](https://github.com/opencarlink-cmd/OpenCarLink/issues)。

## 许可证

本项目基于 Apache License 2.0 开源。

> 本翻译基于英文版，如有出入以 [英文版](README.md) 为准。
