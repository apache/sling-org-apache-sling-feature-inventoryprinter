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

import org.apache.sling.feature.ArtifactId;
import org.apache.sling.feature.Feature;
import org.apache.sling.feature.diff.DefaultDiffRequest;
import org.osgi.service.component.annotations.Component;

@Component(
    property = {
        NAME + "=r2f_base2runtime",
        TITLE + "=Apache Sling Runtime Environment to Feature Model converter - Base 2 Runtime diff Generator",
        FORMAT + "=JSON"
    }
)
public class BaseFeature2CurrentRuntimePrinter extends AbstractRuntimeEnvironment2FeatureModelPrinter {

    @Override
    protected Feature compute(Feature previousFeature, Feature currentFeature) {
        Feature featureDiff = compareFeatures(new DefaultDiffRequest()
                                              .setPrevious(previousFeature)
                                              .setCurrent(currentFeature)
                                              .addIncludeComparator("artifacts")
                                              .addIncludeComparator("configurations")
                                              .setResultId(new ArtifactId(currentFeature.getId().getGroupId(),
                                                           currentFeature.getId().getArtifactId(), 
                                                           currentFeature.getId().getVersion(),
                                                           currentFeature.getId().getClassifier() + "_updater",
                                                           currentFeature.getId().getType())));

        return featureDiff;
    }

}
