//
//  UserAvatar.swift
//  iosApp
//
//  Created by Nikita Krapotkin on 05.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct UserAvatar: View {
    private let action: () -> Void
    
    init(action: @escaping () -> Void) {
        self.action = action
    }
    
    var body: some View {
        Button(action: {
            action()
        }, label: {
            GeometryReader { geometry in
                ZStack {
                    Image(systemName: "person.fill")
                        .resizable()
                        .frame(width: geometry.size.width / 2, height: geometry.size.height / 2)
                        .foregroundColor(.outline)
                    Circle()
                        .stroke(Color.outline, lineWidth: 1)
                        .frame(width: .infinity, height: .infinity)
                }
            }
        })
        .aspectRatio(1, contentMode: .fill)
        .frame(
            maxWidth: .infinity,
            maxHeight: .infinity
        )
    }
}

struct UserAvatar_Previews: PreviewProvider {
    static var previews: some View {
        UserAvatar(action: {})
    }
}
