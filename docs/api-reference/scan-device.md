# scanDevice()

Performs a full device integrity and threat scan.

## Returns

A structured result containing:

- Root detection status
- Presence of spyware or surveillance apps
- AccessibilityService misuse
- Tracking SDKs

## Usage

```kotlin
val result = MallocSecurity.scanDevice()
Log.d("SecurityScan", result.toJson())
