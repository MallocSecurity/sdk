# Authentication

The Malloc SDK handles authentication internally and does not expose any token-based authentication in its client-side implementation. Simply initialize the SDK in your `Application` class to begin using its features.

```kotlin
MallocSecurity.init(applicationContext)
