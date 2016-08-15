#security web demo
web安全示例

##防范CSRF
###使用验证码
RegisterController注册需要验证码
LoginController失败3次出现验证码

###使用token
UserController的update方法
@Token create为true则创建token，remove为true则删除token
TokenInterceptor处理注解

##防范XSS
html转义
${var}可能存在xss
使用<c:out value="${var}" />可转义

##防SQL注入
orm预编译sql语句

##防文件上传漏洞
限制上传文件类型，随机数改写文件名

##认证与授权
###session用户信息注入
@UserInfo()注解实现session中的用户信息注入
required=true的时候，如果未登录则会抛出UserNotLogin异常，在DefaultExceptionHandler中处理跳转到登录页面。（默认必须登录）
UserInfoArgumentResolver处理参数注入

###密码加密
采用MD5(password+salt)加密，其中salt是随机数，存在表中。
也可以用MD5(MD5(passowrd)+salt)进行加密。

###单点登录

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