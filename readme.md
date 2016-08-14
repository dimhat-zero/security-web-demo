#security web demo
web安全示例

##防范CSRF
###使用验证码
LoginController

###使用token
UserController


##防范XSS
html转义
${var}  <c:out value="${var}" />

##防SQL注入
orm预编译sql语句

##防文件上传漏洞
限制上传文件类型，随机数改写文件名

##认证与授权
密码加密（salt），

单点登录

##访问控制
###垂直权限管理
RBAC

###水平权限管理
linux权限

OAuth第三方授权

##安全日志

##防DOS
缓存、限制请求频率（ip）

##业务逻辑安全


##安全运营
安全监控nagios
邮件、短信报警