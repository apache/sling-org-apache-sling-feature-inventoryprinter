/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.feature.inventoryservice.impl;

import java.io.PrintWriter;

import org.apache.felix.inventory.Format;
import org.apache.felix.inventory.InventoryPrinter;
import org.apache.sling.feature.Feature;
import org.apache.sling.feature.r2f.RuntimeEnvironment2FeatureModel;
import org.osgi.framework.BundleContext;

abstract class AbstractFeatureInventoryPrinter implements InventoryPrinter {

    protected BundleContext bundleContext;

    protected RuntimeEnvironment2FeatureModel generator;

    @Override
    public final void print(PrintWriter printWriter, Format format, boolean isZip) {
        try {
            Feature launchFeature = generator.getLaunchFeature(bundleContext);
            onLaunchFeature(launchFeature, printWriter);
        } catch (Throwable t) {
            t.printStackTrace(printWriter);
        }
        printWriter.println();
    }

    protected abstract void onLaunchFeature(Feature launchFeature, PrintWriter printWriter) throws Exception;

}
