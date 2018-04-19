## 功能完善

* 分页和搜索：多条件搜索，需要有排序，缓存搜索条件，同时做好分页（使用Pager辅助分页）
* 把按分类来查询的方法合并到搜索方法中
* 商品的无限级分类
* 商品的属性组合以及库存
* 商家的商品管理，订单管理
* 用户的手机号保存到Address中，登录就通过用户名

## 技术改进

* [完成]改进项目：改为使用 SpringMVC 的模式
* 改进项目：HibernateDao 的 id 类型参数化
* 改进项目：使用 Hibernate 提供的 JdbcTemplate
* Address表改名为Contact
* Orders的receiver和phone改为contact
* 给每个实体类加上createdOn，modifiedOn，isObsolete
* 去掉所有 center 标签
* 文件上传使用SpringMVC的框架
* 使用Spring的权限认证功能，认证每个请求的合法性
* 密码使用md5加密保存
* 预防XSS攻击
* 使用支付宝接口模拟支付过程

## 二期过程

* 销售统计
* 用户统计
* 移动端兼容
* 对比淘宝缺少的功能和性能

