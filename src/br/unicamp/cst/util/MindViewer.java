/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicamp.cst.util;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.representation.owrl.*;
import br.unicamp.cst.core.entities.Mind;
import br.unicamp.cst.core.entities.TestCodelet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author gudwin
 */
public class MindViewer extends javax.swing.JFrame {
    
    private JTree jtree;
    Mind wog;
    
    

    /**
     * Creates new form WorldObjectViewer
     */
    public MindViewer(String windowName) {
        initComponents();
        TreeModel tm = createTreeModel(new Mind());
        jtree = new JTree(tm);
        expandAllNodes(jtree);
        jsp.setViewportView(jtree);
        jtree.setCellRenderer(new RendererJTree());
        setTitle(windowName);
        StartTimer();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsp = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MindViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MindViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MindViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MindViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Mind m = new Mind();
        MemoryObject m1 = m.createMemoryObject("Memory 1","S1");
        MemoryObject m2 = m.createMemoryObject("Memory 2","S2");
        MemoryObject m3 = m.createMemoryObject("Memory 3","S3");
        MemoryObject m4 = m.createMemoryObject("Memory 4","S4");
        MemoryObject m5 = m.createMemoryObject("Memory 5","S5");
        MemoryContainer m6 = new MemoryContainer("Container");
        Codelet c = new TestCodelet();
        c.addInput(m1);
        c.addInput(m2);
        c.addOutput(m3);
        c.addOutput(m4);
        c.addBroadcast(m5);
        m.insertCodelet(c);
        Codelet c2 = new TestCodelet();
        c2.addInput(m4);
        c2.addInput(m5);
        c2.addOutput(m1);
        c2.addOutput(m3);
        c2.addBroadcast(m5);
        m.insertCodelet(c2);
        MindViewer ov = new MindViewer("Mind");
        ov.setVisible(true);
        System.out.println("Teste:");
        ov.updateTree(m);
    }
  
    public void setWO(Mind newwog) {
        wog = newwog;
    }
    
    private DefaultMutableTreeNode addRootNode(String rootNodeName) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new TreeElement(rootNodeName, TreeElement.NODE_NORMAL, null, TreeElement.ICON_CONFIGURATION));
        return(root);
    }
    
    private DefaultMutableTreeNode addMind(Mind m) {
        
        DefaultMutableTreeNode mindNode = addItem("Mind",TreeElement.ICON_MIND);
        DefaultMutableTreeNode codeletsNode = addItem("Codelets",TreeElement.ICON_CODELETS);
        mindNode.add(codeletsNode);
        List<Codelet> codelets = m.getCodeRack().getAllCodelets();
        for (Codelet oo : codelets) {
            DefaultMutableTreeNode newcodeletNode = addCodelet(oo);
            codeletsNode.add(newcodeletNode);
        }
        DefaultMutableTreeNode memoriesNode = addItem("Memories",TreeElement.ICON_MEMORIES);
        mindNode.add(memoriesNode);
        List<Memory> memories = m.getRawMemory().getAllMemoryObjects();
        for (Memory mo : memories) {
                DefaultMutableTreeNode memoryNode = addMemory(mo);
                memoriesNode.add(memoryNode);
            }
        
        return(mindNode);    
    }
    
    private DefaultMutableTreeNode addCodelet(Codelet p) {
        DefaultMutableTreeNode codeletNode = addItem(p.getName(), TreeElement.ICON_CODELET);
        List<Memory> inputs = p.getInputs();
        List<Memory> outputs = p.getOutputs();
        List<Memory> broadcasts = p.getBroadcast();
        DefaultMutableTreeNode inputs_t = addItem("Inputs",TreeElement.ICON_INPUT);
        for (Memory i : inputs) {
            DefaultMutableTreeNode memoryNode = addItem(i.getName(),TreeElement.ICON_MEMORY);
            inputs_t.add(memoryNode);
        }
        codeletNode.add(inputs_t);
        DefaultMutableTreeNode outputs_t = addItem("Ouputs",TreeElement.ICON_OUTPUT);
        for (Memory o : outputs) {
            DefaultMutableTreeNode memoryNode = addItem(o.getName(),TreeElement.ICON_MEMORY);
            outputs_t.add(memoryNode);
        }
        codeletNode.add(outputs_t);
        DefaultMutableTreeNode broadcasts_t = addItem("Broadcasts",TreeElement.ICON_BROADCAST);
        for (Memory b : broadcasts) {
            DefaultMutableTreeNode memoryNode = addItem(b.getName(),TreeElement.ICON_MEMORY);
            broadcasts_t.add(memoryNode);
        }
        codeletNode.add(broadcasts_t);
        return(codeletNode);
    }
    
    private DefaultMutableTreeNode addItem(String p, int icon_type) {
        Object o = new TreeElement(p, TreeElement.NODE_NORMAL, p, icon_type);
        DefaultMutableTreeNode memoryNode = new DefaultMutableTreeNode(o);
        return(memoryNode);
    }
    
    
    private DefaultMutableTreeNode addMemory(Memory p) {
        DefaultMutableTreeNode memoryNode = addItem(p.getName(),TreeElement.ICON_MEMORIES);
        String name = p.getName();
        if (p.getClass().getCanonicalName().equals("br.unicamp.cst.core.entities.MemoryObject")) {
            memoryNode = addItem(name,TreeElement.ICON_MO);
        }
        else if (p.getClass().getCanonicalName().equals("br.unicamp.cst.core.entities.MemoryContainer")) {
            MemoryContainer mc = (MemoryContainer)p;
            for (Memory mo : mc.getAllMemories()) {
                DefaultMutableTreeNode newmemo = addMemory(mo);
                memoryNode.add(newmemo);
            }            
        }
        return(memoryNode);
    }
    
    public TreeModel createTreeModel(Mind m) {
        DefaultMutableTreeNode o = addMind(m);
        TreeModel tm = new DefaultTreeModel(o);
        return(tm);
    }
    
//    private JTree addNodeJTree(WorldObject wo) {
//        JTree tree;
//        TreeModel tm = createTreeModel(wo);
//        tree = new JTree(tm);
//        expandAllNodes(tree);
//        return tree;
//    }
    
    private void expandAllNodes(JTree tree) {
         expandAllNodes(tree, 0, tree.getRowCount());
    }
    
    private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
       for(int i=startingIndex;i<rowCount;++i){
          tree.expandRow(i);
       }
       if(tree.getRowCount()!=rowCount){
          expandAllNodes(tree, rowCount, tree.getRowCount());
       }
    }
    
    public void updateTree(Mind m) {
       TreeModel tm = createTreeModel(m);
       jtree.setModel(tm);
       expandAllNodes(jtree);
    }
    
     public void StartTimer() {
        Timer t = new Timer();
        WOVTimerTask tt = new WOVTimerTask(this);
        t.scheduleAtFixedRate(tt,0,10000);
    }
    
    public void tick() {
        if (wog != null) updateTree(wog);
    }

    class WOVTimerTask extends TimerTask {
    MindViewer wov;
    boolean enabled = true;
    
    public WOVTimerTask(MindViewer wovi) {
        wov = wovi;
    }
    
    public void run() {
        if (enabled) wov.tick();
    }
    
    public void setEnabled(boolean value) {
        enabled = value;
    }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jsp;
    // End of variables declaration//GEN-END:variables
}