//
// Created by Nikita Krapotkin on 05.12.2022.
// Copyright (c) 2022 orgName. All rights reserved.
//

import SwiftUI
import core

struct HomeScreen: View {
    private let component: HomeComponent
    private let stateFlow: CommonStateFlow<HomeComponentState>
    
    @State private var state: HomeComponentState
    
    init(component: HomeComponent) {
        self.component = component
        
        stateFlow = CommonStateFlow<HomeComponentState>.init(origin: component.state)
        state = stateFlow.value
    }
    
    var body: some View {
        VStack {
            HStack {
                SearchBarButton(onClick: {
                    component.onSearchBarClicked()
                })
                .frame(maxWidth: .infinity, maxHeight: 48)
                Spacer().frame(width: 16)
                UserAvatar(size: 48, onClick: {
                    component.onAvatarClicked()
                })
            }
            .frame(maxWidth: .infinity)
            .padding(16)
            Text(String(state.balance.amount) + " " + state.balance.currency.code)
            HStack {
                Button(action: {
                    component.onTopupClicked()
                }) {
                    Text("Topup")
                }
                Button(action: {
                    component.onWithdrawClicked()
                }) {
                    Text("Withdraw")
                }
            }
        }
        .onReceive(createPublisher(stateFlow)) { state in
            self.state = state
        }
    }
}
