# red5-slim

因为一些原因要去研究RTMP,找到了仅有的Java编写的RTMP服务器为red5. 但是呢,经过研究发现red5代码过分拉跨,很难进行再利用 比如

1. 过分依赖 SpringContext.getBean 方法,这个方法还使用服务名去获取服务, 导致服务依赖不清晰
2. 对mina-nio有强耦合,几乎是粘在一起,很难进行分离, 把mina换成netty??

然后放弃了抄袭red5核心代码的念头, 转而使用集成方案.   
red5 本来是集成tomcat的服务, 现在至少先去除tomcat,先作一个纯粹的rtmp服务.

## 之后

之后努力把rtmp服务集成到videojc项目中去, 实现推送rtmp流 接收http-flv流

## USAGE

用obs推流 push rtmp://localhost:1935/live xxxxxx  
用VLC播放 play rtmp://localhost:1935/live/xxxxxx

### mark

ProviderService:123 startBoardcast   
ProviderService:158 stopBoardcast   
org.red5.server.stream.StreamService:613.publish(java.lang.String, java.lang.String)

### 改造方案

org.red5.server.stream.ClientBroadcastStream 这个类里面有一个 autoRecording 的配置, 可以参考这个 RecordingListener 转写一个 FlvDispatchListener, 并且由于的内部流, 不需要额外的连接消耗,还可以实现查看当前所有的rtmp推流 将原来的文件内容写到Http流当中,然后就可以集成这个red5-slim到vediojc当中

### 鉴权方案

org.red5.server.stream.StreamService:613.publish 方法中已经获取了推流中获取的参数, 可以从中嵌入鉴权的业务代码

--TODO 改造成spring-boot-auto-starter
