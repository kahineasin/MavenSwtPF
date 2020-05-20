package com.mavenSwt.mavenSwtPF.form;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
//import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolder2Listener;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
//import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenSwt.mavenSwtPF.CustomDelegate;
import com.mavenSwt.mavenSwtPF.ShowProcessDialog;
import com.mavenSwt.mavenSwtPF.listener.CmdOutListener;
import com.mavenSwt.mavenSwtPF.listener.CmdStartListener;
import com.mavenSwt.mavenSwtPF.model.PFEurekaInstance;
import com.mavenSwt.mavenSwtPF.model.PFEurekaInstance2;
import com.mavenSwt.mavenSwtPF.model.PFSpringList;
import com.mavenSwt.mavenSwtPF.model.PFSpringModel;
import com.mavenSwt.mavenSwtPF.model.UploadJarRestModel;
import com.mavenSwt.mavenSwtPF.projHelper.ExitException;
import com.mavenSwt.mavenSwtPF.projHelper.ProjHelper;
import com.mavenSwt.mavenSwtPF.projHelper.ShutdownManager;
import com.mavenSwt.mavenSwtPF.thread.SpringLoader;

import pf.java.pfHelper.PFDirectory;
//import pf.java.pfHelper.PFHttpServer;
import pf.java.pfHelper.PFPath;
import pf.java.pfHelper.PFRequestResult;
import pf.java.pfHelper.PFSwtHelper;
import pf.java.pfHelper.config.PFDataHelper;
import pf.java.pfHelper.swt.PFRequireValidator;

import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.ho.yaml.Yaml;
import java.util.List;

//import org.ho.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
/**
 * Hello world!
 *
 */
public class MavenSwtPFAppForm {

