# 简介

`actuator` 是一款可以帮助你监控和管理 `SpringBoot` 应用，比如健康检查、审计、统计和 `HTTP`追踪等。所有的这些特性可以通过 `JMX` 
或者 `HTTP endpoints` 来获得。

这里提供了在 `SpringBoot 2.x` 集成 `Actuator`（默认在 `/actuator` 节点了）；并提供一个自定义 `endpoint` 的示例。


## 开始发车

* 引入依赖

> 这里引入了 `spring-boot-starter-security` 用来保护程序的端点

```xml
   <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
```

* yaml 配置

```yaml
spring:
  security:
    user:
      name: admin
      password: admin
      # 和 ActuatorSecurity 的角色对应
      roles: admin
# 应用基本信息
info:
  app:
    author: rexlin600
    email: rexlin600@gmail.com
    name: spring-boot-actuator
    version: 2.1.4.RELEASE
    test: actuator-test
# 端点信息    
management:
  # 管理端点端口
  server:
    port: ${server.port}
  endpoints:
    web:
      # ymal中必须加引号
      exposure:
        include: "*"
      # 跨域设置
      cors:
        allowed-methods: "*"
        allowed-origins: "*"
      # 修改访问路径  2.0之前默认是/   2.0默认是 /actuator  可以通过这个属性值修改，必须填写一个值！
      base-path: "/actuator"
    # 【注意】是否启用默认的 endpoint；如果为了安全性，建议设置为 false
    enabled-by-default: true
  endpoint:
    # 启用info endpoint
    info:
      enabled: true
    # 健康信息查看权限
    health:
      show-details: when_authorized
    # 启用 shutdown 端点,优雅关机，默认情况下不启用；实测web项目无效 403
    shutdown:
      enabled: true      
```

* 安全配置

> 只有登录了并具有 admin 角色的用户才可以访问到敏感端点信息

```java
@Configuration
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
                .anyRequest().hasRole("admin")
                .and()
                .httpBasic();
    }
}
```

* 查看端点

如下，访问 `http://localhost:10011/actuator` 会要求登录：

![actuator-login](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/actuator-login.jpg)

我们输入配置的用户名、密码之后即可访问到端点信息：

```text
{
  "_links": {
    "self": {
      "href": "http://localhost:10011/actuator",
      "templated": false
    },
    "myEndPoint": {
      "href": "http://localhost:10011/actuator/myEndPoint",
      "templated": false
    },
    "auditevents": {
      "href": "http://localhost:10011/actuator/auditevents",
      "templated": false
    }
    ...
   }
}
```


## 自定义端点

> 这里我提供一个读取计算机信息的自定义 endpoint（非sigar方式）。当然，你也可以利用自定义端点去实现其他功能。

* 引入依赖

```xml
    <!-- Java系统监控(淘汰sigar) -->
    <dependency>
        <groupId>com.github.oshi</groupId>
        <artifactId>oshi-core</artifactId>
        <version>3.4.4</version>
    </dependency>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.62</version>
    </dependency>
```

* 自定义 endpoint 实现

我们可以通过如下方式实现一个自定义 `endpoint`（全部代码请参考项目中代码示例）并且能在 `actuator` 访问到：

