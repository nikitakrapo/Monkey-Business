import SwiftUI
import core

struct CoreContentView: View {
    private let component: CoreComponent
    private let childStackFlow: CommonStateFlow<ChildStack<CoreComponentImpl.CoreScreen, CoreComponentChild>>
    
    @State private var childStack: ChildStack<CoreComponentImpl.CoreScreen, CoreComponentChild>
    private var child: CoreComponentChild {
        childStack.active.instance
    }
    
    init(_ component: CoreComponent) {
        self.component = component
        
        childStackFlow = CommonStateFlow<ChildStack<CoreComponentImpl.CoreScreen, CoreComponentChild>>.init(origin: component.childStack)
        childStack = childStackFlow.value
    }
    
    var body: some View {
        ZStack {
            Color.surface.ignoresSafeArea()
            if child is CoreComponentChild.Home || child is CoreComponentChild.More {
                VStack {
                    if child is CoreComponentChild.Home {
                        HomeScreen(component: (child as! CoreComponentChild.Home).component)
                    }
                    if child is CoreComponentChild.More {
                        Text("More")
                    }
                    Spacer()
                    BottomNavigation(items: [
                        BottomNavigationItem(
                            iconSystemName: "house.fill",
                            isSelected: child is CoreComponentChild.Home,
                            onClick: { component.onHomeClicked() }
                        ),
                        BottomNavigationItem(
                            iconSystemName: "ellipsis",
                            isSelected: child is CoreComponentChild.More,
                            onClick: { component.onMoreClicked() }
                        )
                    ])
                }
            } else if child is CoreComponentChild.Profile {
                VStack {
                    HStack {
                        Button(action: { (child as! CoreComponentChild.Profile).component.onBackArrowClicked() }) {
                            HStack {
                                Image(systemName: "chevron.left")
                                    .foregroundColor(.onSurface)
                                Text("Back")
                                    .foregroundColor(.onSurface)
                            }
                        }
                        .padding()
                        Spacer()
                    }
                    .frame(maxWidth: .infinity)
                    Spacer()
                    Text("Profile")
                    Spacer()
                }
            }
        }
        .onReceive(createPublisher(childStackFlow)) { childStack in
            self.childStack = childStack
        }
    }
}

//TODO: add previews
