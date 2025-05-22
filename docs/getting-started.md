# Getting Started

Welcome to the Malloc Security SDK for Android !
This SDK empowers your app with advanced threat detection, safeguarding user privacy and security. It is privacy-first, lightweight, and easy to integrate.

## Requirements

- Android 9.0 (API level 28) or higher
- Internet permission (for URL and SHA checking)
- External storage permission (for Downloads folder scanning)

## What’s Inside?

- 🔍 Device Security Scan (root detection and suspicous files related to spyware) 
- 🌐 URL Threat Detection
- 🔒 Apps and files malware scanning 

## Quick Start

1. [Install the SDK](./installation.md)
2. Initialize it in your Application class
3. Use `scanDevice()`  `scanApps()`, `scanFiles()`and `checkUrl()` where needed
