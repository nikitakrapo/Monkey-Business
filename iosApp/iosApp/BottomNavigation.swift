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
                    ZStack {
                        if item.isSelected {
                            Capsule()
                                .fill(Color.surfaceTint.opacity(0.25))
                                .frame(width: 72, height: 36)
                        }
                        Image(systemName: item.iconSystemName)
                            .foregroundColor(item.isSelected ? .onSecondaryContainer : .onSurfaceVariant)
                            .frame(
                                maxWidth: .infinity,
                                maxHeight: 80
                            )
                    }
                })
            }
        }
        .frame(
            maxWidth: .infinity,
            maxHeight: 80
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
