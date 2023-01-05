//
//  ScaleButtonStyle.swift
//  iosApp
//
//  Created by Nikita Krapotkin on 06.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct ScaleButtonStyle: ButtonStyle {
    private let pressedScale: CGFloat
    
    init(pressedScale: CGFloat) {
        self.pressedScale = pressedScale
    }
    
    func makeBody(configuration: Self.Configuration) -> some View {
        configuration.label
            .scaleEffect(configuration.isPressed ? pressedScale : 1)
            .animation(.easeInOut(duration: 0.1), value: configuration.isPressed)
    }
}
