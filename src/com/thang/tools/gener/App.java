package com.thang.tools.gener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 * 代码生成器
 * @author zyt
 *
 */
public class App extends JFrame {

	private static final long serialVersionUID = 1159831905359034014L;
    private static String src=null;
    private static String web=null;
    private static String service=null;

    public App() {
        initComponents();
    }

    private void initTree(DefaultMutableTreeNode treeNode,File dir){
    	if(dir.isDirectory()){
    		File[] subFiles=dir.listFiles();
    		for(File subFile:subFiles){
    			DefaultMutableTreeNode node=new DefaultMutableTreeNode(subFile.getName());
    			if(subFile.isDirectory()){
    				initTree(node,subFile);
    			}
    			treeNode.add(node);
    		}
    	}else{
    		DefaultMutableTreeNode node=new DefaultMutableTreeNode(dir.getName());
    		treeNode.add(node);
    	}
    }
    
    private void initComponents() {

    	
    	String path=this.getClass().getResource("/").getFile();
    	src=path.replaceFirst("build/classes/","src");
    	web=path.replaceFirst("build/classes/","src/com/thang/web");
    	service=path.replaceFirst("build/classes/","src/com/thang/service");
        File entityDir=new File(path.replaceFirst("build/classes/", "src/com/thang/entity"));
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("entity");
        initTree(root,entityDir);
    	
        delExist = new javax.swing.JCheckBox();
        treeScrollPanel = new javax.swing.JScrollPane();
        pkgTree = new javax.swing.JTree(root);
        generBtn = new javax.swing.JButton();
        generProgressBar = new javax.swing.JProgressBar();
        stopBtn = new javax.swing.JButton();
        generTypes = new javax.swing.JComboBox(new String[]{"后台代码","前台页面","全部代码"});
        generTypeLabel = new javax.swing.JLabel();

        setLocation(350, 150);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("代码生成器");

        treeScrollPanel.setViewportView(pkgTree);

        generBtn.setText("开始生成");

        stopBtn.setText("停止生成");

        generTypeLabel.setText("生成类型：");
        delExist.setText("覆盖己生成");

        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(treeScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(generBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stopBtn))
                    .addComponent(generProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(generTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(delExist)
                            .addComponent(generTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(treeScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(delExist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(generProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generBtn)
                    .addComponent(stopBtn))
                .addContainerGap())
        );

        pack();
    }                     

    
    public void gener(Class<?> modelClass){
    	Map<String,Object> root=new HashMap<String,Object>();
    	root.put("entityName", modelClass.getName());
    	root.put("pkgName", modelClass.getPackage().getName().replaceFirst("com.thang.entity.", ""));
    	root.put("url", modelClass.getPackage().getName().replaceFirst("com.thang.entity.", "").replaceAll("\\.", "/"));
    }
    
    /**
     * 启动程序入口
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }
    

    public static String getSrc() {
		return src;
	}

	public static String getWeb() {
		return web;
	}

	public static String getService() {
		return service;
	}



	// Variables declaration - do not modify
    private javax.swing.JCheckBox delExist;
    private javax.swing.JButton generBtn;
    private javax.swing.JProgressBar generProgressBar;
    private javax.swing.JLabel generTypeLabel;
    private javax.swing.JComboBox generTypes;
    private javax.swing.JTree pkgTree;
    private javax.swing.JButton stopBtn;
    private javax.swing.JScrollPane treeScrollPanel;
    // End of variables declaration                   
}
