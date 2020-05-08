# Feign

## 准备工作

首先我们需要引入依赖并且启用 `FeignClient`，如下：

**引入依赖：**

```xml
    <!-- open feign -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
```

**启用 FeignClient**

```java
@EnableFeignClients(basePackages = "xyz.rexlin600")
@EnableEurekaClient
@SpringBootApplication
public class OpenFeignSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignSvcApplication.class, args);
    }

}
```

## 使用 Feign 直接调用远程服务（by URI）

通过 `Feign` 客户端直接调用远程服务非常简单，不需要像以前使用 `HttpUtil` 一样去封装一个请求工具类，如下：

1. 引入依赖

```java
@Component
@FeignClient(name = "remoteURI", url = "http://api.juheapi.com")
public interface RemoteUriFeign {

    /**
     * 查看某月某日的事件列表
     *
     * @param key   聚合数据 在个人中心->我的数据,接口名称上方查看
     * @param v     版本，当前：1.0
     * @param month 月份，如：10
     * @param day   日，如：1
     * @return
     */
    @GetMapping("/japi/toh")
    String historyToady(@RequestParam(value = "key") String key,
                        @RequestParam(value = "v") String v,
                        @RequestParam(value = "month") Integer month,
                        @RequestParam(value = "day") Integer day);

}
```

只需要进行这样简单的配置，声明式的调用远程接口即可实现以前我们通过 `HttpUtils` 的方式去调用接口！

使用时只需要引入这个 `Bean` 即可，如下：

```java
@Slf4j
@RestController
@RequestMapping("/open/feign")
public class FeignRest {

    @Resource
    private RemoteUriFeign remoteUriFeign;

    /**
     * 查看某月某日的事件列表
     *
     * @param key
     * @param v
     * @param month
     * @param day
     * @return
     */
    @GetMapping("/historyToady")
    public HistoryTodayResponse historyToady(@RequestParam(value = "key") String key,
                                   @RequestParam(value = "v") String v,
                                   @RequestParam(value = "month") Integer month,
                                   @RequestParam(value = "day") Integer day) {
        // 声明式调用即可
        String s = remoteUriFeign.historyToady(key, v, month, day);
        Gson gson = new Gson();
        HistoryTodayResponse historyTodayResponse = gson.fromJson(s, HistoryTodayResponse.class);
        return historyTodayResponse;
    }

}
```

## 打印 Feign 请求日志

在开发过程中，我们可能需要知道 `Feign` 调用远程服务的 `HTTP` 日志，可以通过下面的方式打开日志

1. 增加 `FeignConfiguration` 配置，如下：

```java
/**
 * Feign 客户端配置
 * @author hekunlin
 */
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        //这里记录所有，根据实际情况选择合适的日志level
        return Logger.Level.FULL;
    }
}
```

2. 增加 `yaml` 配置

```yml
# feign
logging:
  level:
    xyz.rexlin600: debug
```

3. 在 `FeignClient` 上增加 `configuration`

```java
// ...
@FeignClient(name = "remoteURI", url = "http://api.juheapi.com", configuration = FeignConfiguration.class)
// ...
```

完成之后，调用 `Feign` 接口即可看到日志输出：

