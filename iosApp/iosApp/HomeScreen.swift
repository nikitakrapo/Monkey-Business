//
// Created by Nikita Krapotkin on 05.12.2022.
// Copyright (c) 2022 orgName. All rights reserved.
//

import SwiftUI
import core

struct HomeScreen: View {
    @Environment(\.colorScheme) private var colorScheme

    var body: some View {
        VStack {
            Text(colorScheme == .dark ? "dark" : "light")
            Text("203141 GBP")
            HStack {
                Button(action: {}) {
                    Text("Topup")
                }
                Button(action: {}) {
                    Text("Withdraw")
                }
            }
        }
    }
}
