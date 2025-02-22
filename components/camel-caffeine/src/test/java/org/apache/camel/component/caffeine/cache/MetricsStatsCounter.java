/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.caffeine.cache;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.github.benmanes.caffeine.cache.stats.StatsCounter;

public class MetricsStatsCounter implements StatsCounter {
    private final Counter hitCount;
    private final Counter missCount;
    private final Counter loadSuccessCount;
    private final Counter loadFailureCount;
    private final Timer totalLoadTime;
    private final Counter evictionCount;
    private final Counter evictionWeight;

    public MetricsStatsCounter(MetricRegistry registry) {
        hitCount = registry.counter("camelcache.hits");
        missCount = registry.counter("camelcache.misses");
        totalLoadTime = registry.timer("camelcache.loads");
        loadSuccessCount = registry.counter("camelcache.loads-success");
        loadFailureCount = registry.counter("camelcache.loads-failure");
        evictionCount = registry.counter("camelcache.evictions");
        evictionWeight = registry.counter("camelcache.evictions-weight");
    }

    @Override
    public void recordHits(int count) {
        hitCount.inc(count);
    }

    @Override
    public void recordMisses(int count) {
        missCount.inc(count);
    }

    @Override
    public void recordLoadSuccess(long loadTime) {
        loadSuccessCount.inc();
        totalLoadTime.update(loadTime, TimeUnit.NANOSECONDS);
    }

    @Override
    public void recordLoadFailure(long loadTime) {
        loadFailureCount.inc();
        totalLoadTime.update(loadTime, TimeUnit.NANOSECONDS);
    }

    @Override
    @Deprecated
    public void recordEviction() {
        recordEviction(1, RemovalCause.EXPLICIT);
    }

    @Override
    public void recordEviction(int weight, RemovalCause removalCause) {
        evictionCount.inc();
        evictionWeight.inc(weight);
    }

    @Override
    public CacheStats snapshot() {
        return CacheStats.of(
                hitCount.getCount(), missCount.getCount(), loadSuccessCount.getCount(), loadFailureCount.getCount(),
                totalLoadTime.getCount(),
                evictionCount.getCount(), evictionWeight.getCount());
    }

    @Override
    public String toString() {
        return snapshot().toString();
    }
}
