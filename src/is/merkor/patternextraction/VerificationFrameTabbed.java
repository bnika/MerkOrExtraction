package is.merkor.patternextraction;

import is.merkor.util.database.DBCommunicator;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.Color;

/**
 * A GUI for the verification of extracted syntactic patterns.
 * Shows patterns with their examples, that are stored in a database.
 * Has the possibility to change classification of a pattern and
 * search for certain patterns or word.
 * 
 * @author Anna B. Nikulasdottir
 */
public class VerificationFrameTabbed extends javax.swing.JFrame {
    DBCommunicator communicator = new DBCommunicator();
    List<PatternInfo> patternsToDisplay = new ArrayList<PatternInfo>();
    String relation = "";
    String patternToMatch = "";
    // position of a selected item in patternsToDisplay
    int position = 0;
    int offset = 0;

    //table vars
    private ArrayList<String[]> tableList = new ArrayList<String[]>();
    private String[] columnNames  = new String[0];
    private PatternTableModel mtm = new PatternTableModel();
    ListSelectionModel tableSelectionModel;

    /** Creates new form VerificationFrameTabbed */
    public VerificationFrameTabbed() {
        initComponents();
        JRootPane rootPane = getRootPane();
        rootPane.setDefaultButton(jButtonGetPatterns);

        initTableListener();
    }

    
    private void initTableListener() {
        tableSelectionModel = jTablePatterns.getSelectionModel();
        tableSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
        jTablePatterns.setSelectionModel(tableSelectionModel);

        jTablePatterns.setRowSorter(new TableRowSorter(mtm));

    }
    private void initTextArea() {
        if (position >= patternsToDisplay.size()) {
            position = 0;
        }
       // String[] patternWithText = patternsToDisplay.get(position);
        PatternInfo patInf = patternsToDisplay.get(position);
        position++;
        String pattern = patInf.getPattern();
        String text = patInf.getOriginalString();
        Integer nrOfEx = patInf.getNrOfExamples();

        jLabelPattern.setText(pattern);
        jLabelNrOfEx.setText(nrOfEx.toString() + " examples");
        jTextAreaExamples.setText(text);
        jTextFieldNote.setText(patInf.getNote());
        jLabelAssignmentStatus.setText(patInf.getRelation().toUpperCase());
        jLabelAssignmentStatus.setForeground(Color.BLUE);
        jComboBoxRelations.setSelectedItem(patInf.getRelation());

        jLabelNrOfPatterns.setText("Pattern nr: " + (position) + "/" + patternsToDisplay.size());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePatterns = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBoxGen = new javax.swing.JCheckBox();
        jCheckBoxCoordNoun = new javax.swing.JCheckBox();
        jCheckBoxSuper = new javax.swing.JCheckBox();
        jCheckBoxOther = new javax.swing.JCheckBox();
        jCheckBoxLoc = new javax.swing.JCheckBox();
        jCheckBoxCoordAdj = new javax.swing.JCheckBox();
        jCheckBoxAdj = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxNrOfEx = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxAssignmentStat = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jCheckBoxPattern = new javax.swing.JCheckBox();
        jCheckBoxText = new javax.swing.JCheckBox();
        jCheckBoxNote = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSomeExamples = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jButtonGetPatterns = new javax.swing.JButton();
        jCheckBoxCoordProper = new javax.swing.JCheckBox();
        jCheckBoxRole = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaExamples = new javax.swing.JTextArea();
        jComboBoxRelations = new javax.swing.JComboBox();
        jButtonFwd = new javax.swing.JButton();
        jButtonRew = new javax.swing.JButton();
        jLabelAssignmentStatus = new javax.swing.JLabel();
        jLabelPattern = new javax.swing.JLabel();
        jLabelNrOfPatterns = new javax.swing.JLabel();
        jButtonSaveRelation = new javax.swing.JButton();
        jTextFieldNote = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButtonSaveNote = new javax.swing.JButton();
        jLabelNrOfEx = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("M E R K O R    P A T T E R N    V E R I F I C A T I O N");
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setResizable(false);

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel1.setAutoscrolls(true);
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        jTablePatterns.setFont(new java.awt.Font("Lucida Grande", 0, 13)); // NOI18N
        jTablePatterns.setModel(mtm);
        jTablePatterns.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTablePatterns);
        jTablePatterns.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14));
        jLabel1.setText("FILTER BY");

        jLabel2.setText("Relation:");

        jCheckBoxGen.setText("genitive");

        jCheckBoxCoordNoun.setText("coordinates noun");

        jCheckBoxSuper.setText("superordinate");

        jCheckBoxOther.setText("other");

        jCheckBoxLoc.setText("location");

        jCheckBoxCoordAdj.setText("coordinates adj.");

        jCheckBoxAdj.setText("adjective");

        jLabel3.setText("Number of Examples:");

        jComboBoxNrOfEx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "> 0", "> 1", "> 10", "> 100", "> 1000" }));

        jLabel4.setText("Assignment status:");

        jComboBoxAssignmentStat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "any", "relation ", "no relation" }));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 14));
        jLabel6.setText("SEARCH");

        jCheckBoxPattern.setText("in pattern");
        jCheckBoxPattern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPatternActionPerformed(evt);
            }
        });

        jCheckBoxText.setText("in text");
        jCheckBoxText.setEnabled(false);

        jCheckBoxNote.setText("in note");
        jCheckBoxNote.setEnabled(false);

        jTextAreaSomeExamples.setColumns(20);
        jTextAreaSomeExamples.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
        jTextAreaSomeExamples.setRows(5);
        jScrollPane2.setViewportView(jTextAreaSomeExamples);

        jLabel7.setText("Examples:");

        jButtonGetPatterns.setText("Get patterns");
        jButtonGetPatterns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGetPatternsActionPerformed(evt);
            }
        });

        jCheckBoxCoordProper.setText("coordinates proper");

        jCheckBoxRole.setText("role");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jCheckBoxPattern)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jCheckBoxText)
                                            .addGap(12, 12, 12)
                                            .addComponent(jCheckBoxNote)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCheckBoxCoordNoun)
                                                .addComponent(jCheckBoxGen)
                                                .addComponent(jCheckBoxCoordProper)
                                                .addComponent(jCheckBoxLoc))
                                            .addGap(36, 36, 36)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCheckBoxRole)
                                                .addComponent(jCheckBoxCoordAdj)
                                                .addComponent(jCheckBoxAdj)
                                                .addComponent(jCheckBoxSuper)))
                                        .addComponent(jButtonGetPatterns)
                                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                            .addGap(28, 28, 28))
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jComboBoxAssignmentStat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel4))
                                .addComponent(jComboBoxNrOfEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jCheckBoxOther))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonGetPatterns)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxGen)
                            .addComponent(jCheckBoxAdj))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxCoordNoun)
                            .addComponent(jCheckBoxCoordAdj))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxSuper)
                            .addComponent(jCheckBoxCoordProper))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxLoc)
                            .addComponent(jCheckBoxRole))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxOther)
                        .addGap(23, 23, 23)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxNrOfEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxAssignmentStat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxNote)
                            .addComponent(jCheckBoxPattern)
                            .addComponent(jCheckBoxText))
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Find patterns", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(800, 600));

        jTextAreaExamples.setColumns(20);
        jTextAreaExamples.setRows(5);
        jScrollPane3.setViewportView(jTextAreaExamples);

        jComboBoxRelations.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "no relation", "genitive", "adjective", "coord. noun", "coord. proper", "coord. adj.", "role", "superordinate", "location", "other" }));

        jButtonFwd.setText(">");
        jButtonFwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFwdActionPerformed(evt);
            }
        });

        jButtonRew.setText("<");
        jButtonRew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRewActionPerformed(evt);
            }
        });

        jLabelAssignmentStatus.setForeground(new java.awt.Color(0, 0, 255));
        jLabelAssignmentStatus.setText("ASSIGNMENT STATUS");

        jLabelPattern.setFont(new java.awt.Font("Lucida Grande", 1, 16));
        jLabelPattern.setText("PATTERN");

        jLabelNrOfPatterns.setText("NR/NR IN QUEUE");

        jButtonSaveRelation.setFont(new java.awt.Font("Lucida Grande", 1, 13));
        jButtonSaveRelation.setText("Assign relation");
        jButtonSaveRelation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveRelationActionPerformed(evt);
            }
        });

        jLabel5.setText("Note:");

        jButtonSaveNote.setText("Save note");
        jButtonSaveNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveNoteActionPerformed(evt);
            }
        });

        jLabelNrOfEx.setText("NR OF EX.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jButtonRew, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonFwd, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel5))
                                .addComponent(jTextFieldNote, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonSaveNote)
                                .addComponent(jComboBoxRelations, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(22, 22, 22)))
                    .addComponent(jButtonSaveRelation))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabelPattern)
                            .addContainerGap())
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabelNrOfPatterns)
                            .addGap(123, 123, 123)
                            .addComponent(jLabelNrOfEx)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                            .addComponent(jLabelAssignmentStatus)
                            .addGap(44, 44, 44)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNrOfPatterns)
                            .addComponent(jLabelNrOfEx))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPattern)
                            .addComponent(jButtonRew)
                            .addComponent(jButtonFwd)))
                    .addComponent(jLabelAssignmentStatus))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxRelations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButtonSaveRelation, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNote, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSaveNote))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Validate patterns", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveRelationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveRelationActionPerformed
       
        relation = (String)jComboBoxRelations.getSelectedItem();
        PatternInfo patInf = patternsToDisplay.get(position-1);
        
