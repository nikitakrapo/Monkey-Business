//
//  BottomNavigationView.swift
//  iosApp
//
//  Created by Nikita Krapotkin on 05.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BottomNavigation: View {
    
    let items: Array<BottomNavigationItem>
    
    var body: some View {
        HStack {
            ForEach(0..<items.count, id: \.self) { index in
                let item = items[index]
                Button(action: {
                    item.onClick()
                }, label: {
                    Image(systemName: item.iconSystemName)
                        .imageScale(.large)
                        .foregroundColor(item.isSelected ? .onSecondaryContainer : .onSurfaceVariant.opacity(0.5))
                        .frame(
                            maxWidth: .infinity,
                            maxHeight: .infinity
                        )
                })
                .buttonStyle(ScaleButtonStyle(pressedScale: 1.25))
            }
        }
        .frame(
            maxWidth: .infinity,
            maxHeight: 58
        )
        .background(Color.surfaceVariant.ignoresSafeArea())
    }
}

struct BottomNavigationView_Previews: PreviewProvider {
    static var previews: some View {
        BottomNavigation(items: [BottomNavigationItem]())
    }
}

struct BottomNavigationItem {
    let iconSystemName: String
    let isSelected: Bool
    let onClick: () -> Void
}
