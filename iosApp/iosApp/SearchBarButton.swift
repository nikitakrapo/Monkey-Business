//
//  SearchBarButton.swift
//  iosApp
//
//  Created by Nikita Krapotkin on 05.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SearchBarButton: View {
    private let onClick: () -> Void
    
    init(onClick: @escaping () -> Void) {
        self.onClick = onClick
    }
    
    var body: some View {
        Button(action: {
            onClick()
        }, label: {
            HStack {
                Spacer().frame(maxWidth: 10)
                Image(systemName: "magnifyingglass")
                    .foregroundColor(.onSurfaceVariant)
                Text("Search")
                    .foregroundColor(.outline)
                Spacer()
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        })
        .overlay(
            RoundedRectangle(cornerRadius: 25)
                .stroke(Color.outline, lineWidth: 1)
        )
    }
}

struct SearchBarButton_Previews: PreviewProvider {
    static var previews: some View {
        SearchBarButton(onClick: {})
    }
}
