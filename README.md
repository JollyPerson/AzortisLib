# AzortisLib ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/com.azortis/azortislib.svg?style=flat-square) ![Spiget Version](https://img.shields.io/spiget/version/64232.svg?label=spigot&style=flat-square)

An extensive utility library for [Spigot](https://www.spigotmc.org/)

## Spigot
Please also check out our [Spigot page](https://www.spigotmc.org/resources/azortislib.64232/) for the library.

## Maven
Add the following to your pom.xml

#### Repository

```xml
<repositories>
    <repository>
        <id>ossrh</id>
        <url>https://oss.sonatype.org/content/groups/public/</url>
    </repository>
</repositories>
```

#### Dependency

```xml
<dependencies>
    <dependency>
        <groupId>com.azortis</groupId>
        <artifactId>azortislib</artifactId>
        <version>@VERSION</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

**Please note that in order to use this library you must shade it into your resource. If you do not don't know how please refer to the [maven-shade-plugin](https://maven.apache.org/plugins/maven-shade-plugin/) page. Also make sure you relocate this resource, you can find more about relocations [here](https://maven.apache.org/plugins/maven-shade-plugin/examples/class-relocation.html).**
