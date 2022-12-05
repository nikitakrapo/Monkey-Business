import SwiftUI
import core

struct CoreContentView: View {
    var body: some View {
        TabView {
            Text("Home")
                    .tabItem {
                        Label("Home", systemImage: "house")
                    }
                    .tag("Home")
            Text("More")
                    .tabItem {
                        Label("More", systemImage: "ellipsis")
                    }
                    .tag("More")
        }
    }
}

struct CoreContentView_Previews: PreviewProvider {
    static var previews: some View {
        CoreContentView()
    }
}