/*
* Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.wso2.micro.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  Carbon Thread Factory which can be used to identify thread specific to tasks.
 */
public class CarbonThreadFactory implements ThreadFactory {

    private ThreadGroup threadGroup;
    private AtomicInteger count = new AtomicInteger();

    public CarbonThreadFactory(ThreadGroup threadGroup) {
        this.threadGroup = threadGroup;
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = threadGroup.getName() + "-" + count.incrementAndGet();
        return new Thread(threadGroup, r, name);
    }
}
