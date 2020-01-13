# 简介

这个项目帮助我们快速掌握 `SprignBoot` 项目如何快速集成 `docker` 插件并将相关指令绑定到 `maven` 的声明周期中，从而快速实现镜像的构建、上传。


## 插件管理

我们的项目可能会统一配置 `docker` 的插件及相关参数，如：`镜像地址`、`命名空间`、`插件版本` 等。这里给出一个 `docker` 插件管理的模板。

* `properties` 参数配置

> 我使用了阿里云提供的镜像服务（请注意替换为自己的阿里云仓库），不会的可以去阿里云镜像服务看下相关介绍、教程。

```xml
    <!-- 参数配置 -->
    <properties>
        <!-- docker 配置 -->
        <docker.plugin.version>0.4.13</docker.plugin.version>
        <!-- 阿里云镜像仓库服务器地址 -->
        <docker.repostory>registry.cn-shanghai.aliyuncs.com</docker.repostory>
        <!-- 阿里云容器镜像的命名空间 -->
        <docker.registry.namespace>rexlin600</docker.registry.namespace>
    </properties>
```

* `build` 模块之 `resources` 管理

> 定义打包镜像时，需要将那些静态资源文件一并打包；也可以指定 targetPath（指定打包放入的位置）

```xml
    <!-- 表示打包时，将resources目录下的配置文件一并打入 -->
    <!-- 使用@@站位符，输出Dockerfile至docker文件夹 -->
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.yml</include>
                <include>**/*.properties</include>
            </includes>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.yml</include>
                <include>**/*.properties</include>
            </includes>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/docker</directory>
            <filtering>true</filtering>
            <includes>
                <include>**/Dockerfile</include>
            </includes>
            <targetPath>../docker</targetPath>
        </resource>
```

* `build` 模块之 `pluginManagement` 管理

> docker-maven-plugin 插件，executions 节点下绑定 docker 命令（build、tag、push）到 maven 生命周期的命令（package、deploy）；
> configuration 节点则是指明 docker 插件的配置：阿里云容器仓库配置、镜像名称、镜像tag、复制jar包等

```xml
    <pluginManagement>
        <plugins>
            <!-- 打包 -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring-boot.version}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <!-- docker 打包插件：可以直接copy使用，注意复制 properties，详细配置步骤参考 doc -->
            <!-- 绑定maven生命周期与docker image -->
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>${docker.plugin.version}</version>
                <executions>
                    <execution>
                        <id>build-image</id>
                        <phase>package</phase>
                        <goals>
                            <goal>build</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>tag-image</id>
                        <phase>package</phase>
                        <goals>
                            <goal>tag</goal>
                        </goals>
                        <configuration>
                            <image>
                                ${docker.repostory}/${docker.registry.namespace}/${project.artifactId}:${project.version}
                            </image>
                            <newName>
                                ${docker.repostory}/${docker.registry.namespace}/${project.artifactId}:${project.version}
                            </newName>
                        </configuration>
                    </execution>
                    <execution>
                        <id>push-image</id>
                        <phase>deploy</phase>
                        <goals>
                            <goal>push</goal>
                        </goals>
                        <configuration>
                            <imageName>
                                ${docker.repostory}/${docker.registry.namespace}/${project.artifactId}:${project.version}
                            </imageName>
                        </configuration>
                    </execution>
                </executions>
                <!-- docker-plugin 配置：阿里云私有仓库 -->
                <configuration>
                    <serverId>docker-aliyun</serverId>
                    <registryUrl>${docker.repostory}</registryUrl>
                    <pushImageTag>false</pushImageTag>
                    <!-- 指定Dockerfile文件所在的目录 -->
                    <dockerDirectory>${project.build.directory}/docker</dockerDirectory>
                    <imageName>
                        ${docker.repostory}/${docker.registry.namespace}/${project.artifactId}:${project.version}
                    </imageName>
                    <imageTags>
                        <imageTag>${project.version}</imageTag>
                        <!--<imageTag>latest</imageTag>-->
                    </imageTags>
                    <!-- 复制指定的文件到 dockerDirectory 指定的位置 -->
                    <resources>
                        <!-- jar文件 -->
                        <resource>
                            <targetPath>/</targetPath>
                            <directory>${project.build.directory}</directory>
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                        <!-- example：拷贝其他文件到 target/docker 下，这个文件夹包含拷贝过来的 Dockerfile（resourecs指定的，接着就可以使用 COPY、ADD 命令了 -->
                        <!--<resource>-->
                        <!--<targetPath>/</targetPath>-->
                        <!--<directory>/Users/rexlin600/IDEA_WorkSpace/ms4all</directory>-->
                        <!--<include>.editorconfig</include>-->
                        <!--</resource>-->
                    </resources>
                </configuration>
            </plugin>
        </plugins>
    </pluginManagement>
```

* `build` 模块之 `plugins` 管理

> 引入 docker 插件即可实现在使用 maven 命令构建过程中打包镜像并上传至阿里云镜像仓库

```xml
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>

        <!-- docker plugin -->
        <!-- 当你本机开启并配置好本机以及上面的 docker 相关参数后就可以打开下面这个插件了 -->
        <!--<plugin>-->
        <!--<groupId>com.spotify</groupId>-->
        <!--<artifactId>docker-maven-plugin</artifactId>-->
        <!--</plugin>-->
    </plugins>
```

