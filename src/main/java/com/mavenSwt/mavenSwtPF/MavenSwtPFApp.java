package com.mavenSwt.mavenSwtPF;

import com.mavenSwt.mavenSwtPF.form.MavenSwtPFAppForm;
import com.mavenSwt.mavenSwtPF.projHelper.ProjHelper;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
/**
 * Hello world!
 *
 */
@SpringBootApplication
//@MapperScan("com.perfect99.receiveUnicomDao")
@ComponentScan(basePackages = {
		//"com.perfect99.receiveUnicomWeb.aop",
		"com.mavenSwt.mavenSwtPF.*","pf.java.pfHelper.config"
		//,"com.perfect99.receiveUnicomMq.consumer"
		})
public class MavenSwtPFApp {
	//private static Shell _shell;
	public static void main(String[] args) {
		
//	        new Thread() {//线程操作
//	               public void run() {
//	       			try {
//	       				new MavenSwtPFAppForm();
//	       				//MavenSwtPFAppForm.main(null);
//						//new PFHttpServer().listen(12345);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	               }
//	        }.start();

		SpringApplication.run(MavenSwtPFApp.class, args);
		// ------------------------------//
	        
//		shell.pack();
//		shell.open();
//		while (!shell.isDisposed()) { // 如果主窗体没有关闭则一直循环
//			if (!display.readAndDispatch()) { // 如果display不忙
//				display.sleep(); // 休眠
//			}
//		}
//		_isAutoRefreshService=false;
//		display.dispose(); // 销毁display
//		for (int i = 0; i < _process.size(); i++) {// 内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
//			Process p = _process.get(i);
//			p.destroy();
//		}
//		Stop();
//		while(AnyRunning()) 
//		{
//	          System.out.println("running");
//		}
	        
		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */
	}			
    @Bean
	CommandLineRunner init() {
		return (args) -> {
				//new MavenSwtPFAppForm();
			//_shell=
			ProjHelper._mainForm=new MavenSwtPFAppForm();
		};
	}
}
