package com.dina.studentmaster.view;

import com.dina.studentmaster.dao.StudentDAO;
import com.dina.studentmaster.model.Student;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentFrame extends JFrame {
    private final JTextField id=new JTextField(), name=new JTextField(), major=new JTextField(), email=new JTextField(), phone=new JTextField(), address=new JTextField();
    private final DefaultTableModel model=new DefaultTableModel(new String[]{"Student ID","Name","Major","Email","Phone","Address"},0){ public boolean isCellEditable(int r,int c){return false;} };
    private final JTable table=new JTable(model);
    private final StudentDAO dao=new StudentDAO();

    public StudentFrame(){
        super("Student Master"); setDefaultCloseOperation(EXIT_ON_CLOSE); setSize(1120,700); setMinimumSize(new Dimension(950,600)); setLocationRelativeTo(null);
        BackgroundPanel root=new BackgroundPanel(); root.setBorder(BorderFactory.createEmptyBorder(18,18,18,18)); setContentPane(root);
        JLabel title=new JLabel("STUDENT MASTER",SwingConstants.CENTER); title.setFont(new Font("SansSerif",Font.BOLD,30)); title.setForeground(new Color(10,53,92));
        title.setOpaque(true); title.setBackground(new Color(255,255,255,220)); title.setBorder(BorderFactory.createEmptyBorder(12,10,12,10)); root.add(title,BorderLayout.NORTH);

        JPanel form=new JPanel(new GridLayout(6,2,10,10)); form.setBackground(new Color(255,255,255,230)); form.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Student Form"),BorderFactory.createEmptyBorder(10,10,10,10)));
        addField(form,"Student ID",id); addField(form,"Name",name); addField(form,"Major",major); addField(form,"Email",email); addField(form,"Phone",phone); addField(form,"Address",address);

        table.setRowHeight(28); table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); table.getTableHeader().setBackground(new Color(11,95,126)); table.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scroll=new JScrollPane(table); scroll.setBorder(BorderFactory.createTitledBorder("Student Data"));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,form,scroll); split.setResizeWeight(.30); split.setDividerLocation(340); split.setOpaque(false); root.add(split,BorderLayout.CENTER);

        JPanel actions=new JPanel(new FlowLayout(FlowLayout.CENTER,12,8)); actions.setBackground(new Color(255,255,255,225));
        JButton add=button("Add",new Color(34,139,94)), update=button("Update",new Color(22,112,168)), delete=button("Delete",new Color(194,57,52)), clear=button("Clear",new Color(100,116,139)), report=button("Print Report",new Color(96,72,168));
        actions.add(add);actions.add(update);actions.add(delete);actions.add(clear);actions.add(report);root.add(actions,BorderLayout.SOUTH);

        add.addActionListener(e->create()); update.addActionListener(e->update()); delete.addActionListener(e->delete()); clear.addActionListener(e->clear()); report.addActionListener(e->new ReportRangeDialog(this).setVisible(true));
        table.getSelectionModel().addListSelectionListener(e->{if(!e.getValueIsAdjusting()&&table.getSelectedRow()>=0) select();});
        load();
    }
    private void addField(JPanel p,String label,JTextField field){p.add(new JLabel(label));p.add(field);}
    private JButton button(String t,Color c){JButton b=new JButton(t);b.setBackground(c);b.setForeground(Color.WHITE);b.setFocusPainted(false);b.setFont(new Font("SansSerif",Font.BOLD,13));return b;}
    private Student value(){
        if(id.getText().isBlank()||name.getText().isBlank()||major.getText().isBlank()||email.getText().isBlank()) throw new IllegalArgumentException("Student ID, Name, Major, dan Email wajib diisi.");
        return new Student(id.getText().trim(),name.getText().trim(),major.getText().trim(),email.getText().trim(),phone.getText().trim(),address.getText().trim());
    }
    private void create(){try{dao.insert(value());load();clear();ok("Data berhasil ditambahkan.");}catch(Exception e){err(e);}}
    private void update(){try{dao.update(value());load();clear();ok("Data berhasil diperbarui.");}catch(Exception e){err(e);}}
    private void delete(){if(id.getText().isBlank()){ok("Pilih data yang akan dihapus.");return;} if(JOptionPane.showConfirmDialog(this,"Hapus data "+id.getText()+"?","Confirm",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)try{dao.delete(id.getText());load();clear();ok("Data berhasil dihapus.");}catch(Exception e){err(e);}}
    private void load(){try{model.setRowCount(0);for(Student s:dao.findAll())model.addRow(new Object[]{s.getStudentId(),s.getStudentName(),s.getMajor(),s.getEmail(),s.getPhone(),s.getAddress()});}catch(Exception e){err(e);}}
    private void select(){int r=table.getSelectedRow();id.setText(model.getValueAt(r,0).toString());name.setText(model.getValueAt(r,1).toString());major.setText(model.getValueAt(r,2).toString());email.setText(model.getValueAt(r,3).toString());phone.setText(String.valueOf(model.getValueAt(r,4)));address.setText(String.valueOf(model.getValueAt(r,5)));id.setEditable(false);}
    private void clear(){for(JTextField f:new JTextField[]{id,name,major,email,phone,address})f.setText("");id.setEditable(true);table.clearSelection();id.requestFocus();}
    private void ok(String m){JOptionPane.showMessageDialog(this,m);}
    private void err(Exception e){JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}
}