	private static CTabFolder _tabFolder;
//	private static ArrayList<Process> _process;
	private static ArrayList<SpringLoader> _springs;
	private static ArrayList<MavenSwtPFAppJarTab> _jarTabs;
	private static ArrayList<PFEurekaInstance> _eurekaInstances;
	// private static ArrayList<PFSpringModel> _springModels;

//	private static PFSpringList  _pfSpringList;
	private static CTabItem _openJarTab;
	private static Tree _tree;
	private static Boolean _isAutoRefreshService = false;
	private static Boolean _isAutoRefreshing = false;
	private static int _isAutoRefreshServiceTime = 2000;
	private static Button _autoRefreshServiceTreeBtn;
	private static Shell _shell;
	public static Display _display;
	private static Button _addBtn;
	private static Thread _tempThread;

//	public static void main(String[] args) {
	public MavenSwtPFAppForm() {
		// public static void CreateForm() {
////		
////	        new Thread() {//线程操作
////	               public void run() {
////	       			try {
////						new PFHttpServer().listen(12345);
////					} catch (Exception e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////	               }
////	        }.start();
//		

//		try {
//			//Yaml y = new Yaml(new CustomClassLoaderConstructor(playClassLoader));
////			CustomClassLoaderConstructor constr = new CustomClassLoaderConstructor(
////					clazz.getClassLoader());
////			Yaml yaml = new Yaml(new CustomClassLoaderConstructor(Play.classloader));
//			Yaml yaml = new Yaml();
////			//如果yml不打包
////			//String yPath="/src/main/resources/application.yml";
////			//String yPath2="/src/main/resources/testYaml.yaml";
////			String yPath2="/src/main/resources/springList.yaml";
////	        File dumpFile=new File(System.getProperty("user.dir") + yPath2);
////	        //_pfSpringList = (PFSpringList) Yaml.loadType(dumpFile, PFSpringList.class);
////	        _pfSpringList = (PFSpringList) Yaml.loadType(dumpFile);
//	        
////			//yml打包
//	        Resource resource = new ClassPathResource("springList.yml");
//	        //File dumpFile = resource.getFile();
//			BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
//	        //_pfSpringList = yaml.loadType(br, com.mavenSwt.mavenSwtPF.model.PFSpringList.class);
//			//注意：以上Yaml.loadType方法在Spring boot+swt环境下会无法转换，网上解析说用了不同的ClassLoader,暂时的解决办法是：
//	        Object pfSpringList = Yaml.load(br);
//	        _pfSpringList=(PFSpringList)PFDataHelper.map2Object((Map<String, Object>)pfSpringList,PFSpringList.class);
//
//	        ObjectMapper mapper = new ObjectMapper();
//	        _pfSpringList.setList(mapper.convertValue(_pfSpringList.getList(), new TypeReference<ArrayList<PFSpringModel>>() { }));
//	        //Person father = (Person) Yaml.loadType(dumpFile, Person.class);
//
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		// benjamin
		// _process = new ArrayList<Process>();
		_springs = new ArrayList<SpringLoader>();
		_jarTabs = new ArrayList<MavenSwtPFAppJarTab>();
		_eurekaInstances = new ArrayList<PFEurekaInstance>();

		Display display = new Display();// 创建一个display对象。
		final Shell shell = new Shell(display);// shell是程序的主窗体
		_display = display;
		_shell = shell;
		shell.setText("SpringCloud运行窗口,请勿随意关闭(本程序占用28601端口)");
		shell.setLayout(new FillLayout());// benjamin

		Menu mainMenu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(mainMenu);

		{
			// "文件"项
			MenuItem fileItem = new MenuItem(mainMenu, SWT.CASCADE);
			fileItem.setText("文件&F");
			// "文件"菜单
			Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
			fileItem.setMenu(fileMenu);
			{
				// "新建"项
				MenuItem newFileItem = new MenuItem(fileMenu, SWT.CASCADE);
				newFileItem.setText("Cloud");
				// "新建"菜单
				Menu newFileMenu = new Menu(shell, SWT.DROP_DOWN);
				newFileItem.setMenu(newFileMenu);
				{
					// "新建项目"项
					MenuItem newProjectItem = new MenuItem(newFileMenu, SWT.PUSH);
					newProjectItem.setText("运行\tCtrl+Shift+N");
					// 设置快捷键
					newProjectItem.setAccelerator(SWT.CTRL + SWT.SHIFT + 'N');
					// 添加事件监听
					newProjectItem.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {

							if (AnyRunning()) {
								Alert(shell, "有正在运行的程序，请先停止所有jar");

								e.doit = false;
								return;
							}

							PFSpringList pfSpringList = new PFSpringList(new ClassPathResource("springList.yml"));
							for (int i = 0; i < pfSpringList.getList().size(); i++) {
								PFSpringModel m = pfSpringList.getList().get(i);
								AddWatchSpringTab(m);
							}

						}
					});

				}

				MenuItem openFileItem = new MenuItem(fileMenu, SWT.CASCADE);
				openFileItem.setText("打开jar");

				openFileItem.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						_tabFolder.setSelection(_openJarTab);

					}
				});

				new MenuItem(fileMenu, SWT.SEPARATOR);
				MenuItem stopItem = new MenuItem(fileMenu, SWT.PUSH);
				stopItem.setText("停止所有jar");
				stopItem.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						// if(_isAutoRefreshService) {
						// try {
						// StopRefreshServiceTree();
						// Thread.sleep(2000);
						// } catch (InterruptedException e1) {
						// // TODO Auto-generated catch block
						// e1.printStackTrace();
						// }
						// }
						Stop();
					}
				});

				new MenuItem(fileMenu, SWT.SEPARATOR);
				MenuItem exitItem = new MenuItem(fileMenu, SWT.CASCADE);
				exitItem.setText("退出&E");
				exitItem.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {

						shell.close();
					}
				});

			}
			MenuItem helpItem = new MenuItem(mainMenu, SWT.CASCADE);
			helpItem.setText("帮助&H");
			helpItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					Alert(shell, "develop by Benjamin");
				}
			});
		}

		// 选项卡
		// CTabFolder tabFolder = new CTabFolder(shell, SWT.NONE | SWT.CLOSE);//
		// 声明一个选项卡容器
		CTabFolder tabFolder = new CTabFolder(shell, SWT.NONE);// 声明一个选项卡容器．这里不定义为可关闭，否则会使CTabItem中定义的可关闭无效
		_tabFolder = tabFolder;// --benjamin
		// tabFolder.setBounds(5,5,180,130); //设置选项卡的位置和大小
		tabFolder.setBounds(5, 5, 1000, 700); // 设置选项卡的位置和大小
		// tabFolder.setBounds(Display.getDefault().getPrimaryMonitor().getBounds());
		tabFolder.addCTabFolder2Listener(new CTabFolder2Adapter() {
			public void close(CTabFolderEvent event) {
				CTabItem closingItem = (CTabItem) event.item;
				String tabText = closingItem.getText().replaceAll("\\(停止\\)$", "");

				for (int i = 0; i < _jarTabs.size(); i++) {
					PFSpringModel sm = _jarTabs.get(i).getSpringModel();
					if (tabText.equals(sm.getSpringName())) {
						_springs.removeIf(a -> a.getSpringModel().equalsT(sm));
						_eurekaInstances.removeIf(a -> a.equalsT(sm));
						_jarTabs.remove(i);
						// Alert(shell, "remove spring "+sm.getSpringName());
						return;
					}
				}
			}
		});

		CreateOpenJarTab(shell);

		CreateEurekaManagerTab(shell);

		// benjamin

		/*
		 * CTabItem tabItem3 = new CTabItem(tabFolder, SWT.NONE); // 声明第2个选项页
		 * tabItem3.setText("选项3"); { // 创建第3个分组框，建立在tabFolder上 Composite composite1 =
		 * new Composite(tabFolder, SWT.NONE); tabItem3.setControl(composite1); //
		 * 让tabItem2控制group2 // group2.setText("兴趣爱好"); composite1.setSize(500, 460); //
		 * 设置容器为7 列 composite1.setLayout(new GridLayout(7, false)); // 在Shell
		 * 上定义List，并对List 进行布局 { List list = new List(composite1, SWT.BORDER); // 设置列表项
		 * list.setItems(new String[] { "常规", "音频", "被拒", "通知", "链接" }); // 使List 垂直充满
		 * GridData gridList = new GridData(GridData.FILL_VERTICAL);
		 * gridList.horizontalSpan = 3;// 水平强占3 列 gridList.widthHint = 100;// 使宽度为100
		 * 个像素 // 设置List 距离shell 容器左边框为5 个像素 gridList.horizontalIndent = 5;
		 * list.setLayoutData(gridList); } }
		 */

		// benjamin
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				if (AnyRunning()) {
					Alert(shell, "有正在运行的程序，不能关闭窗口");
					/*
					 * MessageBox dialog=new MessageBox(shell,SWT.NONE); dialog.setText("提示");
					 * dialog.setMessage("有正在运行的程序，不能关闭窗口"); dialog.open();
					 */
					e.doit = false;
					return;
				}
				MessageBox messagebox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				messagebox.setText("MessageBox 对话框");
				messagebox.setMessage("请确认是否退出?");
				int message = messagebox.open();

				e.doit = message == SWT.YES;
			}
		});
		// ------------------------------//
		shell.pack();
		shell.open();
		

		Path slPath=Paths.get(PFDataHelper.GetBaseDirectory(),  "ymlConfig\\springList.yml");
		String slPathStr=slPath.toString();
		if(PFDirectory.Exists(slPathStr)) {
//			PFSpringList pfSpringList = new PFSpringList( new ClassPathResource( "springList.yml"));
//			PFSpringList pfSpringList = new PFSpringList( new ClassPathResource( slPath.toString()));
			PFSpringList pfSpringList = new PFSpringList(slPathStr);
			for (int i = 0; i < pfSpringList.getList().size(); i++) {
				PFSpringModel m = pfSpringList.getList().get(i);
				AddWatchSpringTab(m);
			}			
		}
		
		// return shell;
		// -----如果不是spring boot子进程的话，可能需要这部分
		while (!shell.isDisposed()) { // 如果主窗体没有关闭则一直循环
			if (!display.readAndDispatch()) { // 如果display不忙
				display.sleep(); // 休眠
			}
		}
		_isAutoRefreshService = false;
		display.dispose(); // 销毁display
//		for (int i = 0; i < _process.size(); i++) {// 内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
//			Process p = _process.get(i);
//			p.destroy();
//		}
		Stop();
		while (AnyRunning()) {
			System.out.println("running");
		}

		// new ShutdownManager().initiateShutdown(402);
		ShutdownManager.initiateShutdown(402);
		// SpringApplication.exit(ctx);
		// throw new ExitException();
		// try { SpringApplication.exit(context); }catch(Exception e) {}

		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */
	}

