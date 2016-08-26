#security web demo
web安全示例

##防范CSRF
###使用验证码
RegisterController注册需要验证码
LoginController失败3次出现验证码

###使用token
UserController的update方法
@Token create为true则创建token，remove为true则删除token
TokenInterceptor处理token注解

##防范XSS
${var}可能存在xss
使用<c:out value="${var}" />可进行转义
XssDemoController示例

用户可提交的数据要额外注意，如评论等
可进行输入过滤特殊字符，也可进行输出html转义。

##防SQL注入
使用?作为占位符的预编译sql语句可避免

避免用户输入的值直接拼接成sql

##防文件上传漏洞
限制上传文件类型，随机数改写文件名

##认证与授权
###session用户信息注入
@UserInfo()注解实现session中的用户信息注入
required=true的时候，如果未登录则会抛出UserNotLogin异常
DefaultExceptionHandler处理异常跳转到登录页面。
UserInfoArgumentResolver处理参数注入

###密码加密
采用MD5(password+salt)加密，其中salt是随机数，存在表中。
也可以用MD5(MD5(passowrd)+salt)进行加密。

###单点登录
Jasig CAS用于认证（我是谁）

##访问控制
###垂直权限管理
RBAC
用户-角色-权限

有缓存意味着修改后不退出权限不更新
没缓存会有大量数据库查询

###水平权限管理
linux权限

OAuth第三方授权
用于授权（我能干什么）

##安全日志
debug 用于调试，进行问题诊断
info 用于业务过程追踪
error 记录不能处理的问题

###安全日志管理要求
对重要安全活动进行日志记录
登录认证日志：成功/失败的认证/登录、用户注销、超时退出等活动。
用户/权限管理：用户/权限的增删改以及密码的修改和重置等活动
业务敏感数据访问：比如支付、消费等活动。

##防DOS
使用redis、memcache等缓存，减少请求数据库
限制请求频率，对相同ip多次请求进行限制
尽量不要出现大请求页面（比如每页1万条数据）

##业务逻辑安全
业务流程安全，比如支付、退款等操作，比如多次输错密码锁定账号
内容安全，第三方机构发布的信息
客户信息安全，用户信息不会被泄露


##安全运营
安全监控nagios
邮件、短信报警