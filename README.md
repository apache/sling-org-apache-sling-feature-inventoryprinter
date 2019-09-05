[<img src="https://sling.apache.org/res/logos/sling.png"/>](https://sling.apache.org)

 [![Build Status](https://builds.apache.org/buildStatus/icon?job=Sling/sling-org-apache-sling-feature-inventoryprinter/master)](https://builds.apache.org/job/Sling/job/sling-org-apache-sling-feature-inventoryprinter/job/master) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![feature](https://sling.apache.org/badges/group-feature.svg)](https://github.com/apache/sling-aggregator/blob/master/docs/groups/feature.md)

# Apache Sling Feature Model Inventory Printer

This bundle registers Inventory Printer services to introspect the feature model at runtime, for example via the Web Console.

The Apache Felix Inventory Printer API is defined [here](https://github.com/apache/felix/blob/trunk/inventory/src/main/java/org/apache/felix/inventory/InventoryPrinter.java)

This bundle introduces new status reporters`:

 * _Sling Feature - Launch_: the Feature model used to launch the platform (see `/system/console/status-feature_launch.json`);
 * _Sling Feature - Running_: the Feature model computed by scanning the BundleContext (see `/system/console/status-feature_running.json`);
 * _Sling Feature - Launch 2 Runtime_: the Feature model which computes the differences between the Launch and Running Features (see `/system/console/status-feature_launch2running.json`);
 * _Sling Feature - Runtime_: the assembled Feature model from the Launch 2 Runtime (see `/system/console/status-feature_runtime.json`).
