/** *****************************************************************************
 * Copyright (c) 2012  DCA-FEEC-UNICAMP
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * Contributors to this module:
 *     S. M. de Paula and R. R. Gudwin
 ***************************************************************************** */
package br.unicamp.cst.representation.owrl;

import br.unicamp.cst.representation.owrl.grammar.CustomizedListener;
import br.unicamp.cst.representation.owrl.grammar.OwrlLexer;
import br.unicamp.cst.representation.owrl.grammar.OwrlParser;
import br.unicamp.cst.util.Pair;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 *
 * @author suelenmapa
 */
public class SimulateConfiguration extends javax.swing.JFrame {

    private JTree configurationTreeA;
    private JTree configurationTreeB;
    private DefaultMutableTreeNode parentNodeA;
    private DefaultMutableTreeNode parentNodeB;
    private ConfigurationManager configurations = new ConfigurationManager();

    /**
     * Creates new form SimulationConfiguration
     */
    public SimulateConfiguration() {
        initComponents();
        setTitle("SIMULATE A CONFIGURATION");
    }

    private void buildTree() {
        if (configurations.size() > 0) {
            parentNodeB = new DefaultMutableTreeNode(new TreeElement("CONFIGURATION", TreeElement.NODE_NORMAL, null, TreeElement.ICON_CONFIGURATION));
            configurationTreeB = new JTree(parentNodeB);
            jSPConfigB.setViewportView(configurationTreeB);
            if (configurations.size() > 1) {
                parentNodeA = new DefaultMutableTreeNode(new TreeElement("CONFIGURATION", TreeElement.NODE_NORMAL, null, TreeElement.ICON_CONFIGURATION));
                configurationTreeA = new JTree(parentNodeA);
                jSPConfigA.setViewportView(configurationTreeA);
            }
            repaint();

            configurationTreeB = TreeManager.addNodeJTree(configurations.getConfiguration(configurations.size() - 1), jSPConfigB, parentNodeB, configurationTreeB);
            configurationTreeB.setCellRenderer(new RendererJTree());
            if (configurations.size() > 1) {
                configurationTreeA = TreeManager.addNodeJTree(configurations.getConfiguration(configurations.size() - 2), jSPConfigA, parentNodeA, configurationTreeA);
                configurationTreeA.setCellRenderer(new RendererJTree());

                TreeManager.compareTrees(configurationTreeA, configurationTreeB, jSPConfigB);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButtonApply = new javax.swing.JButton();
        jSPConfigA = new javax.swing.JScrollPane();
        jSPConfigB = new javax.swing.JScrollPane();
        jButtonCompare = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaComand = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton4.setText("jButton4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonApply.setText("Apply");
        jButtonApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApplyActionPerformed(evt);
            }
        });

        jButtonCompare.setText("Compare");

        jTextAreaComand.setColumns(20);
        jTextAreaComand.setLineWrap(true);
        jTextAreaComand.setRows(5);
        jScrollPane1.setViewportView(jTextAreaComand);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonApply))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSPConfigA, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCompare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSPConfigB, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButtonCompare)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSPConfigA)
                            .addComponent(jSPConfigB))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonApply)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApplyActionPerformed
        // TODO add your handling code here:

        String s = jTextAreaComand.getText();
         //String s = jTextAreaComand.getText();
        jTextAreaComand.setText(null);
        // jTextAreaComand.setText(null);
        s = s + "\n";

        /**
         * ******************** to Parse *******************
         */
        ANTLRInputStream input = new ANTLRInputStream(s);

        // create a lexer that feeds off of input CharStream
        OwrlLexer lexer = new OwrlLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        OwrlParser parser = new OwrlParser(tokens);

        ParseTree tree = parser.conf(); // begin parsing at init rule

        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        CustomizedListener listener = new CustomizedListener();

        for (int i = 1; i < (tree.getChildCount() - 2); i++) {

            walker.walk(listener, tree.getChild(i));
          

        }

        List<Pair<String, List<AbstractObject>>> memory = listener.getMemory();

        if (!configurations.applyCommands(memory)) {
            jTextAreaComand.setText(jTextAreaComand.getText().concat("\nErro: Falha ao aplicar instruções. Verifique seu comando.\n"));
        }
        buildTree();
    }//GEN-LAST:event_jButtonApplyActionPerformed

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
            java.util.logging.Logger.getLogger(SimulateConfiguration.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimulateConfiguration.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimulateConfiguration.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimulateConfiguration.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimulateConfiguration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonApply;
    private javax.swing.JButton jButtonCompare;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jSPConfigA;
    private javax.swing.JScrollPane jSPConfigB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaComand;
    // End of variables declaration//GEN-END:variables
}