//
//	public static void StopSpring(String serviceName, String instanceName) {
//		SpringLoader d = GetSpringLoader(serviceName, instanceName);
//		if (d != null) {
//			d.cutEureka();
//			try {
//				Thread.sleep(2000);// 这个秒数应该大于或等于gateway的fetch from eureka interval加上crm service的cache update
//									// interval秒数,这样才安全.现时的界点是2秒.实测重启4个实例会断两次,断50毫秒左右,原因未明(应该不是因为心跳续约引起的,否则应该不只50毫秒),猜测可能是正在传输时同时断开所致--benjamin
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			d.asyncClose();
//		}
//	}
	public static void StopSpring(String serviceName, String instanceName) {
		_display.syncExec(new Runnable() {
			public void run() {
				_addBtn.setEnabled(false);
			}
		});
	}
//
//	public static void RunSpring(String serviceName, String instanceName) {
//		_display.syncExec(new Runnable() {
//			public void run() {
//				SpringLoader d = GetSpringLoader(serviceName, instanceName);
//				if (d != null) {
//					d.asyncClose();
//					d.open();
//				}
//			}
//		});
//	}

//	//报错：org.eclipse.swt.SWTException: Invalid thread access
//	public static void RunSpring(String serviceName,String instanceName) {
//		_jarTabs.get(0).getRebootBtn().setEnabled(false);
//	}	
//	//报错：org.eclipse.swt.SWTException: Invalid thread access
//	public static void RunSpring(String serviceName,String instanceName) {
//		_addBtn.setEnabled(false);
//	}	
//	//成功
//	public static void RunSpring(String serviceName,String instanceName) {
//		   Display.getDefault().syncExec(new Runnable() {
//			    public void run() {
//					_addBtn.setEnabled(false);
//			    }
//			    }); 
//	}	
	// 成功
	public static void RunSpring(String serviceName, String instanceName) {
		_display.syncExec(new Runnable() {
			public void run() {
				_addBtn.setEnabled(true);
			}
		});
	}

	public static Boolean UpdateSpringJar(MultipartFile jarFile, String serviceName, String instanceName) {

		_display.syncExec(new Runnable() {
			public void run() {
				SpringLoader d = GetSpringLoader(serviceName, instanceName);
				if (d != null) {
					PFSpringModel sm = d.getSpringModel();
					// String filePath = basePath + "/" + file.getOriginalFilename();
					String filePath = sm.getJarPath();
					File desFile = new File(filePath);
					PFDataHelper.EnsureFilePath(filePath);
					try {
						jarFile.transferTo(desFile);
						// return true;
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
						// return false;
					}
				}
			}
		});
		return true;
	}

	public static Boolean UpdateAndRebootSpringJar(MultipartFile jarFile, String serviceName, String instanceName) {
		_display.syncExec(new Runnable() {
			public void run() {
				SpringLoader d = GetSpringLoader(serviceName, instanceName);
				if (d != null) {
					PFSpringModel sm = d.getSpringModel();

					d.cutEureka();
					try {
						Thread.sleep(2000);// 这个秒数应该大于或等于gateway的fetch from eureka interval加上crm service的cache update
											// interval秒数,这样才安全.现时的界点是2秒.实测重启4个实例会断两次,断50毫秒左右,原因未明(应该不是因为心跳续约引起的,否则应该不只50毫秒),猜测可能是正在传输时同时断开所致--benjamin
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.asyncClose();

					// String filePath = basePath + "/" + file.getOriginalFilename();
					String filePath = sm.getJarPath();
					File desFile = new File(filePath);
					PFDataHelper.EnsureFilePath(filePath);
					try {
						jarFile.transferTo(desFile);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
						// return false;
					}

					d.open();
					// return true;
				}
				// return false;
			}
		});
		return true;
	}

	public static Boolean UpdateAndRebootSpringJars(MultipartFile jarFile,
			java.util.List<PFEurekaInstance> eurekaInstances) {
		_display.syncExec(new Runnable() {
			public void run() {
				// 停止运行
				ArrayList<SpringLoader> loaders = new ArrayList<SpringLoader>();
				for (int i = 0; i < eurekaInstances.size(); i++) {
					PFEurekaInstance eurekaInstance = eurekaInstances.get(i);
					SpringLoader d = GetSpringLoader(eurekaInstance.getServiceName(), eurekaInstance.getInstanceName());
					if (d != null) {
						loaders.add(d);
						d.cutEureka();
						try {
							Thread.sleep(2000);// 这个秒数应该大于或等于gateway的fetch from eureka interval加上crm service的cache
												// update
												// interval秒数,这样才安全.现时的界点是2秒.实测重启4个实例会断两次,断50毫秒左右,原因未明(应该不是因为心跳续约引起的,否则应该不只50毫秒),猜测可能是正在传输时同时断开所致--benjamin
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						d.asyncClose();
					}
				}

				// 替换jar(当一个服务器运行同一个服务的两个实例时，每个实例的jar应该是一样的，所以要过滤重复)
				List<String> jarPaths = PFDataHelper.ListSelect(loaders, a -> a.getSpringModel().getJarPath());
				jarPaths = jarPaths.stream().distinct().collect(Collectors.toList());
				for (int i = 0; i < jarPaths.size(); i++) {

					String filePath = jarPaths.get(i);
					File desFile = new File(filePath);
					PFDataHelper.EnsureFilePath(filePath);
					try {
						jarFile.transferTo(desFile);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
						// return false;
					}
				}
				// 运行
				for (int i = 0; i < loaders.size(); i++) {
					loaders.get(i).open();
				}
			}
		});
		return true;
	}

	private static SpringLoader GetSpringLoader(String serviceName, String instanceName) {
		return PFDataHelper.ListFirstOrDefault(_springs, a -> {
			PFSpringModel sm = a.getSpringModel();
			return serviceName.equals(sm.getServiceName()) && instanceName.equals(sm.getInstanceName());
		});
	}

	private static PFEurekaInstance GetEurekaInstance(String serviceName, String instanceName) {
		return PFDataHelper.ListFirstOrDefault(_eurekaInstances, a -> {
			return serviceName.equals(a.getServiceName()) && instanceName.equals(a.getInstanceName());
		});
	}

	private static void CreateOpenJarTab(Shell shell) {
		CTabFolder tabFolder = _tabFolder;
		_openJarTab = new CTabItem(tabFolder, SWT.NONE);// 声明第1个选项页
		_openJarTab.setText("打开jar"); // 设置选项页的标题

		{
			// 创建第1个分组框，建立在tabFolder上
			Group group1 = new Group(tabFolder, SWT.NONE);
			group1.setText("录入信息"); // 设置分组框说明信息
			_openJarTab.setControl(group1); // 让tabItem1控制group1
			Label lb1 = new Label(group1, SWT.NONE); // 注意Label建立在group1上
			lb1.setText("服务名：");
			lb1.setBounds(10, 20, 70, 20);
			Text text1 = new Text(group1, SWT.BORDER);
			text1.setBounds(90, 20, 300, 20);
			Label lb2 = new Label(group1, SWT.NONE);
			lb2.setText("文件名：");
			lb2.setBounds(10, 50, 70, 20);
			Text text2 = new Text(group1, SWT.BORDER);
			text2.setBounds(90, 50, 300, 20);
			text2.setEditable(false);
			/*
			 * // benjamin Button button = new Button(group1, SWT.PUSH | SWT.BORDER);
			 * button.setBounds(170, 50, 70, 20); button.addSelectionListener(new
			 * SelectionAdapter() { public void widgetSelected(SelectionEvent e) { ////
			 * MessageDialog.openInformation(shell, null, "HelloWorld!!");
			 * 
			 * //// new ProgressBarExample(Display.getCurrent().getActiveShell()).open(); //
			 * ProgressBarExample d=new ProgressBarExample(shell); // d.open(); // try {
			 * Thread.sleep(1000); } catch (Throwable e1) { } // d.close();
			 * 
			 */

			Label lb3 = new Label(group1, SWT.NONE);
			lb3.setText("文件：");
			lb3.setBounds(10, 80, 70, 20);
			Text text3 = new Text(group1, SWT.BORDER);
			text3.setBounds(90, 80, 300, 20);
			Button fileBtn = new Button(group1, SWT.PUSH);
			fileBtn.setBounds(400, 80, 80, 20);
			fileBtn.setText("选择jar文件");

			Label lb4 = new Label(group1, SWT.NONE);
			lb4.setText("java命令：");
			lb4.setBounds(10, 110, 70, 20);
			Text text4 = new Text(group1, SWT.BORDER);
			text4.setBounds(90, 110, 300, 20);

			Label lb5 = new Label(group1, SWT.NONE);
			lb5.setText("Eureka：");
			lb5.setBounds(10, 140, 70, 20);

			Label lb6 = new Label(group1, SWT.NONE);
			lb6.setText("serviceName：");
			lb6.setBounds(10, 170, 100, 20);
			Text text6 = new Text(group1, SWT.BORDER);
			text6.setBounds(110, 170, 300, 20);

			Label lb7 = new Label(group1, SWT.NONE);
			lb7.setText("instanceName：");
			lb7.setBounds(10, 200, 100, 20);
			Text text7 = new Text(group1, SWT.BORDER);
			text7.setBounds(110, 200, 300, 20);

			Button addBtn = new Button(group1, SWT.PUSH);
			addBtn.setBounds(90, 230, 80, 20);
			addBtn.setText("运行");
			_addBtn = addBtn;

			fileBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					FileDialog OpenFileDialog = new FileDialog(shell, SWT.OPEN);
					OpenFileDialog.setText("打开型文件选择对话框");
					// OpenFileDialog.setFilterExtensions(new String[] { "*.*", "*.psd", "*.jpg",
					// "*.txt", "*.doc", "*.exe" });
					// OpenFileDialog.setFilterNames(new String[] { "所有类型(*.*)", "potoshopg
					// 格式(*.psd)", "文本格式(*.txt)" });
					OpenFileDialog.setFilterExtensions(new String[] { "*.jar", "*.exe" });
					OpenFileDialog.setFilterNames(new String[] { "jar 格式(*.jar)", "exe 格式(*.exe)" });
					OpenFileDialog.setFilterPath("C:\\");
					String fPath = OpenFileDialog.open();
					if (fPath == null) {
						MessageBox dialog = new MessageBox(shell, SWT.OK | SWT.ICON_INFORMATION);
						dialog.setText("Hello");
						dialog.setMessage("Hello,world!");
						dialog.open();
						Alert(shell, "请选择文件");
					} else {
						text3.setText(fPath);
						String fName = OpenFileDialog.getFileName();
						text1.setText(PFPath.GetFileNameWithoutExtension(fName));
						text2.setText(fName);
						// text4.setText("java -jar D://eclipse_release/springCloudPF/"+fName);
						text4.setText("java -jar " + fPath);
					}
				}
			});

			addBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					PFRequireValidator validate = new PFRequireValidator(new Text[] { text1, text2, text4 });
					if (!validate.IsValidate()) {
						Alert(shell, "服务名,文件名,命令均为必填");
						e.doit = false;
						return;
					}
					PFSpringModel m = new PFSpringModel();
					m.setSpringName(text1.getText());
					// m.setJarPath(text2.getText());
					m.setJarPath(text3.getText());// 用全路径吧--benjamin20200109
					m.setJavaCmd(text4.getText());
					m.setServiceName(text6.getText());
					m.setInstanceName(text7.getText());
					AddWatchSpringTab(m);
				}
			});
		}

	}

