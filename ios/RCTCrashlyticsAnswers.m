/**
 * Copyright (c) 2016-present, see CONTRIBUTORS
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

#import "RCTCrashlyticsAnswers.h"

@implementation RCTCrashlyticsAnswers
RCT_EXPORT_MODULE(CrashlyticsAnswers);

/*
 * Private implementations
 */
-(void) logCustomEventWithName:(NSString*)name customAttributes:(NSDictionary*)customAttributes
{
  [Answers logCustomEventWithName:name
                 customAttributes:customAttributes];
}

/*
 * Export method for logging a custom event (borrowing the API from Answers for Android) to JS
 */
RCT_EXPORT_METHOD(logCustom:(NSString*)eventName customAttributes:(NSDictionary*)customAttributes) {
  [self logCustomEventWithName:eventName customAttributes:customAttributes];
}
@end
