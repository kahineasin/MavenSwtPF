//package pf.java.pfHelper;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///*
// * 计划用此类代替spring boot 来监听 http请求(如http://localhost:12345/hello?param=world)，
// * 后来发现没必要这样
// */
//public class PFHttpServer {
//
//    public static void main(String[] args) throws Exception{
//        //开启一个服务端Socket，监听12345端口
//        ServerSocket server = new ServerSocket(12345);
//
//        //有客户端连进来
//        Socket client = server.accept();
//
//        //获取到客户端输入流
//        InputStream in = client.getInputStream();
//
//        //准备一个缓冲数组
//        byte data[]=new byte[4096];
//
//        //这里有一个read（byte[] b）方法，将数据读取到字节数组中，同返回读取长度
//        int len=in.read(data);
//
//        //打印浏览器发来的请求头
//        System.out.println(new String(data));
//
//
//        //制作响应报文
//        StringBuffer response = new StringBuffer();
//
//        //响应状态
//        response.append("HTTP/1.1 200 OK\r\n");
//
//        //响应头
//        response.append("Content-type:text/html\r\n\r\n");
//
//        //要返回的内容(当前时间)
//        response.append("CurrentTime: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//
//
//        //获取客户端的输出流
//        OutputStream out=client.getOutputStream();
//
//        //将以上内容写入
//        out.write(response.toString().getBytes());
//
//        //关闭客户端和服务端的流和Socket
//        out.close();
//        in.close();
//        client.close();
//        server.close();
//    }
//    
//    private Boolean _running=false;
//    public  void listen(int port) throws Exception{
//    	_running=true;
//    	while(_running==true) {
//            //开启一个服务端Socket，监听12345端口
//            ServerSocket server = new ServerSocket(port);
//
//            //有客户端连进来
//            Socket client = server.accept();
//
//            //获取到客户端输入流
//            InputStream in = client.getInputStream();
//
//            //准备一个缓冲数组
//            byte data[]=new byte[4096];
//
//            //这里有一个read（byte[] b）方法，将数据读取到字节数组中，同返回读取长度
//            int len=in.read(data);
//
//            //打印浏览器发来的请求头
//            System.out.println(new String(data));
//
//
//            //制作响应报文
//            StringBuffer response = new StringBuffer();
//
//            //响应状态
//            response.append("HTTP/1.1 200 OK\r\n");
//
//            //响应头
//            response.append("Content-type:text/html\r\n\r\n");
//
//            //要返回的内容(当前时间)
//            response.append("CurrentTime: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//
//
//            //获取客户端的输出流
//            OutputStream out=client.getOutputStream();
//
//            //将以上内容写入
//            out.write(response.toString().getBytes());
//
//            //关闭客户端和服务端的流和Socket
//            out.close();
//            in.close();
//            client.close();
//            server.close();
//            
//            //Thread.sleep(1000);//如果没有这句，每次请求会接收两次,其实有一次是 favicon.ico的请求
//    	}
//    }
//}
