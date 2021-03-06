## 功能完善

* 分页和搜索：多条件搜索，需要有排序，缓存搜索条件，同时做好分页（使用Pager辅助分页）
* 把按分类来查询的方法合并到搜索方法中
* 商品的属性组合以及库存
* [完成]卖家的商品管理
* [完成]卖家的订单管理
* 用户的手机号保存到Address中，登录就通过用户名
* 新增角色管理员：管理商品分类
* 用户评价可以筛选好，中，差评
* 填写退款理由时可以上传图片
* 用户的忘记密码操作
* 创建订单后需要用户确认
* 使用支付宝接口模拟付款

## 技术改进（数据存储）

* [完成]改进项目：HibernateDao 的 id 类型参数化
* [完成]Orders的receiver和phone改为contact
* [完成]给每个实体类加上createdOn，modifiedOn，isObsolete
* [完成]改进项目：在数据库中保存图片，编写一个请求方法返回图片
* [完成]无限分类（闭包集实现树型结构）
* [完成]Address表改名为Contact
* [完成]密码使用md5加密保存
* 改进项目：使用 SpringData 框架
* 使用Redis进行主页和商品详情页的缓存。缓存策略：有效期1小时。
  同时监听会影响到主页和商品详情页的修改方法，即时更新缓存。
* Goods表新增缩略图字段

## 技术改进（接口服务）

* [完成]改进项目：改为使用 SpringMVC 的模式
* [完成]去掉所有 center 标签
* [完成]文件上传使用SpringMVC的框架
* 使用Hibernate Validator参数校验框架校验参数
* 请求的一切错误（包括找不到请求方法，请求方法参数出错，请求方法内部抛出异常）
  都返回纯文本的错误信息，而不要返回html页面。
* 把项目部署到docker镜像
* 使用Spring的权限认证功能，认证每个请求的合法性
* 使用支付宝接口模拟支付过程
* 预防XSS攻击
* 把所有jsp改为html，所有页面都通过ajax加载数据
* 在Rest api的基础上使用GraphQL，模拟系统升级

## 二期过程

* 销售统计
* 用户统计
* 移动端兼容
* 对比淘宝缺少的功能和性能
* 使用dubbo或者spring cloud转换为微服务架构
* 研究项目使用分布式来开发。研究负载均衡，搭建两台服务器。使用Redis的分布式。
