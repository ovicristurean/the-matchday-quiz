// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "theMatchdayQuizIos",
    platforms: [
        .iOS(.v14)
    ],
    products: [
        .library(
            name: "theMatchdayQuizIos",
            targets: ["theMatchdayQuizIos"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "theMatchdayQuizIos",
            path: "./theMatchdayQuizIos.xcframework"
        ),
    ]
)