//        String[] patAndNr = jLabelPattern.getText().split("fjöldi");
//        communicator.setRelation(patAndNr[0].trim(), relation);
        patInf.setRelation(relation);
        patInf.setPattern(jLabelPattern.getText());

        communicator.setRelation(patInf);
    }//GEN-LAST:event_jButtonSaveRelationActionPerformed

    private void jButtonRewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRewActionPerformed
        if (position >= 2)
        position -= 2;
        initTextArea();
    }//GEN-LAST:event_jButtonRewActionPerformed

    private void jButtonFwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFwdActionPerformed
        initTextArea();
    }//GEN-LAST:event_jButtonFwdActionPerformed

    private void jButtonSaveNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveNoteActionPerformed
        // TODO add your handling code here:
        PatternInfo patInf = patternsToDisplay.get(position - 1);
        patInf.setNote(jTextFieldNote.getText());
        communicator.setNote(patInf);
    }//GEN-LAST:event_jButtonSaveNoteActionPerformed

    private void jCheckBoxPatternActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPatternActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jCheckBoxPatternActionPerformed

    private void jButtonGetPatternsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGetPatternsActionPerformed
        
        patternsToDisplay.clear();
        getNextPatterns();

        if(!(jTextFieldSearch.getText().equals("")))
            search(jTextFieldSearch.getText());
        else
            getFilterStatus();
        
        	