```log
...
2020-05-07 14:42:50.821 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] ---> GET http://api.juheapi.com/japi/toh?key=951e007714f394840e0f7f03e743eb76&v=1.0&month=5&day=7 HTTP/1.1
2020-05-07 14:42:50.822 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] ---> END HTTP (0-byte body)
2020-05-07 14:42:50.983 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] <--- HTTP/1.1 200 (161ms)
2020-05-07 14:42:50.984 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] accept-charset: big5, big5-hkscs, cesu-8, euc-jp, euc-kr, gb18030, gb2312, gbk, ibm-thai, ibm00858, ibm01140, ibm01141, ibm01142, ibm01143, ibm01144, ibm01145, ibm01146, ibm01147, ibm01148, ibm01149, ibm037, ibm1026, ibm1047, ibm273, ibm277, ibm278, ibm280, ibm284, ibm285, ibm290, ibm297, ibm420, ibm424, ibm437, ibm500, ibm775, ibm850, ibm852, ibm855, ibm857, ibm860, ibm861, ibm862, ibm863, ibm864, ibm865, ibm866, ibm868, ibm869, ibm870, ibm871, ibm918, iso-2022-cn, iso-2022-jp, iso-2022-jp-2, iso-2022-kr, iso-8859-1, iso-8859-13, iso-8859-15, iso-8859-2, iso-8859-3, iso-8859-4, iso-8859-5, iso-8859-6, iso-8859-7, iso-8859-8, iso-8859-9, jis_x0201, jis_x0212-1990, koi8-r, koi8-u, shift_jis, tis-620, us-ascii, utf-16, utf-16be, utf-16le, utf-32, utf-32be, utf-32le, utf-8, windows-1250, windows-1251, windows-1252, windows-1253, windows-1254, windows-1255, windows-1256, windows-1257, windows-1258, windows-31j, x-big5-hkscs-2001, x-big5-solaris, x-compound_text, x-euc-jp-linux, x-euc-tw, x-eucjp-open, x-ibm1006, x-ibm1025, x-ibm1046, x-ibm1097, x-ibm1098, x-ibm1112, x-ibm1122, x-ibm1123, x-ibm1124, x-ibm1166, x-ibm1364, x-ibm1381, x-ibm1383, x-ibm300, x-ibm33722, x-ibm737, x-ibm833, x-ibm834, x-ibm856, x-ibm874, x-ibm875, x-ibm921, x-ibm922, x-ibm930, x-ibm933, x-ibm935, x-ibm937, x-ibm939, x-ibm942, x-ibm942c, x-ibm943, x-ibm943c, x-ibm948, x-ibm949, x-ibm949c, x-ibm950, x-ibm964, x-ibm970, x-iscii91, x-iso-2022-cn-cns, x-iso-2022-cn-gb, x-iso-8859-11, x-jis0208, x-jisautodetect, x-johab, x-macarabic, x-maccentraleurope, x-maccroatian, x-maccyrillic, x-macdingbat, x-macgreek, x-machebrew, x-maciceland, x-macroman, x-macromania, x-macsymbol, x-macthai, x-macturkish, x-macukraine, x-ms932_0213, x-ms950-hkscs, x-ms950-hkscs-xp, x-mswin-936, x-pck, x-sjis_0213, x-utf-16le-bom, x-utf-32be-bom, x-utf-32le-bom, x-windows-50220, x-windows-50221, x-windows-874, x-windows-949, x-windows-950, x-windows-iso2022jp
2020-05-07 14:42:50.984 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] connection: keep-alive
2020-05-07 14:42:50.984 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] content-length: 10289
2020-05-07 14:42:50.984 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] content-type: text/html;charset=UTF-8
2020-05-07 14:42:50.984 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] date: Thu, 07 May 2020 06:42:50 GMT
2020-05-07 14:42:50.984 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] set-cookie: aliyungf_tc=AQAAAHwzEVLP0w0AWJWYr+CzB/lpnBCi; Path=/; HttpOnly
2020-05-07 14:42:50.984 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] vary: Accept-Encoding
2020-05-07 14:42:50.984 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] vary: Accept-Encoding
2020-05-07 14:42:50.984 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] 
2020-05-07 14:42:51.002 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] {"result":[{"_id":"17110507","title":"英国哲学家休谟诞生","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/6/27221239206.jpg","year":1711,"month":5,"day":7,"des":"在309年前的今天，1711年5月7日 (农历三月二十)，英国哲学家休谟诞生。","lunar":"辛卯年三月二十"},{"_id":"18330507","title":"德国古典主义作曲家约翰内斯·勃拉姆斯诞生","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/6/56221531213.gif","year":1833,"month":5,"day":7,"des":"在187年前的今天，1833年5月7日 (农历三月十八)，德国古典主义作曲家约翰内斯·勃拉姆斯诞生。","lunar":"癸巳年三月十八"},{"_id":"18400507","title":"俄国作曲家柴可夫斯基诞辰","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200405/7/D1234414387.jpg","year":1840,"month":5,"day":7,"des":"在180年前的今天，1840年5月7日 (农历四月初六)，俄国作曲家柴可夫斯基诞辰。","lunar":"庚子年四月初六"},{"_id":"18610507","title":"印度诗人泰戈尔诞辰","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/6F161826424.jpg","year":1861,"month":5,"day":7,"des":"在159年前的今天，1861年5月7日 (农历三月廿八)，印度诗人泰戈尔诞辰。","lunar":"辛酉年三月廿八"},{"_id":"19010507","title":"美国影星贾莱·古柏出生","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/3C161736347.jpg","year":1901,"month":5,"day":7,"des":"在119年前的今天，1901年5月7日 (农历三月十九)，美国影星贾莱·古柏出生。","lunar":"辛丑年三月十九"},{"_id":"19090507","title":"美国著名的发明家兰德·埃德温·赫伯特的诞生","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/6/4622183341.jpg","year":1909,"month":5,"day":7,"des":"在111年前的今天，1909年5月7日 (农历三月十八)，美国著名的发明家兰德·埃德温·赫伯特的诞生。","lunar":"己酉年三月十八"},{"_id":"19120507","title":"小提琴家、作曲家、音乐教育家马思聪诞生","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/6/1D22234986.jpg","year":1912,"month":5,"day":7,"des":"在108年前的今天，1912年5月7日 (农历三月廿一)，小提琴家、作曲家、音乐教育家马思聪诞生。","lunar":"壬子年三月廿一"},{"_id":"19160507","title":"护国军政府设立军务院","pic":"","year":1916,"month":5,"day":7,"des":"在104年前的今天，1916年5月7日 (农历四月初六)，护国军政府设立军务院。","lunar":"丙辰年四月初六"},{"_id":"19350507","title":"日本向佳木斯武装移民","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/6416101182.jpg","year":1935,"month":5,"day":7,"des":"在85年前的今天，1935年5月7日 (农历四月初五)，日本向佳木斯武装移民。","lunar":"乙亥年四月初五"},{"_id":"19370507","title":"毛泽东、刘少奇作报告为实现抗日民族统一战线而努力","pic":"","year":1937,"month":5,"day":7,"des":"在83年前的今天，1937年5月7日 (农历三月廿七)，毛泽东、刘少奇作报告为实现抗日民族统一战线而努力。","lunar":"丁丑年三月廿七"},{"_id":"19450507","title":"法西斯德国无条件投降，二战结束","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200906/3/BB222628629.jpg","year":1945,"month":5,"day":7,"des":"在75年前的今天，1945年5月7日 (农历三月廿六)，法西斯德国无条件投降，二战结束。","lunar":"乙酉年三月廿六"},{"_id":"19460507","title":"英国和法国从叙利亚撤军","pic":"","year":1946,"month":5,"day":7,"des":"在74年前的今天，1946年5月7日 (农历四月初七)，英国和法国从叙利亚撤军。","lunar":"丙戌年四月初七"},{"_id":"19480507","title":"欧洲统一运动首届大会开幕","pic":"","year":1948,"month":5,"day":7,"des":"在72年前的今天，1948年5月7日 (农历三月廿九)，欧洲统一运动首届大会开幕。","lunar":"戊子年三月廿九"},{"_id":"19490507","title":"“永不消逝的电波”的主人李白牺牲","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/3/E9233311223.jpg","year":1949,"month":5,"day":7,"des":"1949年5月7日 (农历四月初十)，“永不消逝的电波”的主人李白牺牲。","lunar":"己丑年四月初十"},{"_id":"19520507","title":"中国抗议美国残杀战俘","pic":"","year":1952,"month":5,"day":7,"des":"1952年5月7日 (农历四月十四)，中国抗议美国残杀战俘。","lunar":"壬辰年四月十四"},{"_id":"19540507","title":"奠边府战役胜利","pic":"","year":1954,"month":5,"day":7,"des":"1954年5月7日 (农历四月初五)，奠边府战役胜利。","lunar":"甲午年四月初五"},{"_id":"19600507","title":"中央大刀阔斧调整国民经济","pic":"","year":1960,"month":5,"day":7,"des":"1960年5月7日 (农历四月十二)，中央大刀阔斧调整国民经济。","lunar":"庚子年四月十二"},{"_id":"19610507","title":"毛泽东批转周恩来关于农村政策问题的调查报告","pic":"","year":1961,"month":5,"day":7,"des":"1961年5月7日 (农历三月廿三)，毛泽东批转周恩来关于农村政策问题的调查报告。","lunar":"辛丑年三月廿三"},{"_id":"19660507","title":"毛泽东给林彪写了《五七指示》","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/53155456816.jpg","year":1966,"month":5,"day":7,"des":"1966年5月7日 (农历闰三月十七)，毛泽东给林彪写了《五七指示》。","lunar":"丙午年闰三月十七"},{"_id":"19670507","title":"现代散文家周作人病逝","pic":"","year":1967,"month":5,"day":7,"des":"1967年5月7日 (农历三月廿八)，现代散文家周作人病逝。","lunar":"丁未年三月廿八"},{"_id":"19670507m1","title":"我军先后击落26架美军飞机","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/5C155429874.jpg","year":1967,"month":5,"day":7,"des":"1967年5月7日 (农历三月廿八)，我军先后击落26架美军飞机。","lunar":"丁未年三月廿八"},{"_id":"19680507","title":"中国社会学家吴景超逝世","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/6/6D222649881.jpg","year":1968,"month":5,"day":7,"des":"1968年5月7日 (农历四月十一)，中国社会学家吴景超逝世。","lunar":"戊申年四月十一"},{"_id":"19730507","title":"《华盛顿邮报》因披露水门事件而钦誉全国","pic":"","year":1973,"month":5,"day":7,"des":"1973年5月7日 (农历四月初五)，《华盛顿邮报》因披露水门事件而钦誉全国。","lunar":"癸丑年四月初五"},{"_id":"19750507","title":"美国总统福特宣布停止支持越南战争","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/30155211885.jpg","year":1975,"month":5,"day":7,"des":"1975年5月7日 (农历三月廿六)，美国总统福特宣布停止支持越南战争。","lunar":"乙卯年三月廿六"},{"_id":"19780507","title":"苏联英雄卓娅和舒拉的母亲逝世","pic":"","year":1978,"month":5,"day":7,"des":"1978年5月7日 (农历四月初一)，苏联英雄卓娅和舒拉的母亲逝世。","lunar":"戊午年四月初一"},{"_id":"19810507","title":"国民党高级将领杜聿明逝世","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201103/17/3E122935716.jpg","year":1981,"month":5,"day":7,"des":"1981年5月7日 (农历四月初四)，国民党高级将领杜聿明逝世。","lunar":"辛酉年四月初四"},{"_id":"19840507","title":"国务院发布《居民身份证试行条例》","pic":"","year":1984,"month":5,"day":7,"des":"1984年5月7日 (农历四月初七)，国务院发布《居民身份证试行条例》。","lunar":"甲子年四月初七"},{"_id":"19870507","title":"中信实业银行在北京成立并开始营业","pic":"","year":1987,"month":5,"day":7,"des":"1987年5月7日 (农历四月初十)，中信实业银行在北京成立并开始营业。","lunar":"丁卯年四月初十"},{"_id":"19900507","title":"苏公布卫国战争中军民损失数字","pic":"","year":1990,"month":5,"day":7,"des":"1990年5月7日 (农历四月十三)，苏公布卫国战争中军民损失数字。","lunar":"庚午年四月十三"},{"_id":"19900507m1","title":"中苏美和平登山队登上珠穆朗玛峰","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/DA15478530.jpg","year":1990,"month":5,"day":7,"des":"1990年5月7日 (农历四月十三)，中苏美和平登山队登上珠穆朗玛峰。","lunar":"庚午年四月十三"},{"_id":"19950507","title":"希拉克再次当选法国总统","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/67154415553.jpg","year":1995,"month":5,"day":7,"des":"1995年5月7日 (农历四月初八)，希拉克再次当选法国总统。","lunar":"乙亥年四月初八"},{"_id":"19970507","title":"光明工程开始实施","pic":"","year":1997,"month":5,"day":7,"des":"1997年5月7日 (农历四月初一)，光明工程开始实施。","lunar":"丁丑年四月初一"},{"_id":"20000507","title":"普京就任俄总统","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200405/7/9223464443.jpg","year":2000,"month":5,"day":7,"des":"2000年5月7日 (农历四月初四)，普京就任俄总统。","lunar":"庚辰年四月初四"},{"_id":"20020507","title":"北方航空公司一客机在大连湾海域坠毁 112人遇难","pic":"","year":2002,"month":5,"day":7,"des":"2002年5月7日 (农历三月廿五)，北方航空公司一客机在大连湾海域坠毁 112人遇难。","lunar":"壬午年三月廿五"},{"_id":"20080507","title":"中日签署关于全面推进战略互惠关系的联合声明","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/6/1A221057331.jpg","year":2008,"month":5,"day":7,"des":"2008年5月7日 (农历四月初三)，中日签署关于全面推进战略互惠关系的联合声明。","lunar":"戊子年四月初三"},{"_id":"9730507","title":"神圣罗马帝国奥托大帝逝世","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201108/4/1E05728864.jpg","year":973,"month":5,"day":7,"des":"在1047年前的今天，0973年5月7日 (农历四月初二)，神圣罗马帝国奥托大帝逝世。","lunar":"癸酉年四月初二"}],"reason":"请求成功！","error_code":0}
2020-05-07 14:42:51.002 DEBUG 18584 --- [io-10033-exec-1] x.r.openfeign.svc.feign.RemoteUriFeign   : [RemoteUriFeign#historyToady] <--- END HTTP (10289-byte body)
...
```

