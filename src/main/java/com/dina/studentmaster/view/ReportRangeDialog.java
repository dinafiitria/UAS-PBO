package com.dina.studentmaster.view;

import com.dina.studentmaster.config.DatabaseConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ReportRangeDialog extends JDialog {
    private final JTextField start = new JTextField(16);
    private final JTextField end = new JTextField(16);

    public ReportRangeDialog(JFrame owner) {
        super(owner,"Print Student Report",true);
        setSize(450,250); setLocationRelativeTo(owner);
        JPanel form = new JPanel(new GridBagLayout()); form.setBorder(BorderFactory.createEmptyBorder(18,18,18,18));
        GridBagConstraints g = new GridBagConstraints(); g.insets=new Insets(7,7,7,7); g.fill=GridBagConstraints.HORIZONTAL;
        g.gridx=0;g.gridy=0;form.add(new JLabel("Student ID awal"),g); g.gridx=1;form.add(start,g);
        g.gridx=0;g.gridy=1;form.add(new JLabel("Student ID akhir"),g); g.gridx=1;form.add(end,g);
        JButton preview=new JButton("Preview"); JButton pdf=new JButton("Export PDF");
        JPanel actions=new JPanel(); actions.add(preview); actions.add(pdf);
        g.gridx=0;g.gridy=2;g.gridwidth=2;form.add(actions,g); add(form);
        preview.addActionListener(e -> run(false)); pdf.addActionListener(e -> run(true));
    }

    private void run(boolean export) {
        if(start.getText().isBlank()||end.getText().isBlank()){ JOptionPane.showMessageDialog(this,"Range Student ID wajib diisi."); return; }
        try (Connection c=DatabaseConnection.getConnection(); InputStream jrxml=getClass().getResourceAsStream("/reports/StudentReport.jrxml")) {
            if(jrxml==null) throw new FileNotFoundException("StudentReport.jrxml tidak ditemukan");
            Map<String,Object> params=new HashMap<>();
            params.put("START_ID",start.getText().trim()); params.put("END_ID",end.getText().trim());
            params.put("LOGO_STREAM",getClass().getResourceAsStream("/images/logo.png"));
            JasperPrint print=JasperFillManager.fillReport(JasperCompileManager.compileReport(jrxml),params,c);
            if(export){
                JFileChooser chooser=new JFileChooser(); chooser.setSelectedFile(new File("student-report.pdf"));
                if(chooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
                    String p=chooser.getSelectedFile().getAbsolutePath(); if(!p.toLowerCase().endsWith(".pdf")) p += ".pdf";
                    JasperExportManager.exportReportToPdfFile(print,p); JOptionPane.showMessageDialog(this,"PDF berhasil dibuat.");
                }
            } else JasperViewer.viewReport(print,false);
        } catch(Exception ex){ JOptionPane.showMessageDialog(this,ex.getMessage(),"Report Error",JOptionPane.ERROR_MESSAGE); }
    }
}
