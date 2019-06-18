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

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.newBufferedReader;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.felix.inventory.Format;
import org.apache.felix.inventory.InventoryPrinter;
import org.osgi.framework.BundleContext;

abstract class AbstractFeatureInventoryPrinter implements InventoryPrinter {

    private static final String SLING_FEATURE_PROPERTY_NAME = "sling.feature";

    protected BundleContext bundleContext;

    @Override
    public final void print(PrintWriter printWriter, Format format, boolean isZip) {
        String featureLocation = bundleContext.getProperty(SLING_FEATURE_PROPERTY_NAME);
        URI previousFeatureURI = URI.create(featureLocation);
        Path previousFeaturePath = Paths.get(previousFeatureURI);

        try (BufferedReader reader = newBufferedReader(previousFeaturePath, UTF_8)) {
            onFeature(featureLocation, reader, printWriter);
        } catch (Exception e) {
            e.printStackTrace(printWriter);
        }
        printWriter.println();
    }

    protected abstract void onFeature(String featureLocation, BufferedReader reader, PrintWriter printWriter) throws Exception;

}