//        if(patternsToDisplay.isEmpty())
//            patternsToDisplay = communicator.getPatternsByExNumber(100);
        mtm.setData(patternsToDisplay);
        jTextAreaSomeExamples.setText("");
    }//GEN-LAST:event_jButtonGetPatternsActionPerformed
    private void getNextPatterns() {
        patternsToDisplay = communicator.getNonValidatedPatterns(offset);
        offset += 500;
    }
    private void search(String q) {
        if(jCheckBoxPattern.isSelected())
            patternsToDisplay = communicator.search("pattern", q);
        if(jCheckBoxText.isSelected())
            patternsToDisplay = communicator.search("originalstring", q);
        if(jCheckBoxNote.isSelected())
            patternsToDisplay = communicator.search("note", q);

        if(patternsToDisplay.isEmpty()) {
            JOptionPane.showMessageDialog(null, "nothing found matching " + q, "OK", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void getFilterStatus() {
        // get status of every check box and get results from database
        if (jCheckBoxAdj.isSelected()) {
            patternsToDisplay.addAll(communicator.getPatternsByRelation("adjective"));
        }
        if (jCheckBoxCoordAdj.isSelected()) {
            patternsToDisplay.addAll(communicator.getPatternsByRelation("coord. adj."));
        }
        if (jCheckBoxCoordNoun.isSelected()) {
            patternsToDisplay.addAll(communicator.getPatternsByRelation("coord. noun"));
        }
        if (jCheckBoxGen.isSelected()) {
            patternsToDisplay.addAll(communicator.getPatternsByRelation("genitive"));
        }
        if (jCheckBoxLoc.isSelected()) {
            patternsToDisplay.addAll(communicator.getPatternsByRelation("location"));
        }
        if (jCheckBoxOther.isSelected()) {
            patternsToDisplay.addAll(communicator.getPatternsByRelation("other"));
        }
        if (jCheckBoxSuper.isSelected()) {
            patternsToDisplay.addAll(communicator.getPatternsByRelation("superordinate"));
        }
        if (jCheckBoxCoordProper.isSelected()) {
            patternsToDisplay.addAll(communicator.getPatternsByRelation("coord. proper"));
        }
        if (jCheckBoxRole.isSelected()) {
            patternsToDisplay.addAll(communicator.getPatternsByRelation("role"));
        }
        
        

        String assigned = (String)jComboBoxAssignmentStat.getSelectedItem();
        assigned = assigned.trim();

        if(patternsToDisplay.isEmpty()) {

            if(assigned.equals("no relation")) {
                patternsToDisplay = communicator.getPatternsByStatus("no relation");
            }
            if(assigned.equals("relation")) {
                patternsToDisplay = communicator.getPatternsByStatus("relation");
            }
            if(assigned.equals("note")) {
                patternsToDisplay = communicator.getPatternsByStatus("note");
            }
        }

        String nr = (String)jComboBoxNrOfEx.getSelectedItem();
            nr = nr.substring(2);
            Integer i = new Integer(nr);
        if(patternsToDisplay.isEmpty()) {
            patternsToDisplay = communicator.getPatternsByExNumber(i);
        }
        else {
            List<PatternInfo> tmpList = new ArrayList<PatternInfo>();
            for (int j = 0; j < patternsToDisplay.size(); j++) {
                int n = patternsToDisplay.get(j).getNrOfExamples();
                if(n > i)
                    tmpList.add(patternsToDisplay.get(j));
            }
            patternsToDisplay = tmpList;
        }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerificationFrameTabbed().setVisible(true);
            }
        });
    }

    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            
            int firstIndex = lsm.getMinSelectionIndex();
            if(firstIndex < 0)
                firstIndex = 0;
            
            //sort patternsToDisplay by table order:
            List<PatternInfo> orderedList = new ArrayList<PatternInfo>();
            for (int i = 0; i < patternsToDisplay.size(); i++) {
                String pat = (String)jTablePatterns.getValueAt(i, 0);
                for (int j = 0; j < patternsToDisplay.size(); j++) {
                if(pat.equals(patternsToDisplay.get(j).getPattern()))
                    orderedList.add(patternsToDisplay.get(j));
                }
            }
            patternsToDisplay = orderedList;

            PatternInfo patInf = patternsToDisplay.get(firstIndex);

            jTextAreaSomeExamples.setText(patInf.getOriginalString());
            position = firstIndex;
            
            initTextArea();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFwd;
    private javax.swing.JButton jButtonGetPatterns;
    private javax.swing.JButton jButtonRew;
    private javax.swing.JButton jButtonSaveNote;
    private javax.swing.JButton jButtonSaveRelation;
    private javax.swing.JCheckBox jCheckBoxAdj;
    private javax.swing.JCheckBox jCheckBoxCoordAdj;
    private javax.swing.JCheckBox jCheckBoxCoordNoun;
    private javax.swing.JCheckBox jCheckBoxCoordProper;
    private javax.swing.JCheckBox jCheckBoxGen;
    private javax.swing.JCheckBox jCheckBoxLoc;
    private javax.swing.JCheckBox jCheckBoxNote;
    private javax.swing.JCheckBox jCheckBoxOther;
    private javax.swing.JCheckBox jCheckBoxPattern;
    private javax.swing.JCheckBox jCheckBoxRole;
    private javax.swing.JCheckBox jCheckBoxSuper;
    private javax.swing.JCheckBox jCheckBoxText;
    private javax.swing.JComboBox jComboBoxAssignmentStat;
    private javax.swing.JComboBox jComboBoxNrOfEx;
    private javax.swing.JComboBox jComboBoxRelations;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelAssignmentStatus;
    private javax.swing.JLabel jLabelNrOfEx;
    private javax.swing.JLabel jLabelNrOfPatterns;
    private javax.swing.JLabel jLabelPattern;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTablePatterns;
    private javax.swing.JTextArea jTextAreaExamples;
    private javax.swing.JTextArea jTextAreaSomeExamples;
    private javax.swing.JTextField jTextFieldNote;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

}

