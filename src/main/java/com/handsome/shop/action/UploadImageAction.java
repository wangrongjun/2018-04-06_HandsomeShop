package com.handsome.shop.action;

/**
 * by wangrongjun on 2017/6/22.
 */
public class UploadImageAction {
//    @Override
//    protected String execute() throws ServletException, IOException, ParamErrorException, CustomerNotExistsException {
//        Customer customer = checkCustomer();
//
//        String url = "admin/img/customer_" + customer.getCustomerId() + ".jpg";
//        String savePath = request.getServletContext().getRealPath(url);
//        System.out.println(savePath);
//        /*
//         * 状态：
//         * 0：上传成功
//         * 1：大小超过1M
//         * 2：未知错误
//         */
//        int state;
//        try {
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            upload.setHeaderEncoding("UTF-8");
//            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
//            upload.setFileSizeMax(1024 * 1024);
//            List<FileItem> list = upload.parseRequest(request);
//            for (FileItem item : list) {
//                InputStream is = item.getInputStream();
//                FileOutputStream fos = new FileOutputStream(savePath);
//                byte[] buf = new byte[1024];
//                int len;
//                while ((len = is.read(buf)) != -1) {
//                    fos.write(buf, 0, len);
//                }
//                is.close();
//                fos.close();
//                item.delete();
//            }
//            state = 0;
//        } catch (FileUploadBase.FileSizeLimitExceededException e) {
//            state = 1;
//        } catch (Exception e) {
//            state = 2;
//            e.printStackTrace();
//        }
//
//        switch (state) {
//            case 0:
//                CustomerDao customerDao = DaoFactory.getCustomerDao();
//                customerDao.updateHeadUrl(customer.getCustomerId(), url);
//                customer.setHeadUrl(url);
//                request.setAttribute("msg", "上传成功");
//                break;
//            case 1:
//                request.setAttribute("msg", "上传失败，图片大小不能超过1MB");
//                break;
//            case 2:
//                request.setAttribute("msg", "上传失败，未知异常");
//                break;
//        }
//        return "customer_info.jsp";
//    }
}