## 使用 Apache HttpClient 连接池替换内嵌 HttpClient

1. 添加依赖（注意版本兼容问题）

```xml
    <!-- http client -->
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.12</version>
    </dependency>
    <!-- 注意 feign 版本兼容性问题，这里需要使用 10.1.0 -->
    <dependency>
        <groupId>io.github.openfeign</groupId>
        <artifactId>feign-httpclient</artifactId>
        <version>10.1.0</version>
    </dependency>
```

2. 增加配置

```java
/**
 * Feign HttpClient 配置类
 *
 * @author: hekunlin
 * @date: 2020/5/7
 */
@Import(FeignAutoConfiguration.class)
@Configuration
public class FeignHttpClientConfiguration {

    // 启用 apache HttpClient 连接池

}
```

3. 添加 `yml` 配置

```yml
# feign
feign:
  okhttp:
    enabled: false
  httpclient:
    enabled: true # 此版本默认启用
    max-connections: 200
    max-connections-per-route: 50
    time-to-live: 60
    connection-timeout: 2000
```

至此，即实现了启用 `Apache` 的 `httpclient`而非默认的内嵌 `httpclient`

## SpringBoot 单文件上传于多文件上传

这个比较简单，参考代码如下：

```java
    /**
     * SpringBoot 单个文件上传
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        long fileSize = file.getSize();
        InputStream inputStream = file.getInputStream();

        // 省略文件上传后续操作 ...

        return "SUCCESS";
    }

    /**
     * SpringBoot 批量文件上传
     *
     * @param files
     * @return
     */
    @SneakyThrows
    @PostMapping("/batch/upload")
    public String upload(@RequestParam(value = "files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            this.upload(file);
        }

        // 省略文件上传后续操作 ...

        return "SUCCESS";
    }
```

