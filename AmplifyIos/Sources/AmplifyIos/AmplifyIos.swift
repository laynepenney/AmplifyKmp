// The Swift Programming Language
// https://docs.swift.org/swift-book

import Foundation
import Amplify
import AWSAPIPlugin
import AWSPluginsCore
import AWSCognitoAuthPlugin

@objc public protocol AmplifyPlugin {

}
@objc public class AWSCognitoAuth : NSObject, AmplifyPlugin {
    private let plugin: AWSCognitoAuthPlugin
    
    @objc public override init() {
        self.plugin = AWSCognitoAuthPlugin()
    }
    
    public init(plugin: AWSCognitoAuthPlugin) {
        self.plugin = plugin
    }
}
@objc public class AmplifyIOS : NSObject {
    
    @objc public static func add(plugin: AmplifyPlugin) throws {
        
    }
}


