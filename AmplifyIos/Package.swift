// swift-tools-version: 6.0
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let platforms: [SupportedPlatform] = [
    .iOS(.v13),
    .macOS(.v10_15),
    .tvOS(.v13),
    .watchOS(.v9)
]

let dependencies: [Package.Dependency] = [
    .package(url: "git@github.com:aws-amplify/amplify-swift.git", exact: "2.46.1")
]

let targets: [Target] = [
    // Targets are the basic building blocks of a package, defining a module or a test suite.
    // Targets can depend on other targets in this package and products from dependencies.
    .target(
        name: "AmplifyIos",
        dependencies: [
            .product(name: "Amplify", package: "amplify-swift"),
            .product(name: "AWSPluginsCore", package: "amplify-swift"),
            .product(name: "AWSAPIPlugin", package: "amplify-swift"),
            .product(name: "AWSCognitoAuthPlugin", package: "amplify-swift"),
        ]
    ),
    .testTarget(
        name: "AmplifyIosTests",
        dependencies: ["AmplifyIos"]
    ),
]

let package = Package(
    name: "AmplifyIos",
    platforms: platforms,
    products: [
        .library(
            name: "AmplifyIos",
            type: .static,
            targets: ["AmplifyIos"]
        ),
    ],
    dependencies: dependencies,
    targets: targets
)
