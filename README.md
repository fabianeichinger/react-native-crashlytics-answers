# Answers for React Native
React Native bindings for Fabric's [Answers](https://answers.io) SDK.

## Disclaimer
This library is not provided or supported by Crashlytics.

## Setup
TODO: Put this on npm maybe.

These steps assume the project to have the same structure as the skeleton app created by `react-native init <your project name>` (as of React Native v0.18-rc).
All file paths are relative to your project root.

Step 1: Clone this repo into the root folder of your project.

`git clone https://github.com/feichngr/react-native-crashlytics-answers`;

### Android
Step 2: Follow the instructions on [Fabric.io](https://fabric.io) to add Answers to your Android project and make sure it works on the Java side.

Step 3: Add the binding library project to your Gradle settings file (`./android/settings.gradle`) by adding
```groovy
include ':ReactNativeCrashlyticsAnswers'
project(':ReactNativeCrashlyticsAnswers').projectDir = new File(rootProject.projectDir, '../react-native-crashlytics-answers/android')
```

Step 4: Add a dependency on the library to your app's build.gradle file (`./android/app/build.gradle`) by modifying the `dependencies` section
```groovy
dependencies {
    ... // your other dependencies
    compile project(':ReactNativeCrashlyticsAnswers')
```

Step 5: Add the `AnswersPackage` to your `ReactActivity` (usually `./android/app/src/main/java/<your package path>/MainActivity.java`)
```java
... // your imports

import rn.crashlytics.answers.AnswersPackage;

public class ... // your Activity class header

  @Override
  protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
        new MainReactPackage()
      , new AnswersPackage()
      , ... // other packages if you have any
    );
  }
```

### iOS
Step 2: Follow the instructions on [Fabric.io](https://fabric.io) to add Answers to your iOS project and make sure it works on the ObjC/Swift side.

Step 3: Open your iOS project in Xcode.

Step 4: Add a new Group to `Libraries` in the Project navigator and call it `ReactNativeCrashlyticsAnswers`.

Step 5: Right-click on the new Group, `Add files to <your project name>`, navigate to `./react-native-crashlytics-answers/ios` and select both the `.m` and `.h` file.

## Usage
Note: Only logging of custom events is implemented.

Step 1: Import `NativeModules` from `react-native`
```js
import {NativeModules} from 'react-native';
```

Step 2: Get the `CrashlyticsAnswers` module
```js
const { CrashlyticsAnswers } = NativeModules;
```

Step 3: Call the `logCustom` function with the event name and an object of extra attributes (nullable, but must be specified)
```js
CrashlyticsAnswers.logCustom('An Event from JS', {'Does it work?': true,
                                                  'Is it handy?': 'totally',
                                                  'Rating out of 10?': 10});
```

## License
BSD-style, see the [LICENSE](https://github.com/feichngr/react-native-crashlytics-answers/blob/master/LICENSE) file.
