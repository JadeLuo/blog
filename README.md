# 个人博客项目
技术框架
spring data  jpa
apach shiro
spring boot 
spring mvc 
layui

支持mysql数据库


#Demo
http://149.28.89.195/

#部署
1.创建数据库blog字符集为utf8 

2.配置application.properties中的数据库用户名和密码

3.配置application.properties中的图片服务器路径，一般为你的Tomact的IP和端口，不配置的话在上传图片后将无法查看
    image.host=
    image.port=
    

3.打包mvn package 生成ROOT.WAR部署到tomcat即可直接访问

4.启动项目后会自动生成表结构




#注册

注册时发送验证码会在code表里有验证码。

