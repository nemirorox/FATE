/*
 * Copyright 2019 The FATE Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.ai.eggroll.framework.roll.api.grpc.observer.kv.roll;

import com.webank.ai.eggroll.api.storage.Kv;
import com.webank.ai.eggroll.core.api.grpc.observer.BaseCallerResponseStreamObserver;
import com.webank.ai.eggroll.core.utils.ErrorUtils;
import com.webank.ai.eggroll.core.utils.ToStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Scope("prototype")
public class RollKvCreateResponseObserver extends BaseCallerResponseStreamObserver<Kv.CreateTableInfo, Kv.CreateTableInfo> {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private ToStringUtils toStringUtils;
    @Autowired
    private ErrorUtils errorUtils;

    public RollKvCreateResponseObserver(CountDownLatch finishLatch) {
        super(finishLatch);
    }

    @Override
    public void onNext(Kv.CreateTableInfo createTableInfo) {
        LOGGER.info("Kv.CreateTableInfo result: {}", toStringUtils.toOneLineString(createTableInfo));
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.error(errorUtils.getStackTrace(throwable));
        super.onError(throwable);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
    }
}
