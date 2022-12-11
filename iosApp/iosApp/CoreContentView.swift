import SwiftUI

struct CoreContentView: View {
    @State var activeScreen = "Home"

    var body: some View {
        TabView(selection: $activeScreen) {
            HomeScreen().tag("Home")
                    .tabItem {
                        Label("Home", systemImage: "house")
                    }
                    .onTapGesture {
                        activeScreen = "More"
                    }
            Text("More").tag("More")
                    .tabItem {
                        Label("More", systemImage: "ellipsis")
                    }
                    .onTapGesture {
                        activeScreen = "Home"
                    }
        }
    }
}

struct CoreContentView_Previews: PreviewProvider {
    static var previews: some View {
        CoreContentView()
    }
}