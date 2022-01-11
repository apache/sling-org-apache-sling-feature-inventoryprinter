[![Apache Sling](https://sling.apache.org/res/logos/sling.png)](https://sling.apache.org)

&#32;[![Build Status](https://ci-builds.apache.org/job/Sling/job/modules/job/sling-org-apache-sling-feature-inventoryprinter/job/master/badge/icon)](https://ci-builds.apache.org/job/Sling/job/modules/job/sling-org-apache-sling-feature-inventoryprinter/job/master/)&#32;[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=apache_sling-org-apache-sling-feature-inventoryprinter&metric=coverage)](https://sonarcloud.io/dashboard?id=apache_sling-org-apache-sling-feature-inventoryprinter)&#32;[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=apache_sling-org-apache-sling-feature-inventoryprinter&metric=alert_status)](https://sonarcloud.io/dashboard?id=apache_sling-org-apache-sling-feature-inventoryprinter)&#32;[![JavaDoc](https://www.javadoc.io/badge/org.apache.sling/org.apache.sling.feature.inventoryprinter.svg)](https://www.javadoc.io/doc/org.apache.sling/org.apache.sling.feature.inventoryprinter)&#32;[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.apache.sling/org.apache.sling.feature.inventoryprinter/badge.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.apache.sling%22%20a%3A%22org.apache.sling.feature.inventoryprinter%22)&#32;[![feature](https://sling.apache.org/badges/group-feature.svg)](https://github.com/apache/sling-aggregator/blob/master/docs/groups/feature.md) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

# Apache Sling Feature Model Inventory Printer

This bundle registers Inventory Printer services to introspect the feature model at runtime, for example via the Web Console.

The Apache Felix Inventory Printer API is defined [here](https://github.com/apache/felix-dev/blob/master/inventory/src/main/java/org/apache/felix/inventory/InventoryPrinter.java)

This bundle introduces new status reporters`:

 * _Sling Feature - Launch_: the Feature model used to launch the platform (see `/system/console/status-feature_launch.json`);
 * _Sling Feature - Running_: the Feature model computed by scanning the BundleContext (see `/system/console/status-feature_running.json`);
 * _Sling Feature - Launch 2 Runtime_: the Feature model which computes the differences between the Launch and Running Features (see `/system/console/status-feature_launch2running.json`);
 * _Sling Feature - Runtime_: the assembled Feature model from the Launch 2 Runtime (see `/system/console/status-feature_runtime.json`).
