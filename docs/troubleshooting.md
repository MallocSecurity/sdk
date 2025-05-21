# Troubleshooting

## SDK not initializing?

Ensure that you've called `MallocSecurity.init(context)` in your Application class and that the required permissions are granted.

## Device root not detected?

Root detection may be bypassed on certain emulators or with sophisticated root hiding techniques.

## checkUrl always returns SAFE?

Make sure the app has internet permissions and the domain/IP you're checking is known to threat feeds used by the SDK.