```java
@Endpoint(id = "myEndPoint")
@Component
public class MyEndPoint {

    private static final Double CONSTANT_0D = 0d;
    private static final Double CONSTANT_1D = 1d;

    public static final Map<String, Object> MAP = new LinkedHashMap<>();

    /**
     * 读取系统信息
     *
     * @return
     */
    @ReadOperation
    public Map<String, Object> getSystemInfo() {
        Map<String, Object> map = new LinkedHashMap<>();
        SystemInfo systemInfo = new SystemInfo();

        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();

        map.put("计算机基本信息", injectComputerSystem(hardware.getComputerSystem()));
        map.put("处理器基本信息", injectProcessor(hardware.getProcessor()));
        map.put("内存基本信息", injectMemory(hardware.getMemory()));
        map.put("CPU详细信息", injectCpu(hardware.getProcessor()));
        map.put("传感器信息", injectSensors(hardware.getSensors()));
        map.put("电源信息", injectPowerSources(hardware.getPowerSources()));
        map.put("磁盘信息", injectDisks(hardware.getDiskStores()));
        map.put("文件系统信息", injectFileSystem(operatingSystem.getFileSystem()));
        map.put("网络接口信息", injectNetworkInterfaces(hardware.getNetworkIFs()));
        map.put("网路参数", injectNetworkParameters(operatingSystem.getNetworkParams()));
        map.put("显示设备", injectDisplays(hardware.getDisplays()));
        map.put("USB设备-true", injectUsbDevices(hardware.getUsbDevices(true)));
        map.put("USB设备-false", injectUsbDevices(hardware.getUsbDevices(false)));

        // 计算比较耗时，建议单独写一个 endpoint
        MAP.put("线程详情", injectProcesses(operatingSystem, hardware.getMemory(), 10, OperatingSystem.ProcessSort.MEMORY));

        return map;
    }

    /**
     * 计算机基本信息
     *
     * @param computerSystem
     */
    public static JSONObject injectComputerSystem(final ComputerSystem computerSystem) {
        Gson gson = new Gson();
        JSONObject jsonObject = gson.fromJson(gson.toJsonTree(computerSystem), JSONObject.class);
        return jsonObject;
    }
    
    // ...
}
```

* 访问自定义端点

我们可以访问 `http://localhost:10011/actuator/myEndPoint` 来读取到计算机的信息：