//	private static String fetchGroupKey(PFEurekaInstance detail){
//        return detail.getServiceName();
//    }
	private static void CreateEurekaManagerTab(Shell shell) {
		CTabFolder tabFolder = _tabFolder;
		CTabItem tabItem2 = new CTabItem(tabFolder, SWT.NONE); // 声明第2个选项页
		tabItem2.setText("eureka管理");
		{ // 创建第2个分组框，建立在tabFolder上
			Group group2 = new Group(tabFolder, SWT.NONE);
			tabItem2.setControl(group2); // 让tabItem2控制group2
			group2.setText("删除已连接的服务");

			Label lb1 = new Label(group2, SWT.NONE); // 注意Label建立在group1上
			lb1.setText("服务名：");
			lb1.setBounds(10, 20, 70, 20);
			Text serviceTxt = new Text(group2, SWT.BORDER);
			serviceTxt.setBounds(90, 20, 300, 20);
			serviceTxt.setText("SERVICE-HI");

			Label lb2 = new Label(group2, SWT.NONE); // 注意Label建立在group1上
			lb2.setText("实例名：");
			lb2.setBounds(10, 50, 70, 20);
			Text instanceTxt = new Text(group2, SWT.BORDER);
			instanceTxt.setBounds(90, 50, 300, 20);
			instanceTxt.setText("spring-cloud-front1");

			Button delServiceBtn = new Button(group2, SWT.NONE);
			delServiceBtn.setBounds(90, 80, 80, 20);
			delServiceBtn.setText("删除");

			Button outServiceBtn = new Button(group2, SWT.NONE);
			outServiceBtn.setBounds(180, 80, 80, 20);
			outServiceBtn.setText("take out");
			Button backServiceBtn = new Button(group2, SWT.NONE);
			backServiceBtn.setBounds(270, 80, 80, 20);
			backServiceBtn.setText("move back");

			Button updateJarServiceBtn = new Button(group2, SWT.NONE);
			updateJarServiceBtn.setBounds(360, 80, 80, 20);
			updateJarServiceBtn.setText("update jar");

			Label outLbl = new Label(group2, SWT.NONE); // 注意Label建立在group1上
			outLbl.setText("out：");
			outLbl.setBounds(10, 110, 70, 20);
			Text outTxt = new Text(group2, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL);
			outTxt.setBounds(90, 110, 1000, 400);
			Button refreshOutBtn = new Button(group2, SWT.NONE);
			refreshOutBtn.setBounds(1100, 110, 80, 20);
			refreshOutBtn.setText("清空");

			Label servicesLbl = new Label(group2, SWT.NONE); // 注意Label建立在group1上
			servicesLbl.setText("服务列表：");
			servicesLbl.setBounds(10, 520, 70, 20);

			// 定义一个树对象
			final Tree tree = new Tree(group2, SWT.SINGLE | SWT.Selection);
			_tree = tree;
			tree.setBounds(90, 520, 1000, 400);

			ArrayList<PFEurekaInstance> selectedEurekaInstances = new ArrayList<PFEurekaInstance>();
			_tree.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					// Alert(shell,e.text);
					TreeItem item = (TreeItem) e.item;
					TreeItem p = item.getParentItem();
					if (p != null) {
						serviceTxt.setText(p.getText());
						instanceTxt.setText(item.getText());
						selectedEurekaInstances.clear();
						selectedEurekaInstances.add(GetEurekaInstance(serviceTxt.getText(), instanceTxt.getText()));
					} else {
						TreeItem[] children = item.getItems();
						if (children != null && children.length > 0) {
							selectedEurekaInstances.clear();
							for (int i = 0; i < children.length; i++) {
								selectedEurekaInstances.add(GetEurekaInstance(item.getText(), children[i].getText()));
							}
						}
					}
				}
			});
			// 创建树节点
			// RefreshServiceTree();

			Button refreshServiceTreeBtn = new Button(group2, SWT.NONE);
			refreshServiceTreeBtn.setBounds(1100, 520, 80, 20);
			refreshServiceTreeBtn.setText("刷新");
			Button autoRefreshServiceTreeBtn = new Button(group2, SWT.TOGGLE);
			_autoRefreshServiceTreeBtn = autoRefreshServiceTreeBtn;
			autoRefreshServiceTreeBtn.setBounds(1100, 550, 80, 20);
			autoRefreshServiceTreeBtn.setText("自动刷新");

			delServiceBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					try {
						PFRequireValidator validate = new PFRequireValidator(new Text[] { serviceTxt, instanceTxt });
						if (!validate.IsValidate()) {
							Alert(shell, "服务名,实例名均为必填");
							e.doit = false;
							return;
						}
						PFRequestResult r = ProjHelper.EurekaServiceDelete(serviceTxt.getText(), instanceTxt.getText());
						// PFRequestResult r=PFDataHelper.HttpDelete(String.format(
						// "http://localhost:8761/eureka/apps/%s/%s",
						// serviceTxt.getText(),instanceTxt.getText()),"");
						outTxt.append(r.toString());
						RefreshServiceTree();
					} catch (Exception e1) { // TODO Auto-generated catch block
						if (e1 != null) {
							String s = e1.getMessage();
							if (PFDataHelper.StringIsNullOrWhiteSpace(s)) {
								s = e1.getCause().getMessage();
							}
							Alert(shell, s);
						}
					}
				}
			});

			outServiceBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					try {
						PFRequireValidator validate = new PFRequireValidator(new Text[] { serviceTxt, instanceTxt });
						if (!validate.IsValidate()) {
							Alert(shell, "服务名,实例名均为必填");
							e.doit = false;
							return;
						}
						PFRequestResult r = ProjHelper.EurekaServiceOut(serviceTxt.getText(), instanceTxt.getText());
						// PFRequestResult r=PFDataHelper.HttpPut(String.format(
						// "http://localhost:8761/eureka/apps/%s/%s/status?value=OUT_OF_SERVICE",
						// serviceTxt.getText(),instanceTxt.getText()),"");
						outTxt.append(r.toString());
						RefreshServiceTree();
					} catch (Exception e1) { // TODO Auto-generated catch block
						if (e1 != null) {
							String s = e1.getMessage();
							if (PFDataHelper.StringIsNullOrWhiteSpace(s)) {
								s = e1.getCause().getMessage();
							}
							Alert(shell, s);
						}
					}
				}
			});

			backServiceBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					try {
						PFRequireValidator validate = new PFRequireValidator(new Text[] { serviceTxt, instanceTxt });
						if (!validate.IsValidate()) {
							Alert(shell, "服务名,实例名均为必填");
							e.doit = false;
							return;
						}
						PFRequestResult r = PFDataHelper
								.HttpDelete(String.format("http://localhost:8761/eureka/apps/%s/%s/status?value=UP",
										serviceTxt.getText(), instanceTxt.getText()), "");
						outTxt.append(r.toString());
						RefreshServiceTree();
					} catch (Exception e1) { // TODO Auto-generated catch block
						if (e1 != null) {
							String s = e1.getMessage();
							if (PFDataHelper.StringIsNullOrWhiteSpace(s)) {
								s = e1.getCause().getMessage();
							}
							Alert(shell, s);
						}
					}
				}
			});

			updateJarServiceBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {

					try {
						//待启用--benjamin 
						if (selectedEurekaInstances.isEmpty()) {
							Alert(shell, "未选择eureka项");
							e.doit = false;
							return;
						}

						FileDialog OpenFileDialog = new FileDialog(shell, SWT.OPEN);
						OpenFileDialog.setText("打开型文件选择对话框");
						// OpenFileDialog.setFilterExtensions(new String[] { "*.*", "*.psd", "*.jpg",
						// "*.txt", "*.doc", "*.exe" });
						// OpenFileDialog.setFilterNames(new String[] { "所有类型(*.*)", "potoshopg
						// 格式(*.psd)", "文本格式(*.txt)" });
						OpenFileDialog.setFilterExtensions(new String[] { "*.jar", "*.exe" });
						OpenFileDialog.setFilterNames(new String[] { "jar 格式(*.jar)", "exe 格式(*.exe)" });
						// OpenFileDialog.setFilterPath("C:\\");
						OpenFileDialog.setFilterPath("D:\\eclipse_release\\springCloudPF\\backup");
						String fPath = OpenFileDialog.open();
						if (fPath == null) {
							Alert(shell, "请选择文件");
							// MessageDialog.openInformation(shell, null, "请选择文件");
						} else {

//							new Thread() {// 线程操作，
//								public void run() {// 请注意，新的线程内不能使用线程外的swt部件方法，否则会报错的(所以springModel.setServiceName(serviceTxt.getText())这句一定要放外面)
//
////									ArrayList<PFEurekaInstance> tmpIns=new ArrayList<PFEurekaInstance>();
////									tmpIns.add(new PFEurekaInstance("CONFIG-SERVER", "config-server1","192.168.205.111","8888"));
////									ArrayList<String> testStringArray=new ArrayList<String>();
////									testStringArray.add("555");
////									ArrayList<PFEurekaInstance2> testObjArray=new ArrayList<PFEurekaInstance2>();
////									testObjArray.add(new PFEurekaInstance2("CONFIG-SERVER", "config-server1"));
////									UploadJarRestModel testObj=new UploadJarRestModel();
////									testObj.setEurekaInstance(testObjArray);
////									
////									HashMap<String, Object> strArrMap=new HashMap<String, Object>() {
////										private static final long serialVersionUID = 1L;
////
////										{
////											put("eurekaInstance", testStringArray);
////										}
////									};
////									HashMap<String, Object> objArrMap=new HashMap<String, Object>() {
////										private static final long serialVersionUID = 1L;
////
////										{
////											put("eurekaInstance", testObjArray);
////										}
////									};
////									HashMap<String, Object> objMap=new HashMap<String, Object>() {
////										private static final long serialVersionUID = 1L;
////
////										{
////											put("eurekaInstance", testObj);
////										}
////									};
////									String tmpMapJson=JSON.toJSONString(strArrMap);
//									
////									PFDataHelper.HttpPostFile(
////											PFDataHelper.FormatString("http://192.168.205.111:28601/UploadJar", ""),
////											new File(fPath), new HashMap<String, Object>() {
////												private static final long serialVersionUID = 1L;
////
////												{
////													put("eurekaInstance", tmpIns.toArray());
////													//put("eurekaInstance", testP.toArray());
//////												put("serviceName", springModel.getServiceName());
//////												put("instanceName", springModel.getInstanceName());
////												}
////											});
////									PFDataHelper.HttpPost(
////											"http://192.168.205.111:28601/UploadJar",
////											tmpMapJson
////											 );
//									
//									//成功调用UploadJar的api
////									PFDataHelper.HttpPostFile(
////											"http://192.168.205.111:28601/UploadJar",
////											new File(fPath),
////											strArrMap
////											 );
//									
////									PFDataHelper.HttpPostFile(
////											"http://192.168.205.111:28601/UploadJar",
////											new File(fPath),
////											objMap
////											 );
//								}
//							}.start();
//							
//							PFSpringModel springModel = new PFSpringModel();
//							springModel.setServiceName(serviceTxt.getText());
//							springModel.setInstanceName(instanceTxt.getText());
//							PFEurekaInstance eurekaInstance = GetEurekaInstance(serviceTxt.getText(),
//									instanceTxt.getText());

//					        new Thread() {//线程操作
//					               public void run() {
//										PFDataHelper.HttpGet(
//												PFDataHelper.FormatString("http://{0}:28601/StopSpring", "192.168.205.111"),
//												 null);
//					               }
//					        }.start();//不会死锁
//							
//							PFDataHelper.HttpGet(
//											PFDataHelper.FormatString("http://{0}:28601/StopSpring", "192.168.205.111"),
//											 null);//死锁

//							_display.syncExec(new Runnable() {
//								public void run() {
//									PFDataHelper.HttpGet(
//											PFDataHelper.FormatString("http://{0}:28601/StopSpring", "192.168.205.111"),
//											 null);
//								}
//							});//死锁

//							_display.syncExec(new Runnable() {
//								public void run() {
//							        new Thread() {//线程操作
//						               public void run() {
//											PFDataHelper.HttpGet(
//													PFDataHelper.FormatString("http://{0}:28601/StopSpring", "192.168.205.111"),
//													 null);
//						               }
//							        }.start();
//								}
//							});//不会死锁

//							PFDataHelper.HttpPostFile(
////									"http://localhost:28601/UploadJar",
////											PFDataHelper.FormatString("http://{0}:28601/UploadJar", eurekaInstance.getIpAddr()),
//											PFDataHelper.FormatString("http://{0}:28601/StopSpring", "192.168.205.111"),
//											new File(fPath), new HashMap<String, Object>() {
//												private static final long serialVersionUID = 1L;
//
//												{
//													put("serviceName", "CONFIG-SERVER");
//													put("instanceName", "config-server1");
//												}
//											});

							// benjamin todo

							new Thread() {// 线程操作，
								public void run() {// 请注意，新的线程内不能使用线程外的swt部件方法，否则会报错的(所以springModel.setServiceName(serviceTxt.getText())这句一定要放外面)
									Map<String, java.util.List<PFEurekaInstance>> detailmap = selectedEurekaInstances
											.stream().collect(Collectors.groupingBy(d -> d.getIpAddr()));

									Iterator<Entry<String, java.util.List<PFEurekaInstance>>> iter = detailmap
											.entrySet().iterator();
									while (iter.hasNext()) {
										Entry<String, java.util.List<PFEurekaInstance>> key = iter.next();

										PFDataHelper.HttpPostFile(
												PFDataHelper.FormatString("http://{0}:28601/UploadJar", key.getKey()),
												new File(fPath), new HashMap<String, Object>() {
													private static final long serialVersionUID = 1L;

													{
//														put("eurekaInstance",PFDataHelper.ListSelect(key.getValue(), a->a.getServiceName()+","+a.getInstanceName()) );
														put("eurekaInstance",key.getValue());
													}
												});
									}

								}
							}.start();

//							_display.syncExec(new Runnable() {
//								public void run() {
//							        new Thread() {//线程操作
//						               public void run() {
//
//											PFDataHelper.HttpPostFile(
//													PFDataHelper.FormatString("http://{0}:28601/UploadJar", eurekaInstance.getIpAddr()),
//													new File(fPath), new HashMap<String, Object>() {
//														private static final long serialVersionUID = 1L;
//
//														{
//															put("serviceName", springModel.getServiceName());
//															put("instanceName", springModel.getInstanceName());
//														}
//													});
//
//						               }
//							        }.start();
//								}
//							});
//							
//							Display.getDefault().syncExec(new Runnable() {
//								public void run() {
//
//									PFSpringModel springModel = new PFSpringModel();
//									springModel.setServiceName(serviceTxt.getText());
//									springModel.setInstanceName(instanceTxt.getText());
//
//									PFEurekaInstance eurekaInstance = GetEurekaInstance(serviceTxt.getText(),
//											instanceTxt.getText());
//									PFDataHelper.HttpPostFile(
//											PFDataHelper.FormatString("http://{0}:28601/UploadJar", eurekaInstance.getIpAddr()),
//											new File(fPath), new HashMap<String, Object>() {
//												private static final long serialVersionUID = 1L;
//
//												{
//													put("serviceName", springModel.getServiceName());
//													put("instanceName", springModel.getInstanceName());
//												}
//											});
//								}
//							});

//							PFSpringModel springModel = new PFSpringModel();
//							springModel.setServiceName(serviceTxt.getText());
//							springModel.setInstanceName(instanceTxt.getText());
//
//							PFEurekaInstance eurekaInstance = GetEurekaInstance(serviceTxt.getText(),
//									instanceTxt.getText());
//							PFDataHelper.HttpPostFileAsync(
//									PFDataHelper.FormatString("http://{0}:28601/UploadJar", eurekaInstance.getIpAddr()),
//									new File(fPath), new HashMap<String, Object>() {
//										private static final long serialVersionUID = 1L;
//
//										{
//											put("serviceName", springModel.getServiceName());
//											put("instanceName", springModel.getInstanceName());
//										}
//									});

//							_tempThread=new Thread() {//线程操作
//					               public void run() {//这里如果不用异步，swt的界面会死锁
//										PFSpringModel springModel = new PFSpringModel();
//										springModel.setServiceName(serviceTxt.getText());
//										springModel.setInstanceName(instanceTxt.getText());
//
//										PFEurekaInstance eurekaInstance = GetEurekaInstance(serviceTxt.getText(),
//												instanceTxt.getText());
//										PFDataHelper.HttpPostFile(
//												PFDataHelper.FormatString("http://{0}:28601/UploadJar", eurekaInstance.getIpAddr()),
//												new File(fPath), new HashMap<String, Object>() {
//													private static final long serialVersionUID = 1L;
//
//													{
//														put("serviceName", springModel.getServiceName());
//														put("instanceName", springModel.getInstanceName());
//													}
//												});
//					               }
//					        };
//					        _tempThread.start();//不会死锁

						}
						PFRequestResult r = new PFRequestResult();
						outTxt.append(r.toString());

						RefreshServiceTree();
					} catch (Exception e1) { // TODO Auto-generated catch block
						if (e1 != null) {
							String s = e1.getMessage();
							if (PFDataHelper.StringIsNullOrWhiteSpace(s) && e1.getCause() != null) {
								s = e1.getCause().getMessage();
							}
							Alert(shell, s);
						}
					}
				}
			});

			refreshServiceTreeBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					RefreshServiceTree();
				}
			});

			refreshOutBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					outTxt.setText("");
				}
			});

			autoRefreshServiceTreeBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					Boolean b = autoRefreshServiceTreeBtn.getSelection();
					if (b) {
						_isAutoRefreshService = true;
						new Thread() {// 线程操作
							public void run() {
								while (_isAutoRefreshService && (!_isAutoRefreshing)) {
									_isAutoRefreshing = true;
									// 对Label进行实时刷新，需要加上这句
									_tree.getDisplay().asyncExec(new Runnable() {
										@Override
										public void run() {
											RefreshServiceTree();
											_isAutoRefreshing = false;
										}
									});
									try {
										Thread.sleep(_isAutoRefreshServiceTime);// 每隔一秒刷新一次
									} catch (Exception e) {
										// _isAutoRefreshService=false;
										// _isAutoRefreshing=false;
									}
								}
							}
						}.start();
					} else {
						_isAutoRefreshService = false;
					}
				}
			});

		}

	}

	private static void RefreshServiceTree() {
		Tree tree = _tree;
		_tree.removeAll();
		PFRequestResult r;

		r = PFDataHelper.HttpGet("http://localhost:8761/eureka/apps", "");
		if (r.refuse || r.statusCode != PFDataHelper.HTTP_STATUS_OK) {
			StopRefreshServiceTree();
			return;
		}
		// 返回字符串转为Document
		Document document;
		try {
			document = DocumentHelper.parseText(r.content);

			// 获取根元素
			Element root = document.getRootElement();
			// 获取所有子元素
			java.util.List<?> applications = root.elements("application");
			_eurekaInstances.clear();
			for (int i = 0; i < applications.size(); i++) {
				Element application = (Element) applications.get(i);
				final TreeItem treeItemGoogle = new TreeItem(tree, SWT.NONE);
				treeItemGoogle.setText(application.element("name").getText());
				java.util.List<?> instances = application.elements("instance");
				for (int j = 0; j < instances.size(); j++) {
					Element instance = (Element) instances.get(j);
					PFEurekaInstance eurekaInstance = new PFEurekaInstance(instance);
					_eurekaInstances.add(eurekaInstance);
					// new TreeItem(treeItemGoogle,
					// SWT.NULL).setText(instance.elementText("instanceId"));
					new TreeItem(treeItemGoogle, SWT.NULL).setText(eurekaInstance.getInstanceName());

				}
				treeItemGoogle.setExpanded(true);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void Alert(Shell shell, String msg) {
		PFSwtHelper.Alert(shell, msg);
//		if(PFDataHelper.StringIsNullOrWhiteSpace(msg)) {return;}
//		MessageBox dialog=new MessageBox(shell,SWT.NONE);
//        dialog.setText("提示");
//        dialog.setMessage(msg);
//        dialog.open();		
	}

	private static void StopRefreshServiceTree() {
		_autoRefreshServiceTreeBtn.setSelection(false);
		_isAutoRefreshService = false;
	}

	private static void Stop() {
		// _isAutoRefreshService=false;
		for (int i = 0; i < _springs.size(); i++) {// 内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
			SpringLoader p = _springs.get(i);
			p.close();
		}

	}

	private static Boolean AnyRunning() {
		for (int i = 0; i < _springs.size(); i++) {// 内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
			SpringLoader p = _springs.get(i);
			if (p.IsRunning()) {
				return true;
			}
		}
		return false;
	}

	private static void AddWatchSpringTab(PFSpringModel m) {

		CTabItem tabItemTmp = new CTabItem(_tabFolder, SWT.BORDER);// 声明第1个选项页
		tabItemTmp.setShowClose(false);
		tabItemTmp.setText(m.getSpringName()); // 设置选项页的标题
		{
			// 创建第1个分组框，建立在tabFolder上--benjamin
			Group groupTmp = new Group(_tabFolder, SWT.NONE);
			groupTmp.setText("输出信息"); // 设置分组框说明信息
			tabItemTmp.setControl(groupTmp); // 让tabItem1控制group1
			Label lb1 = new Label(groupTmp, SWT.NONE); // 注意Label建立在group1上
			lb1.setText("out：");
			lb1.setBounds(10, 20, 70, 20);
			Text text1 = new Text(groupTmp, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL);
			// text1.setWordWrap(true);
			text1.setBounds(90, 20, 1000, 400);
			// text1.setTextLimit(5);
			// text1.setToolTipText("文本项不能为空,且输入不超过8 位密码");

			Label lb2 = new Label(groupTmp, SWT.NONE);
			lb2.setText("err：");
			lb2.setBounds(10, 430, 70, 20);
			Text text2 = new Text(groupTmp, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL);
			// text2.setBounds(90, 50, 70, 20);
			text2.setBounds(90, 430, 1000, 400);

			Button isEurekaBtn = new Button(groupTmp, SWT.CHECK);
			isEurekaBtn.setBounds(90, 840, 70, 20);
			isEurekaBtn.setText("Eureka");
			isEurekaBtn.setEnabled(false);
			isEurekaBtn.setSelection(m.isEurekaClient());

			Button rebootBtn = new Button(groupTmp, SWT.NONE);
			rebootBtn.setBounds(170, 840, 70, 20);
			rebootBtn.setText("重新启动");
			rebootBtn.setEnabled(false);

			Button closeBtn = new Button(groupTmp, SWT.NONE);
			closeBtn.setBounds(250, 840, 70, 20);
			closeBtn.setText("停止");
			// 要异步--benjamin
			MavenSwtPFAppJarTab jarTab = new MavenSwtPFAppJarTab(m, tabItemTmp, rebootBtn, text1, text2);
			// PFSpringList m=_pfSpringList.getList().get(i);
			SpringLoader d = new SpringLoader(m, new CmdOutListener(text1),
					/*
					 * new CustomDelegate() {
					 * 
					 * @Override public void setValue(Object value) { // TODO Auto-generated method
					 * stub Display.getDefault().syncExec(new Runnable() { public void run() {
					 * text1.append(value.toString()+"\r\n"); } }); }
					 * 
					 * },
					 */
					new CustomDelegate() {
						@Override
						public void setValue(Object value) {
							_display.syncExec(new Runnable() {// 为了解决其它线程不能处理本线程的swt控件https://blog.csdn.net/ecjtuxuan/article/details/2125023
								public void run() {
									String t = text2.getText();
									if (t != null && t.length() > 5000) {
										text2.setText("");
									}
									text2.append(value.toString() + "\r\n");
									rebootBtn.setEnabled(true);
									tabItemTmp.setShowClose(true);
									tabItemTmp.setText(m.getSpringName() + "(停止)");
								}
							});

						}

					},
//					new CustomDelegate() {
//						@Override
//						public void setValue(Object value) {
//							_display.syncExec(new Runnable() {//为了解决其它线程不能处理本线程的swt控件https://blog.csdn.net/ecjtuxuan/article/details/2125023
//						        public void run() {
//									rebootBtn.setEnabled(false);
//									tabItemTmp.setShowClose(false);
//									tabItemTmp.setText(m.getSpringName());
//									text1.setText("");
//									text2.setText("");
//						        }
//						    }); 
//							
//						}				
//					}
					new CmdStartListener(jarTab));
			d.open();
			_springs.add(d);
			_jarTabs.add(jarTab);

			rebootBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
//这部分代码移到SpringLoader._startAction里
//							rebootBtn.setEnabled(false);
//							tabItemTmp.setShowClose(false);
//							tabItemTmp.setText(m.getSpringName());
//							text1.setText("");
//							text2.setText("");
					d.close();
					d.open();
				}
			});
			closeBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {

					ShowProcessDialog spd = new ShowProcessDialog(_shell, new CustomDelegate() {
						@Override
						public void setValue(Object value) {
							d.cutEureka();
							try {
								Thread.sleep(2000);// 这个秒数应该大于或等于gateway的fetch from eureka interval加上crm service的cache
													// update
													// interval秒数,这样才安全.现时的界点是2秒.实测重启4个实例会断两次,断50毫秒左右,原因未明(应该不是因为心跳续约引起的,否则应该不只50毫秒),猜测可能是正在传输时同时断开所致--benjamin
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							d.close();
						}
					});
					spd.open(); //

				}
			});

		}
//		//tabItemTmp.addListener(EventType., listener);
//
//		tabItemTmp.addDisposeListener(new DisposeListener() {
//
//	      public void widgetDisposed(DisposeEvent e) {
//				e..doit = message == SWT.YES;
//	      }
//	    });
//		shell.addShellListener(new ShellAdapter() {
//			public void shellClosed(ShellEvent e) {
//				if(AnyRunning()) {
//					Alert(shell,"有正在运行的程序，不能关闭窗口");
//					/*
//					 * MessageBox dialog=new MessageBox(shell,SWT.NONE); dialog.setText("提示");
//					 * dialog.setMessage("有正在运行的程序，不能关闭窗口"); dialog.open();
//					 */
//					e.doit=false;
//					return ;
//				}
//				MessageBox messagebox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
//				messagebox.setText("MessageBox 对话框");
//				messagebox.setMessage("请确认是否退出?");
//				int message = messagebox.open();
//								
//				e.doit = message == SWT.YES;
//			}
//		});
	}

}
