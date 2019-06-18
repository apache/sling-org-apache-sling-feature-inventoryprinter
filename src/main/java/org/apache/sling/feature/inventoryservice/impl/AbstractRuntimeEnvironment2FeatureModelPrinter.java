/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.sling.feature.inventoryservice.impl;

import static org.apache.sling.feature.io.json.FeatureJSONReader.read;
import static org.apache.sling.feature.io.json.FeatureJSONWriter.write;

import java.io.BufferedReader;
import java.io.PrintWriter;

import org.apache.sling.feature.ArtifactId;
import org.apache.sling.feature.Feature;
import org.apache.sling.feature.r2f.ConversionRequest;
import org.apache.sling.feature.r2f.DefaultConversionRequest;
import org.apache.sling.feature.r2f.RuntimeEnvironment2FeatureModel;
import org.osgi.service.component.annotations.Reference;

abstract class AbstractRuntimeEnvironment2FeatureModelPrinter extends AbstractFeatureInventoryPrinter {

    @Reference
    RuntimeEnvironment2FeatureModel generator;

    @Override
    protected void onFeature(String featureLocation, BufferedReader reader, PrintWriter printWriter) throws Exception {
        Feature previousFeature = read(reader, featureLocation);

        String groupId = previousFeature.getId().getGroupId();
        String artifactId = previousFeature.getId().getArtifactId();
        String version = previousFeature.getId().getArtifactId();
        String classifier = previousFeature.getId().getArtifactId() + "-RUNTIME";

        ConversionRequest request = new DefaultConversionRequest()
                                    .setBundleContext(bundleContext)
                                    .setResultId(new ArtifactId(groupId, artifactId, version, classifier, null));
        Feature currentFeature = generator.scanAndAssemble(request);

        Feature computedFeature = compute(previousFeature, currentFeature);

        write(printWriter, computedFeature);
    }

    protected abstract Feature compute(Feature previousFeature, Feature currentFeature);

}
