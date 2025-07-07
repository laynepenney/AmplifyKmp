# AmplifyKMP

This repository contains a minimal Kotlin Multiplatform library that wraps AWS Amplify for multiple targets.

## Modules

- **amplify** – Kotlin module configured for Android, JVM (desktop), iOS, and WasmJS. It exposes a simple `Greeting` API and demonstrates platform-specific implementations of `Platform`.
- **AmplifyIos** – Swift package providing basic wrappers around AWS Amplify plugins. This package is consumed in the Kotlin module via the local `swiftklib` plugin.

## Build Setup

The project uses Gradle with a custom build logic plugin. Most tasks can be run with the Gradle wrapper:

```bash
./gradlew build
```

The build config expects JDK 8 and uses toolchains to download the required version. When running in a restricted environment without access to `foojay.io`, toolchain resolution may fail.

## Repository Structure

```
├─ amplify/        Kotlin Multiplatform code
│  └─ src/         Source sets for Android, iOS, desktop, wasm
├─ AmplifyIos/     Swift Package with Amplify wrappers
├─ build-logic/    Gradle plugin for Maven publishing
├─ repo/           Local Maven repository containing the `swiftklib` plugin
```

## Next Steps

- Expand the Swift wrapper in `AmplifyIos` to expose more Amplify functionality.
- Add sample applications for each target to verify integration.
- Replace the local `swiftklib` plugin with a remote dependency when available.

