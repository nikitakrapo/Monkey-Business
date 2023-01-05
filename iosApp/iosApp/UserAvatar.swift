//
//  UserAvatar.swift
//  iosApp
//
//  Created by Nikita Krapotkin on 05.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct UserAvatar: View {
    private let size: CGFloat
    private let onClick: () -> Void
    
    init(size: CGFloat, onClick: @escaping () -> Void) {
        self.size = size
        self.onClick = onClick
    }
    
    var body: some View {
        Button(action: {
            onClick()
        }, label: {
            ZStack {
                Image(systemName: "person.fill")
                    .resizable()
                    .foregroundColor(.outline)
                    .frame(width: size / 2, height: size / 2)
                Circle()
                    .stroke(Color.outline, lineWidth: 1)
                    .frame(width: size, height: size)
            }
        })
        .aspectRatio(1, contentMode: .fill)
        .frame(
            maxWidth: size,
            maxHeight: size
        )
    }
}

struct UserAvatar_Previews: PreviewProvider {
    static var previews: some View {
        UserAvatar(
            size: 48,
            onClick: {}
        )
    }
}
