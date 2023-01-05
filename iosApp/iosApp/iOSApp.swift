import SwiftUI
import FirebaseCore
import core

class AppDelegate: NSObject, UIApplicationDelegate {
    private var coreHolder: CoreHolder?

    func application(
            _ application: UIApplication,
            didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        FirebaseApp.configure()
        return true
    }

    fileprivate func getCoreHolder() -> CoreHolder {
        if (coreHolder == nil) {
            coreHolder = CoreHolder(savedState: nil)
        }
        return coreHolder!
    }
}

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var delegate

    private var coreHolder: CoreHolder { delegate.getCoreHolder() }

    var body: some Scene {
        WindowGroup {
            CoreContentView(coreHolder.core)
        }
    }
}

private class CoreHolder : ObservableObject {
    let lifecycle: LifecycleRegistry
    let stateKeeper: StateKeeperDispatcher
    let core: CoreComponent
    
    init(savedState: ParcelableContainer?) {
        lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        stateKeeper = StateKeeperDispatcherKt.StateKeeperDispatcher(savedState: savedState)
        
        let accountManager = AccountManagerFactoryKt.FirebaseAccountManager()
        let analyticsManager = FirebaseAnalyticsManager()
        
        core = CoreComponentImpl(
            componentContext: DefaultComponentContext(
                lifecycle: lifecycle,
                stateKeeper: stateKeeper,
                instanceKeeper: nil,
                backHandler: nil
            ),
            analyticsManager: analyticsManager,
            accountManager: accountManager
        )
        
        lifecycle.onCreate()
    }
    
    deinit {
        lifecycle.onDestroy()
    }
}
