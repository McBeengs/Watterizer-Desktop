package com.watterizer.panels.options;

import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import static javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION;

public class OptionsJFrame extends javax.swing.JFrame {

    private final String originalContent;
    private DefaultTreeModel model;
    private DefaultMutableTreeNode hierarchy;
    private DefaultMutableTreeNode batch;
    private final GridBagConstraints c;
    private final XmlManager xml;
    private final XmlManager language;

    public OptionsJFrame() {
        setTitle("Opções - Watterizer");
        xml = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
        language = UsefulMethods.getManagerInstance(UsefulMethods.LANGUAGE);

        originalContent = xml.toString();
        initComponents();
        setTree();
        getContentPane().setBackground(Color.black);

        mainContainer.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        Option1 o1 = new Option1(xml);
        
        o1.setVisible(true);
        c.gridx = 0;
        c.gridy = 0;
        mainContainer.add(o1, c);

        optionsTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (optionsTree.getSelectionModel().getLeadSelectionRow()) {
                    case 0:
                        o1.setVisible(false);
                        break;
                    case 1:
                        o1.setVisible(true);
                        break;
                    case 2:
                        o1.setVisible(false);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setTree() {
        //This creates the root of the tree on which all the others will be linked (not visible)
        hierarchy = new DefaultMutableTreeNode("root");

        //This sets the first group of options...
        DefaultMutableTreeNode section1 = new DefaultMutableTreeNode("Configurações");

        //... and add them to the root
        hierarchy.add(section1);

        //Sets all the options pulled from the file... 
        String[] section1Ops = new String[1];

        section1Ops[0] = "Opções Gerais";

        //... and sets them to the section
        for (String section1Op : section1Ops) {
            batch = new DefaultMutableTreeNode(section1Op);
            section1.add(batch);
        }

        model = new DefaultTreeModel(hierarchy);

        optionsTree.setModel(model);
        optionsTree.setCellRenderer(new JTreeRenderer());
        optionsTree.getSelectionModel().setSelectionMode(SINGLE_TREE_SELECTION);

        for (int i = 0; i < optionsTree.getRowCount(); i++) {
            optionsTree.expandRow(i);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        optionsTree = new javax.swing.JTree();
        mainContainer = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        cancelButton.setText("Cancelar");
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Salvar");
        saveButton.setFocusPainted(false);
        saveButton.setFocusable(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        optionsTree.setBackground(new java.awt.Color(0, 0, 0));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        optionsTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        optionsTree.setRootVisible(false);
        jScrollPane1.setViewportView(optionsTree);

        mainContainer.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator1)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelButton)
                        .addComponent(saveButton))
                    .addContainerGap()))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        cancelButton.setFocusPainted(false);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int option = JOptionPane.showConfirmDialog(this, language.getContentById("saveConfirm"),
                language.getContentById("save"), 0);

        if (option == JOptionPane.OK_OPTION) {
            try {
                xml.saveXml();
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(OptionsJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
//        if (xml.toString().equals(originalContent)) {
//            this.dispose();
//        } else {
//            int option = JOptionPane.showConfirmDialog(this, language.getContentById("cancelConfirm"),
//                    language.getContentById("cancel"), 0);
//
//            if (option == JOptionPane.OK_OPTION) {
//                this.dispose();
//            }
//        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainContainer;
    private javax.swing.JTree optionsTree;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    private class JTreeRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                boolean expanded, boolean leaf, int row, boolean hasFocus) {

            super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

            setForeground(Color.white);
            setBackgroundNonSelectionColor(Color.black);
            setOpenIcon(new ImageIcon(getClass().getResource("/com/watterizer/style/icons/openArrow.png")));
            setClosedIcon(new ImageIcon(getClass().getResource("/com/watterizer/style/icons/closedArrow.png")));
            setLeafIcon(new ImageIcon(getClass().getResource("/com/watterizer/style/icons/mainBullet.png")));

            return this;
        }
    }
}
