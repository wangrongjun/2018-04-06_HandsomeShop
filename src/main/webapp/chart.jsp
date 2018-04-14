<%@ page import="com.handsome.shop.dao.CustomerDao" %>
<%@ page import="com.handsome.shop.dao.impl.CustomerDaoImpl" %>
<%@ page import="org.jfree.chart.ChartFactory" %>
<%@ page import="org.jfree.chart.JFreeChart" %>
<%@ page import="org.jfree.chart.StandardChartTheme" %>
<%@ page import="org.jfree.chart.axis.CategoryAxis" %>
<%@ page import="org.jfree.chart.axis.NumberAxis" %>
<%@ page import="org.jfree.chart.axis.NumberTickUnit" %>
<%@ page import="org.jfree.chart.plot.PlotOrientation" %>
<%@ page import="org.jfree.chart.servlet.ServletUtilities" %>
<%@ page import="org.jfree.data.category.DefaultCategoryDataset" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/9/29
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    CustomerDao customerDao = new CustomerDaoImpl();
    List<Map<String, Object>> mapList = customerDao.countGender();

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (Map<String, Object> map : mapList) {
        int gender = (int) map.get("gender");
        long count = (long) map.get("count");
        dataset.addValue(count, "人数", gender == 1 ? "男" : "女");
    }

    // 表示支持中文
    StandardChartTheme theme = new StandardChartTheme("CN");
    // 图例字体
    theme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
    // 轴向字体
    theme.setRegularFont(new Font("宋体", Font.BOLD, 20));
    // 应用主题样式
    theme.setLargeFont(new Font("宋体", Font.BOLD, 20));
    ChartFactory.setChartTheme(theme);

    // 创建图像对象
    JFreeChart chart = ChartFactory.createBarChart(
            "用户性别统计",
            "x轴",
            "y轴",
            dataset,
            PlotOrientation.VERTICAL,// 柱形条方向
            false, false, false
    );

    // 设置标题字体
    chart.getTitle().setFont(new Font("微软雅黑", Font.BOLD, 18));

    NumberAxis rangeAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
    // 设置y轴
    rangeAxis.setTickUnit(new NumberTickUnit(3));
    rangeAxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));

    // 设置x轴
    CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
    domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));

    // 图表保存成文件
//    String filePath = ServletUtilities.saveChartAsPNG(chart, 300, 250, null, session);
//    String s = UUID.randomUUID().toString().replace("-", "");
//    String imagePath = "admin/temp/" + s + ".png";
//    String imageFilePath = request.getServletContext().getRealPath("/" + imagePath);
//    ChartUtilities.saveChartAsPNG(new File(imageFilePath), chart, 300, 250);
    String fileName = ServletUtilities.saveChartAsPNG(chart, 420, 300, null, session);
    String graphURL = request.getContextPath() + "/Admin/DisplayChart?filename=" + fileName;

%>

<img src="<%=graphURL%>" width="420" height="300" border="0">

</body>
</html>
