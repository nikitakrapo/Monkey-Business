//
//  FlowPublisher.swift
//  iosApp
//
//  Created by Nikita Krapotkin on 04.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Combine
import core

public func createPublisher<T>(_ cFlow: CommonStateFlow<T>) -> AnyPublisher<T, Never> {
    return CFlowPublisher(cFlow: cFlow).eraseToAnyPublisher()
}

private struct CFlowPublisher<Output: AnyObject>: Publisher {
    
    typealias Output = Output
    typealias Failure = Never
    
    let cFlow: CommonStateFlow<Output>
    
    func receive<S>(subscriber: S) where S : Subscriber, Failure == S.Failure, Output == S.Input {
        subscriber.receive(subscription: CFlowSubscription(flow: cFlow, subscriber: subscriber))
    }
}

private class CFlowSubscription<Output: AnyObject, S: Subscriber>: Subscription where S.Input == Output, S.Failure == Never {
    
    private let disposable: Cancelable
    private let subscriber: S
    
    init(flow: CommonStateFlow<Output>, subscriber: S) {
        self.subscriber = subscriber
        self.disposable = flow.watch { value in
            let _ = subscriber.receive(value)
        }
    }
    
    func request(_ demand: Subscribers.Demand) { }
    
    func cancel() {
        DispatchQueue.main.async {
            self.disposable.cancel()
        }
    }
}
