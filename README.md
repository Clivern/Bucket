<p align="center">
	<img alt="Bucket Logo" src="https://raw.githubusercontent.com/Clivern/Bucket/main/images/logo.png" height="150" />
	<h3 align="center">Bucket</h3>
	<p align="center">Consistent Hashing Package for Scalable Data Distribution.</p>
	<p align="center">
        <a href="https://github.com/Clivern/Bucket/actions/workflows/ci.yml">
            <img src="https://github.com/Clivern/Bucket/actions/workflows/ci.yml/badge.svg">
        </a>
		<a href="http://www.javadoc.io/doc/com.clivern/bucket">
            <img src="http://www.javadoc.io/badge/com.clivern/bucket.svg">
        </a>
		<a href="https://mvnrepository.com/artifact/com.clivern/bucket/0.1.0">
            <img src="https://img.shields.io/maven-central/v/com.clivern/bucket.svg">
        </a>
		<a href="https://github.com/Clivern/Bucket/blob/main/LICENSE">
            <img src="https://img.shields.io/badge/LICENSE-Apache_2.0-orange.svg">
        </a>
	</p>
</p>

Consistent Hashing was introduced in the year 1997. It’s used in Amazon’s Dynamo DB as a partitioning component. Further, open-source applications such as Apache Cassandra use it for data partitioning. In order to fully understand how it works, please [check this guide first.](https://medium.datadriveninvestor.com/consistent-hashing-an-efficient-scalable-data-distribution-algorithm-a81fc5c0a6c7)


## Documentation

### Installation

To add a dependency using Maven, use the following:

```xml
<dependency>
    <groupId>com.clivern</groupId>
    <artifactId>bucket</artifactId>
    <version>0.1.0</version>
</dependency>
```

To add a dependency using Gradle, use the following:

```java
dependencies {
    compile 'com.clivern:bucket:0.1.0'
}
```

To add a dependency using Scala SBT, use the following:

```java
libraryDependencies += "com.clivern" % "bucket" % "0.1.0"
```

### Usage

Here is a basic example

```java
import com.clivern.bucket.Bucket;
import com.clivern.bucket.ServiceNode;


ServiceNode node1 = new ServiceNode("DC1", "127.0.0.1", 8080);
ServiceNode node2 = new ServiceNode("DC2", "127.0.0.2", 8081);
ServiceNode node3 = new ServiceNode("DC3", "127.0.0.3", 8082);
ServiceNode node4 = new ServiceNode("DC4", "127.0.0.4", 8084);
ServiceNode node5 = new ServiceNode("DC5", "127.0.0.5", 8085);
ServiceNode node6 = new ServiceNode("DC6", "127.0.0.6", 8086);
ServiceNode node7 = new ServiceNode("DC7", "127.0.0.7", 8087);
Bucket<ServiceNode> bucket =
        new Bucket<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7), 10);

// Get the node used for allocation for something with id 1
ServiceNode node = (ServiceNode) bucket.routeNode("1");
System.out.println(node);
```


## Versioning

For transparency into our release cycle and in striving to maintain backward compatibility, Bucket is maintained under the [Semantic Versioning guidelines](https://semver.org/) and release process is predictable and business-friendly.

See the [Releases section of our GitHub project](https://github.com/clivern/bucket/releases) for changelogs for each release version of Bucket. It contains summaries of the most noteworthy changes made in each release.


## Bug tracker

If you have any suggestions, bug reports, or annoyances please report them to our issue tracker at https://github.com/clivern/bucket/issues


## Security Issues

If you discover a security vulnerability within Bucket, please send an email to [hello@clivern.com](mailto:hello@clivern.com)


## Contributing

We are an open source, community-driven project so please feel free to join us. see the [contributing guidelines](CONTRIBUTING.md) for more details.


## License

© 2021, Clivern. Released under [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).

**Bucket** is authored and maintained by [@Clivern](http://github.com/clivern).
