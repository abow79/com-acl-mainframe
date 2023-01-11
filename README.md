# com-gateway-example

## Install JDK 17

The Project must be executed in a JDK 17 environment by setting the JAVA_HOME environment variable

```shell
export JAVA_HOME=/Library/Java/JavaVirtualMachines/openjdk-17.jdk/Contents/Home
```

## Install dependent artifacts in local maven repository

```shell
mvn install:install-file -Dfile=fcbframework-core\fcbframework-core-1.0.0.jar -DgroupId=tw.com.firstbank.fcbcore.fcbframework -DartifactId=fcbframework-core -Dversion=1.0.0 -Dpackaging=jar -DpomFile=fcbframework-core\pom.xml
mvn install:install-file -Dfile=fcbframework-core-extensions-transaction\fcbframework-core-extensions-transaction-1.0.0.jar -DgroupId=tw.com.firstbank.fcbcore.fcbframework -DartifactId=fcbframework-core-extensions-transaction -Dversion=1.0.0 -Dpackaging=jar -DpomFile=fcbframework-core-extensions-transaction\pom.xml
mvn install:install-file -Dfile=fcbframework-adapters-rest\fcbframework-adapters-rest-1.0.0.jar -DgroupId=tw.com.firstbank.fcbcore.fcbframework -DartifactId=fcbframework-adapters-rest -Dversion=1.0.0 -Dpackaging=jar -DpomFile=fcbframework-adapters-rest\pom.xml
mvn install:install-file -Dfile=fcbframework-adapters-rabbitmq\fcbframework-adapters-rabbitmq-1.0.0.jar -DgroupId=tw.com.firstbank.fcbcore.fcbframework -DartifactId=fcbframework-adapters-rabbitmq -Dversion=1.0.0 -Dpackaging=jar -DpomFile=fcbframework-adapters-rabbitmq\pom.xml
mvn install:install-file -Dfile=fcbframework-adapters-jms\fcbframework-adapters-jms-1.0.0.jar -DgroupId=tw.com.firstbank.fcbcore.fcbframework -DartifactId=fcbframework-adapters-jms -Dversion=1.0.0 -Dpackaging=jar -DpomFile=fcbframework-adapters-jms\pom.xml
mvn install:install-file -Dfile=fcbframework-adapters-soap\fcbframework-adapters-soap-1.0.0.jar -DgroupId=tw.com.firstbank.fcbcore.fcbframework -DartifactId=fcbframework-adapters-soap -Dversion=1.0.0 -Dpackaging=jar -DpomFile=fcbframework-adapters-soap\pom.xml
```

# Package com-gateway-example module

Packaging jar for all environments

```shell
mvn clean package
```

# Run com-gateway-example

透過 **spring_profiles_active** 環境變數，來指定套用的 application yaml

* spring_profiles_active=default (本機環境)
* spring_profiles_active=dev (DEV環境) **default**
* spring_profiles_active=sit (SIT環境)
* spring_profiles_active=uat (UAT環境)
* spring_profiles_active=prod (PROD環境)

透過yml檔 **logging.config** 定義，來指定 ap log 是否改輸出到 console (僅適用於 application.yaml 本機環境)

* logging.config=classpath:logback-default.xml (輸出到 console)
* logging.config=classpath:logback-eik.xml (輸出到 logstash) **default**

```shell
export spring_profiles_active=[dev|sit|uat|prod]
java -jar target/com-gateway-example-1.0.0.jar
```

# dependency check

* 第一次執行會需要下載 NVD 資源，會花一點時間下載，後續僅更新差異則時間會縮短
* 產生報告路徑： /target/dependency-check-report.html

透過 plugin 指令執行

```shell
mvn clean dependency-check:check
```

透過 maven build 指令執行

```shell
mvn clean [verify|install|site|deploy]
```