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

import static org.apache.felix.inventory.InventoryPrinter.FORMAT;
import static org.apache.felix.inventory.InventoryPrinter.NAME;
import static org.apache.felix.inventory.InventoryPrinter.TITLE;
import static org.apache.sling.feature.diff.FeatureDiff.compareFeatures;

import org.apache.felix.inventory.InventoryPrinter;
import org.apache.sling.feature.ArtifactId;
import org.apache.sling.feature.Feature;
import org.apache.sling.feature.diff.DefaultDiffRequest;
import org.apache.sling.feature.r2f.RuntimeEnvironment2FeatureModel;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    service = InventoryPrinter.class,
    property = {
        NAME + "=launch2runtime_feature",
        TITLE + "=Sling launch 2 runtime diff",
        FORMAT + "=JSON"
    },
    reference = {
        @Reference(field = "generator", name = "generator", service = RuntimeEnvironment2FeatureModel.class)
    }
)
public class Launch2RuntimeInventoryPrinter extends AbstractRuntimeEnvironment2FeatureModelPrinter {

    @Activate
    public void start(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    @Override
    protected Feature compute(Feature launchFeature, Feature runtimeFeature) {
        Feature featureDiff = compareFeatures(new DefaultDiffRequest()
                                              .setPrevious(launchFeature)
                                              .setCurrent(runtimeFeature)
                                              .addIncludeComparator("bundles")
                                              .addIncludeComparator("configurations")
                                              .setResultId(new ArtifactId(runtimeFeature.getId().getGroupId(),
                                                                          runtimeFeature.getId().getArtifactId(), 
                                                                          runtimeFeature.getId().getVersion(),
                                                                          runtimeFeature.getId().getClassifier() + "_updater",
                                                                          runtimeFeature.getId().getType())));

        return featureDiff;
    }

}
