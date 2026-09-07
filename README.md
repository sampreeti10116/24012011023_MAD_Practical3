# Practical-3: Implicit & Explicit Intent

## Aim

To create an Android application that demonstrates **Implicit Intent** and **Explicit Intent** for making a call, opening a URL, accessing the Call Log and Gallery, setting an alarm, opening the Camera, and navigating to a Login Activity.

## Objectives

1. Make a call to a specific number.
2. Open a specific URL.
3. Open the Call Log.
4. Open the Gallery.
5. Set an alarm.
6. Open the Camera.
7. Open a Login Activity.

## Concepts Covered

- Intent
- Implicit Intent
- Explicit Intent
- Intent Actions
- `Intent.setData()`
- `Intent.setType()`
- `startActivity()`
- `Uri.parse()`
- Button
- ConstraintLayout
- CoordinatorLayout
- ActivityResultContracts
- Manifest and Runtime Permissions
- `ContextCompat.checkSelfPermission()`
- `ActivityCompat.requestPermissions()`
- Contacts and Call Log content types
- MIME type `image/*`
- URI scheme `tel:`
- Adding Drawable Resources
- Adding Activities

## 1. Implicit Intent

An **Implicit Intent** does not specify the exact component that should handle the request. Android finds a suitable application based on the requested action and data.

Examples:
- Open a URL
- Open Call Log
- Open Gallery
- Set an Alarm
- Open Camera
- Make a call

Example:

```kotlin
val intent = Intent(Intent.ACTION_VIEW)
intent.data = Uri.parse("https://www.google.com")
startActivity(intent)
```

## 2. Explicit Intent

An **Explicit Intent** specifies the exact Activity or component to open.

Example:

```kotlin
val intent = Intent(this, LoginActivity::class.java)
startActivity(intent)
```

In this practical, an Explicit Intent is used to open the **Login Activity**.

## 3. Make Call to Specific Number

Use the `tel:` URI scheme with an Intent.

```kotlin
val intent = Intent(Intent.ACTION_DIAL)
intent.data = Uri.parse("tel:9876543210")
startActivity(intent)
```

`ACTION_DIAL` opens the dialer with the number filled in. Directly placing a call requires the `CALL_PHONE` permission.

## 4. Open Specific URL

```kotlin
val intent = Intent(Intent.ACTION_VIEW)
intent.data = Uri.parse("https://www.google.com")
startActivity(intent)
```

Android opens an appropriate browser.

## 5. Open Call Log

```kotlin
val intent = Intent(Intent.ACTION_VIEW)
intent.type = CallLog.Calls.CONTENT_TYPE
startActivity(intent)
```

`CallLog.Calls.CONTENT_TYPE` identifies Call Log content.

## 6. Open Gallery

```kotlin
val intent = Intent(Intent.ACTION_VIEW)
intent.type = "image/*"
startActivity(intent)
```

The `image/*` MIME type indicates image content.

## 7. Set Alarm

```kotlin
val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
    putExtra(AlarmClock.EXTRA_HOUR, 7)
    putExtra(AlarmClock.EXTRA_MINUTES, 30)
}
startActivity(intent)
```

This opens the device's alarm application with the specified time.

## 8. Open Camera

Using the modern Activity Result API:

```kotlin
private val cameraLauncher =
    registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            // Handle captured image
        }
    }
```

Launch it using:

```kotlin
cameraLauncher.launch(null)
```

Camera permission may be required depending on the implementation.

## 9. Open Login Activity

Create `LoginActivity` and launch it using an Explicit Intent:

```kotlin
val intent = Intent(this, LoginActivity::class.java)
startActivity(intent)
```

## 10. Important Intent Actions

| Intent Action | Purpose |
|---|---|
| `Intent.ACTION_DIAL` | Open phone dialer |
| `Intent.ACTION_VIEW` | Display/open data using a suitable application |
| `AlarmClock.ACTION_SET_ALARM` | Set an alarm |
| Explicit Intent | Open a specific Activity |

## 11. Important URI and MIME Types

### Telephone

```text
tel:
```

Example:

```kotlin
Uri.parse("tel:9876543210")
```

### URL

```kotlin
Uri.parse("https://www.google.com")
```

### Image

```text
image/*
```

### Call Log

```kotlin
CallLog.Calls.CONTENT_TYPE
```

### Contacts

```kotlin
ContactsContract.Contacts.CONTENT_TYPE
```

## 12. Permissions

Permissions are declared in `AndroidManifest.xml`.

Example:

```xml
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.CAMERA" />
```

Check a permission using:

```kotlin
ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.CAMERA
)
```

Request it using:

```kotlin
ActivityCompat.requestPermissions(
    this,
    arrayOf(Manifest.permission.CAMERA),
    CAMERA_PERMISSION_CODE
)
```

Modern Android applications can also use `ActivityResultContracts.RequestPermission`.

## 13. ActivityResultContracts

`ActivityResultContracts` provides a modern API for launching activities and receiving results.

Example:

```kotlin
private val cameraLauncher =
    registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        // Handle result
    }
```

## 14. UI Components

### Button
Buttons are used to trigger each Intent operation, such as Call, URL, Gallery, Camera, Alarm, and Login.

### ConstraintLayout
Used to arrange UI elements by applying constraints between views.

### CoordinatorLayout
A ViewGroup that coordinates interactions between its child views and is commonly used with components such as Snackbar and AppBarLayout.

## 15. Expected Application Flow

```text
                         Main Activity
                              |
       +----------+-----------+----------+----------+
       |          |           |          |          |
      Call       URL       Call Log    Gallery    Alarm
       |          |           |          |          |
    Dialer     Browser     Call Log   Gallery   Alarm App

                         |
                       Camera
                         |
                    Camera App

                         |
                       Login
                         |
                   Login Activity
```

## 16. Android Project Resources

### Add Drawable Resource

Drawable files can be added to:

```text
app/src/main/res/drawable/
```

They can then be referenced as:

```xml
android:src="@drawable/logo"
```

### Add Activity

A new Activity can be created through:

**Project → New → Activity → Empty Views Activity**

The new Activity can then be launched using an Explicit Intent.

## 17. Learning Outcomes

After completing this practical, the student will be able to:

- Understand Android Intent.
- Differentiate between Implicit and Explicit Intent.
- Use different Intent actions.
- Open external applications using Implicit Intent.
- Navigate between Activities using Explicit Intent.
- Use `Intent.setData()` and `Intent.setType()`.
- Use `Uri.parse()`.
- Work with URI schemes and MIME types.
- Understand Android Manifest and runtime permissions.
- Use `ContextCompat.checkSelfPermission()`.
- Use `ActivityCompat.requestPermissions()`.
- Use `ActivityResultContracts`.
- Work with Buttons and ConstraintLayout.
- Add Activities and Drawable resources.

## Conclusion

Practical-3 demonstrates communication between Android applications and Activities using **Implicit and Explicit Intents**. It also introduces Intent actions, URI and MIME types, permissions, Activity Result APIs, and Android UI components. These concepts are essential for building interactive Android applications that can access device features and other applications.