## 使用 Feign 实现文件上传

> 注意，本示例相当于同时演示了如何和微服务集成实现服务间的调用

如果使用原生的 `Fegin` 组件进行文件上传，会提示：

```log
feign.codec.EncodeException: class [Lorg.springframework.web.multipart.MultipartFile; is not a type supported by this encoder.
    at feign.codec.Encoder$Default.encode(Encoder.java:90) ~[feign-core-9.5.1.jar:na]
    at feign.form.FormEncoder.encode(FormEncoder.java:87) ~[feign-form-3.3.0.jar:3.3.0]
    at feign.form.spring.SpringFormEncoder.encode(SpringFormEncoder.java:64) ~[feign-form-spring-3.3.0.jar:3.3.0]
```

由于我们这里引入的是 `openfeign`，它已经帮我们集成了下面的依赖并且也增加了 `Feign` 实现文件上传的配置（具体参考 `feign-form-spring` 包源码）因此可以实现文件上传：

```xml
    <dependency>
      <groupId>io.github.openfeign.form</groupId>
      <artifactId>feign-form</artifactId>
      <version>3.5.0</version>
    </dependency>
    <dependency>
      <groupId>io.github.openfeign.form</groupId>
      <artifactId>feign-form-spring</artifactId>
      <version>3.5.0</version>
    </dependency>
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.3</version>
    </dependency>
```

