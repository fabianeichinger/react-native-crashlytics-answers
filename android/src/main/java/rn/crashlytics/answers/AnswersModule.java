/**
 * Copyright (c) 2016-present, see CONTRIBUTORS
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

package rn.crashlytics.answers;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.AnswersEvent;
import com.crashlytics.android.answers.CustomEvent;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;

public class AnswersModule extends BaseJavaModule {

  private final Answers answers;

  public AnswersModule() {
    this(Answers.getInstance());
  }

  public AnswersModule(Answers answers) {
    this.answers = answers;
  }

  private static void addCustomAttribute(AnswersEvent event, ReadableMap attrMap, String attrKey) {
    ReadableType type = attrMap.getType(attrKey);
    switch (type) {
      case Number:
        event.putCustomAttribute(attrKey, attrMap.getDouble(attrKey));
        break;
      case String:
        event.putCustomAttribute(attrKey, attrMap.getString(attrKey));
        break;
      case Boolean:
        event.putCustomAttribute(attrKey, String.valueOf(attrMap.getBoolean(attrKey)));
        break;
      case Null:
        break; // skip
      default:
        throw new IllegalArgumentException("Can't handle Objects or Arrays");
    }
  }

  @ReactMethod
  public void logCustom(String eventName, ReadableMap customAttributes) {
    final CustomEvent event = new CustomEvent(eventName);
    final ReadableMapKeySetIterator readableMapKeySetIterator = customAttributes.keySetIterator();

    while (readableMapKeySetIterator.hasNextKey()) {
      final String key = readableMapKeySetIterator.nextKey();
      addCustomAttribute(event, customAttributes, key);
    }

    answers.logCustom(event);
  }

  @Override
  public String getName() {
    return "CrashlyticsAnswers";
  }
}
