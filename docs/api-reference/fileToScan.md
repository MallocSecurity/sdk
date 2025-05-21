# `FileToScan` Class

The `FileToScan` class is used within the Malloc SDK to represent files that are queued for scanning. It is utilized in the **File Scanning** feature when invoking asynchronous scan methods. It encapsulates metadata for a file, including its name and a URI reference.

---

## 🔍 Class Definition

```java
package com.mallocprivacy.mallocsecuritysdk;

import android.net.Uri;

public class FileToScan {

    private String fileName;
    private Uri uri;

    public FileToScan(String fileName, Uri uri) {
        this.fileName = fileName;
        this.uri = uri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "FileToScan{" +
                "fileName='" + fileName + '\'' +
                ", uri=" + uri +
                '}';
    }
}
```

---

## Fields

| Field      | Type   | Description                                |
| ---------- | ------ | ------------------------------------------ |
| `fileName` | String | The name of the file as a readable label.  |
| `uri`      | Uri    | URI reference to the actual file location. |

---
