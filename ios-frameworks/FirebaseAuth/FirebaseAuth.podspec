Pod::Spec.new do |spec|
    spec.name                     = 'FirebaseAuth'
    spec.version                  = '10.6.0'
    spec.homepage                 = 'https://github.com/firebase/firebase-ios-sdk'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = { :type=> 'Apache License 2.0' }
    spec.summary                  = 'xcframework from Firebase'
    spec.vendored_frameworks      = 'FirebaseAuth.xcframework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '14.1'
end