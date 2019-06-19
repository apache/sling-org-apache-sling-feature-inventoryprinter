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

import static org.apache.felix.inventory.InventoryPrinter.FORMAT;
import static org.apache.felix.inventory.InventoryPrinter.NAME;
import static org.apache.felix.inventory.InventoryPrinter.TITLE;

import org.apache.felix.inventory.InventoryPrinter;
import org.apache.sling.feature.Feature;
import org.apache.sling.feature.r2f.RuntimeEnvironment2FeatureModel;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    service = InventoryPrinter.class,
    property = {
        NAME + "=launch_feature",
        TITLE + "=Sling launch Feature",
        FORMAT + "=JSON"
    },
    reference = {
        @Reference(field = "generator", name = "generator", service = RuntimeEnvironment2FeatureModel.class)
    }
)
public class FeaturesInventoryPrinter extends AbstractFeatureInventoryPrinter {

    @Override
    protected Feature getComputedFeature() {
        return generator.getLaunchFeature();
    }

}