**实现步骤：**

1. 编写文件上传接口（`spring-boot-eureka-discovery` 服务中）

```java
@RestController
@RequestMapping("/file")
public class FileUploadRest {

    /**
     * Feign 文件上传
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        long fileSize = file.getSize();
        InputStream inputStream = file.getInputStream();

        // 省略文件上传后续操作 ...

        return "SUCCESS";
    }

}
```

2. 编写 `Feign` 接口（`spring-boot-openfeign-svc` 服务）

```java
@FeignClient(name = "fileUpload", url = "http://localhost:10032/file", configuration = {FeignAutoConfiguration.class})
public interface FileUploadFeign {

    // 注意：必须要指定 consume、需要指定注解为 @RequestPart(value = "file")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestPart(value = "file") MultipartFile file);

}
```

3. 本地调用 `Feign` 实现文件上传

```java
    // 省略部分代码 ...    

    @Resource
    private FileUploadFeign fileUploadFeign;

    /**
     * Feign 文件上传
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("/feign/upload")
    public String feignUpload(@RequestParam(value = "file") MultipartFile file) {
        return fileUploadFeign.upload(file);
    }
```

## 参考文章

- [Spring Boot与Spring Security整合后post数据不了，403拒绝访问](https://blog.csdn.net/sinat_28454173/article/details/52251004)
- [Spring boot + Spring Cloud框架下通过Feign进行跨服务传递MultipartFile文件](https://blog.csdn.net/Dragon_MD/article/details/79745103)
- [关于FeignClient的使用大全——进阶篇](https://www.jianshu.com/p/50fd582b739f)