```json
{
  "计算机基本信息": {
    "serialNumber": "8T5RLR2",
    "baseboard": {
      "manufacturer": "Dell Inc.",
      "model": "unknown",
      "version": "A01",
      "serialNumber": "/8T5RLR2/CNWS20087K00GT/"
    },
    "model": "Vostro 3670",
    "firmware": {
      "manufacturer": "Dell Inc.",
      "name": "1.3.4",
      "description": "1.3.4",
      "version": "DELL   - 1072009",
      "releaseDate": {
        "year": 2018.0,
        "month": 5.0,
        "day": 24.0
      }
    },
    "manufacturer": "Dell Inc."
  },
  "处理器基本信息": {
    "Identifier": "Intel64 Family 6 Model 158 Stepping 10",
    "物理CPU": 6,
    "处理器ID": "BFEBFBFF000906EA",
    "逻辑CPU": 12
  },
  "内存基本信息": {
    "内存概况": "总内存: 7.8 GiB   已使用内存: 880.3 MiB",
    "交换内存": "总Swap Mem: 11.5 GiB  已使用Swap Mem: 1.2 GiB"
  },
  "CPU详细信息": {
    "CPU详情": "User: 10.5% Nice: 0.0% System: 5.4% Idle: 84.0% IOwait: 0.0% IRQ: 0.0% SoftIRQ: 0.0% Steal: 0.0%",
    "Uptime": "0 days, 06:31:24",
    "Per core CPU": "CPU load per processor 32.9% 9.3% 18.4% 6.6% 11.9% 1.3% 14.5% 17.1% 26.3% 23.7% 15.8% 18.2%",
    "CPU load averages": " N/A N/A N/A",
    "CPU, IOWait, and IRQ ticks @ 0 sec": "[10263359, 0, 9112333, 228479328, 0, 46494, 44516, 0]",
    "CPU, IOWait, and IRQ ticks @ 1 sec": "[10264656, 0, 9113000, 228489671, 0, 46498, 44518, 0]",
    "CPU负载-1": "CPU load: 16.4% (counting ticks)",
    "CPU负载-2": "CPU load: 19.3% (OS MXBean)"
  },
  "传感器信息": {
    "CPU Temperature: %.1f°C": 28.0,
    "CPU Voltage: %.1fV": 0.0,
    "Fan Speeds": "[0]"
  },
  "电源信息": {
    "PowerSource": "Power: Charging Unknown @ 0.0%"
  },
  "磁盘信息": {
    "Disks": {
      "\\\\.\\PHYSICALDRIVE0": "(model: WDC WD10EZEX-75WN4A0 (标准磁盘驱动器) - S/N:      WD-WCC6Y4FLS7YZ) size: 1.0 TB, reads: 345421 (10.3 GiB), writes: 65730 (3.5 GiB), xfer: 3008836 ms",
      "\\\\.\\PHYSICALDRIVE1": "(model: NVMe KBG30ZMS128G NVM (标准磁盘驱动器) - S/N: 0008_0D04_0012_3948.) size: 128.0 GB, reads: 902766 (30.0 GiB), writes: 545623 (27.7 GiB), xfer: 4314783 ms",
      "\\\\.\\PHYSICALDRIVE1:P:0": {
        "GPT: System": "(model: NVMe KBG30ZMS128G NVM (标准磁盘驱动器) - S/N: 0008_0D04_0012_3948.) size: 128.0 GB, reads: 902766 (30.0 GiB), writes: 545623 (27.7 GiB), xfer: 4314783 ms",
        "GPT: Basic Data": "(model: NVMe KBG30ZMS128G NVM (标准磁盘驱动器) - S/N: 0008_0D04_0012_3948.) size: 128.0 GB, reads: 902766 (30.0 GiB), writes: 545623 (27.7 GiB), xfer: 4314783 ms"
      },
      "\\\\.\\PHYSICALDRIVE0:P:1": {
        "GPT: System": "(model: WDC WD10EZEX-75WN4A0 (标准磁盘驱动器) - S/N:      WD-WCC6Y4FLS7YZ) size: 1.0 TB, reads: 345421 (10.3 GiB), writes: 65730 (3.5 GiB), xfer: 3008836 ms",
        "GPT: Basic Data": "(model: WDC WD10EZEX-75WN4A0 (标准磁盘驱动器) - S/N:      WD-WCC6Y4FLS7YZ) size: 1.0 TB, reads: 345421 (10.3 GiB), writes: 65730 (3.5 GiB), xfer: 3008836 ms"
      }
    }
  },
  "文件系统信息": {
    "0-fileSystem": " 本地固定磁盘 (D:) (Fixed drive) [NTFS] 283.7 GiB of 300.0 GiB free (94.6%) is \\\\?\\Volume{4f876b68-5369-4aca-83a9-7be8ee6dcb5c}\\  and is mounted at D:\\",
    "3-fileSystem": " 本地固定磁盘 (C:) (Fixed drive) [NTFS] 39.9 GiB of 118.8 GiB free (33.6%) is \\\\?\\Volume{6d3a51a1-c07f-468e-afd9-e25990a090cd}\\  and is mounted at C:\\",
    "File Descriptors": "0 / 0",
    "1-fileSystem": " 本地固定磁盘 (E:) (Fixed drive) [NTFS] 246.6 GiB of 316.0 GiB free (78.0%) is \\\\?\\Volume{21d06f3e-3fb4-4141-b7e4-9be91c641c0c}\\  and is mounted at E:\\",
    "2-fileSystem": " 本地固定磁盘 (F:) (Fixed drive) [NTFS] 314.9 GiB of 315.1 GiB free (99.9%) is \\\\?\\Volume{cef65185-a2db-4c91-83a7-c1b29d193a2c}\\  and is mounted at F:\\"
  },
  "网络接口信息": {
    "Name: %s (%s)": "eth8 / Hyper-V Virtual Ethernet Adapter",
    "MTU: %s, Speed: %s": "1500 / 268.4 Mbps",
    "IPv4: %s": "[192.168.231.129]",
    "MAC Address: %s": "32:15:f1:4f:09:fd",
    "Traffic": "Traffic: received 0 packets/0 bytes (0 err); transmitted 0 packets/6.3 MiB (0 err)",
    "IPv6: %s": "[fe80:0:0:0:4073:369:f6ad:216c]"
  },
  "网路参数": {
    "Domain name: %s": "NB-20191203WESU.hikvision.com",
    "IPv6 Gateway: %s": "",
    "Host name: %s": "NB-20191203WESU",
    "IPv4 Gateway: %s": "10.197.239.254",
    "DNS servers: %s": "[10.1.7.77, 10.1.7.88]"
  },
  "显示设备": {
    "0-Display": "  Manuf. ID=NEC, Product ID=2b45, Analog, Serial=01010101, ManufDate=9/2014, EDID v1.3\r\n  60 x 34 cm (23.6 x 13.4 in)\n  Preferred Timing: Clock 148MHz, Active Pixels 3840x1920 \n  Range Limits: Field Rate 56-75 Hz vertical, 31-80 Hz horizontal, Max clock: 170 MHz\n  Monitor Name: VE2708XI\n  Serial Number: 49011035EC",
    "1-Display": "  Manuf. ID=DEL, Product ID=d0a2, Analog, Serial=0MKL, ManufDate=1/2019, EDID v1.4\r\n  53 x 30 cm (20.9 x 11.8 in)\n  Preferred Timing: Clock 148MHz, Active Pixels 3840x1920 \n  Serial Number: XN09C91J0MKL\n  Monitor Name: DELL SE2416HM\n  Range Limits: Field Rate 56-76 Hz vertical, 30-83 Hz horizontal, Max clock: 170 MHz"
  },
  "USB设备-true": {
    "0-UsbDevice": "Intel(R) USB 3.1 可扩展主机控制器 - 1.10 (Microsoft) (通用 USB xHCI 主机控制器)\n |-- USB 根集线器(USB 3.0) ((标准 USB 集线器))\n     |-- Qualcomm QCA9565 Bluetooth 4.0 (Qualcomm)\n     |-- Realtek USB 2.0 Card Reader (Realtek Semiconductor Corp.)\n     |-- USB Composite Device ((标准 USB 主控制器))\n         |-- USB 输入设备 ((标准系统设备))\n             |-- HID-compliant mouse (Microsoft)\n         |-- USB 输入设备 ((标准系统设备))\n             |-- HID Keyboard Device ((标准键盘))\n             |-- 符合 HID 标准的供应商定义设备 ((标准系统设备))\n             |-- 符合 HID 标准的供应商定义设备 ((标准系统设备))\n             |-- 符合 HID 标准的用户控制设备 (Microsoft)\n             |-- 符合 HID 标准的系统控制器 ((标准系统设备))\n     |-- USB Composite Device ((标准 USB 主控制器))\n         |-- USB 输入设备 ((标准系统设备))\n             |-- HID Keyboard Device ((标准键盘))\n         |-- USB 输入设备 ((标准系统设备))\n             |-- HID-compliant mouse (Microsoft)\n             |-- 符合 HID 标准的用户控制设备 (Microsoft)\n             |-- 符合 HID 标准的系统控制器 ((标准系统设备))\n         |-- USB 输入设备 ((标准系统设备))\n             |-- HID Keyboard Device ((标准键盘))\n         |-- USB 输入设备 ((标准系统设备))\n             |-- 符合 HID 标准的供应商定义设备 ((标准系统设备))"
  },
  "USB设备-false": {
    "0-UsbDevice": "USB 根集线器(USB 3.0) ((标准 USB 集线器))",
    "14-UsbDevice": "HID Keyboard Device ((标准键盘))",
    "9-UsbDevice": "符合 HID 标准的供应商定义设备 ((标准系统设备))",
    "13-UsbDevice": "USB 输入设备 ((标准系统设备))",
    "15-UsbDevice": "USB 输入设备 ((标准系统设备))",
    "20-UsbDevice": "HID Keyboard Device ((标准键盘))",
    "8-UsbDevice": "符合 HID 标准的供应商定义设备 ((标准系统设备))",
    "12-UsbDevice": "USB Composite Device ((标准 USB 主控制器))",
    "3-UsbDevice": "USB Composite Device ((标准 USB 主控制器))",
    "16-UsbDevice": "HID-compliant mouse (Microsoft)",
    "17-UsbDevice": "符合 HID 标准的用户控制设备 (Microsoft)",
    "4-UsbDevice": "USB 输入设备 ((标准系统设备))",
    "11-UsbDevice": "符合 HID 标准的系统控制器 ((标准系统设备))",
    "10-UsbDevice": "符合 HID 标准的用户控制设备 (Microsoft)",
    "22-UsbDevice": "符合 HID 标准的供应商定义设备 ((标准系统设备))",
    "5-UsbDevice": "HID-compliant mouse (Microsoft)",
    "18-UsbDevice": "符合 HID 标准的系统控制器 ((标准系统设备))",
    "21-UsbDevice": "USB 输入设备 ((标准系统设备))",
    "2-UsbDevice": "Realtek USB 2.0 Card Reader (Realtek Semiconductor Corp.)",
    "6-UsbDevice": "USB 输入设备 ((标准系统设备))",
    "7-UsbDevice": "HID Keyboard Device ((标准键盘))",
    "19-UsbDevice": "USB 输入设备 ((标准系统设备))",
    "1-UsbDevice": "Qualcomm QCA9565 Bluetooth 4.0 (Qualcomm)"
  }
}
```




