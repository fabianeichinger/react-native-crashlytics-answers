/**
 * Copyright (c) 2016-present, see CONTRIBUTORS
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */
 
package rn.crashlytics.answers;

import com.crashlytics.android.answers.Answers;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.List;

public class AnswersPackage implements ReactPackage {

  final Answers answers;

  public AnswersPackage() {
    this(null);
  }

  public AnswersPackage(Answers answers) {
    this.answers = answers;
  }

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
    if (answers != null)
      return Collections.<NativeModule>singletonList(new AnswersModule(answers));
    else
      return Collections.<NativeModule>singletonList(new AnswersModule());
  }

  @Override
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return Collections.emptyList();
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
    return Collections.emptyList();
  }
}
