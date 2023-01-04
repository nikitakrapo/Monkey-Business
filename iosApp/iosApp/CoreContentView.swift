import SwiftUI
import core

struct CoreContentView: View {
    @Environment(\.colorScheme) private var colorScheme
    
    private var accountManager: AccountManager
    private var analyticsManager: AnalyticsManager
    private var analytics: CoreScreenAnalytics

    @State var activeScreen = "Home"

    init() {
        accountManager = AccountManagerFactoryKt.FirebaseAccountManager()
        analyticsManager = FirebaseAnalyticsManager()
        analytics = CoreScreenAnalytics(analytics: analyticsManager)
    }

    var body: some View {
        TabView(selection: $activeScreen) {
            HomeScreen().tag("Home")
                    .tabItem {
                        Label("Home", systemImage: "house")
                    }
                    .onTapGesture {
                        activeScreen = "More"
                        analytics.onMoreClicked()
                    }
            Text("More").tag("More")
                    .tabItem {
                        Label("More", systemImage: "ellipsis")
                    }
                    .onTapGesture {
                        activeScreen = "Home"
                        analytics.onHomeClicked()
                    }
        }
    }
}

struct CoreContentView_Previews: PreviewProvider {
    static var previews: some View {
        CoreContentView()
    }
}
