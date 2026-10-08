<p align="center"><img src="assets/logo.png" width="140" alt="OpenCarLink"></p>

# OpenCarLink

**English** | [简体中文](README.zh-CN.md)

![Status](https://img.shields.io/badge/status-early%20development-orange)

OpenCarLink is an Android app for car head units. It provides protocol code support for CarPlay, Android Auto, ICCOA, CarLife, Carbit, HiCar, AirPlay, and Miracast, and runs on Android 4.4 through Android 17.

It is intended for developers, head unit manufacturers, and end users.

> **Early development:** OpenCarLink is under active development. There is no stable release yet, and features, behavior, and compatibility may change without notice.

## Project Status

OpenCarLink is in the early development stage.

- Protocol code support for all 8 protocols is provided, and is still being improved.
- No stable release is available yet.
- Breaking changes should be expected until the first stable release.

## Features

- Protocol code support for 8 protocols: CarPlay, Android Auto, ICCOA, CarLife, Carbit, HiCar, AirPlay, and Miracast
- Runs on the head unit, compatible with Android 4.4 - Android 17
- Open source under the Apache License 2.0

## Roadmap

- [ ] Publish source code
- [ ] Provide a testable build
- [ ] Verify compatibility for each supported protocol
- [ ] First public release

## FAQ

**Which protocols are supported?**

Eight: CarPlay, Android Auto, ICCOA, CarLife, Carbit, HiCar, AirPlay, and Miracast.

**Which Android versions are supported?**

Android 4.4 through Android 17, on the head unit.

**Why Java instead of Kotlin?**

The automotive industry carries a large legacy of Java codebases, and building on Java lets those assets be reused directly, keeping integration and migration costs low. Kotlin and Java interoperate within the same project, and OpenCarLink does not exclude Kotlin modules; Java was chosen for asset reuse and maintenance cost, not technical limitations.

**Is it production-ready?**

No. OpenCarLink is in early development, with no stable release yet, and features, behavior, and compatibility may change without notice.

**How do I get the source or a build?**

The source code is not published yet. It will be announced in this repository; follow the roadmap for progress.

**Is this an officially certified or licensed project?**

No. OpenCarLink is an independent project and is not affiliated with, endorsed by, or certified by the owners of these trademarks.

**Can I use it commercially?**

OpenCarLink is licensed under the Apache License 2.0, which permits commercial use. Certification and licensing requirements for these protocols on commercial hardware are defined by their respective owners and are separate from this license.

## Trademarks

CarPlay, Android Auto, ICCOA, CarLife, Carbit, HiCar, AirPlay, and Miracast are trademarks or registered trademarks of their respective owners.

OpenCarLink is an independent project. It is not affiliated with, endorsed by, or certified by any of these trademark owners. All product names and brands are used for identification purposes only.

## Contact

For infringement notices, licensing, or partnership inquiries: [opencarlink@gmail.com](mailto:opencarlink@gmail.com)

For general questions and bug reports, please use [GitHub Issues](https://github.com/opencarlink-cmd/OpenCarLink/issues).

## License

Licensed under the Apache License 2.0.
