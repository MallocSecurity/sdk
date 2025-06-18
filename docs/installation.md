# Installation

Follow these steps to integrate the Malloc Security SDK into your Android project.

You will need the mallocSecuritySdkAuthToken which is provided to you.
## 1. Add the SDK

### SDK Integration

#### Modify `settings.gradle`:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url "https://jitpack.io"
            credentials { username mallocSecuritySdkAuthToken }
        }
    }
}
```
#### Add your mallocSecuritySdkAuthToken in `gradle.properties (app)`:

```groovy
mallocSecuritySdkAuthToken=[the-token-that-was-given-to-you]
```

#### Add dependency in `build.gradle (app)`:

```groovy
dependencies {
    implementation 'com.gitlab.devmalloc:malloc-security-sdk-android:0.2.1'
}
```
