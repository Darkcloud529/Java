import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Student extends JPanel {
	// TextField: txt, Button: btn, RadioButton: rdo
	JTextField txtId=null;
	JTextField txtName=null;
	JTextField txtDepartment=null;
	JTextField txtAddress=null;
	JTextArea taList=null;
	
	// CRUD
	JButton btnInsert=null; //��� Create
	JButton btnSelect=null; //��� Read
	JButton btnUpdate=null; //���� Update
	JButton btnDelete=null; //���� Delete
	
	//search
	JButton btnSearch=null; // �й����� �л� �˻�
	
	// ���̺�.��
	DefaultTableModel model=null; // ���̺��� ������
	JTable table=null;
	public Student() {
		
		this.setLayout(new FlowLayout());
		
		this.add(new JLabel("�й�"));
		this.txtId=new JTextField(14);
		this.add(this.txtId);
		
		this.btnSearch=new JButton("�˻�");
		this.add(this.btnSearch);
		this.btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					//oracle jdbc����̹� �ε�
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
					//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","����ID","����PWD");
					// ����
					System.out.println("����Ϸ�");
					Statement stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery("select * from student where id='"+txtId.getText()+"'");
					
					//JTable�ʱ�ȭ
					model.setNumRows(0);
					while(rs.next()) { // rs.next() �� �پ� �о ����
						String[] row = new String[3];
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						model.addRow(row); // model�� ���߰�
						
						//�˻� ���� �� �̸�, �а��� ä������
						txtId.setText(rs.getString("id"));
						txtName.setText(rs.getString("name"));
						txtDepartment.setText(rs.getString("dept"));
					}
					rs.close();
					stmt.close();
					conn.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {}
				
			}});
		
		this.add(new JLabel("�̸�"));
		this.txtName=new JTextField(20);
		this.add(this.txtName);
		
		this.add(new JLabel("�а�"));
		this.txtDepartment=new JTextField(20);
		this.add(this.txtDepartment);
		
		this.add(new JLabel("�ּ�"));
		this.txtAddress=new JTextField(20);
		this.add(this.txtAddress);
		
		//���̺� ����
		String[] colname= {"�й�","�̸�","�а�"};
		this.model=new DefaultTableModel(colname,0);
		this.table = new JTable(model); // model�� table ���ε�
		table.setPreferredScrollableViewportSize(new Dimension(250,270)); // ���̺� ũ��
		this.add(this.table);
		JScrollPane sp=new JScrollPane(table); // ��ũ�� ����
		this.add(sp);
		
		this.table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) { // ���̺� ���콺�� Ŭ���ϸ� Ŭ���� ���� ������ �о���� �Լ�
				table=(JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				txtId.setText((String)model.getValueAt(table.getSelectedRow(), 0)); // �й�
				txtName.setText((String)model.getValueAt(table.getSelectedRow(), 1)); // �̸�
				txtDepartment.setText((String)model.getValueAt(table.getSelectedRow(), 2)); // �а�
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
	
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}});
		
		this.btnInsert=new JButton("���");
		this.add(btnInsert);
		this.btnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("���");
				//Oracle�� �����ؼ� insert�ϴ� �۾� �ڵ�
				try {
					//oracle jdbc����̹� �ε�
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
					//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","����ID","����PWD");
					// ����
					System.out.println("����Ϸ�");
					Statement stmt=conn.createStatement();
					// insert
					// stmt.executeUpdate("insert into student values('1234567','�հ�','�����а�')");
					stmt.executeUpdate("insert into student values('"+txtId.getText()+"','"+txtName.getText()+"','"+txtDepartment.getText()+"')");
					
					ResultSet rs=stmt.executeQuery("select * from student");
					//JTable�ʱ�ȭ
					model.setNumRows(0);
					while(rs.next()) {
						//taList.append(rs.getString("id")+"\t"+rs.getString("name")+"\t"+rs.getString("dept")+"\n");
						String[] row = new String[3];
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						model.addRow(row); // model�� ���߰�
					}
					rs.close();
					stmt.close();
					conn.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {}
				
			}});
		
		this.btnSelect=new JButton("���");
		this.add(btnSelect);
		this.btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//oracle jdbc����̹� �ε�
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
					//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","����ID","����PWD");
					// ����
					System.out.println("����Ϸ�");
					
					Statement stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery("select * from student");
					
					//JTable�ʱ�ȭ
					model.setNumRows(0);
					
					while(rs.next()) {
						String[] row = new String[3];
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						model.addRow(row); // model�� ���߰�
					}
					rs.close();
					stmt.close();
					conn.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {}
				
			}});
		
		this.btnUpdate=new JButton("����");
		this.add(btnUpdate);
		this.btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					//oracle jdbc����̹� �ε�
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
					//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","����ID","����PWD");
					// ����
					System.out.println("����Ϸ�");
					Statement stmt=conn.createStatement();
				
					// update
					// stmt.executeUpdate("update student set name ='ȫ�浿' where id='1234567'");
					stmt.executeUpdate("update student set name ='"+txtName.getText()+"', dept='"+txtDepartment.getText()+"' where id='"+txtId.getText()+"'");
					
					ResultSet rs=stmt.executeQuery("select * from student where id='"+txtId.getText()+"'"); // ������ �ǿ� ���ؼ��� ���
					//JTable�ʱ�ȭ
					model.setNumRows(0);
					while(rs.next()) {
						
						String[] row = new String[3];
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						model.addRow(row); // model�� ���߰�
					}
					rs.close();
					stmt.close();
					conn.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {}
			}});
		
		this.btnDelete=new JButton("����");
		this.btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "confirm", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					// Oralce����. ����(delete)ó��.
					try {
						//oracle jdbc����̹� �ε�
						Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
						//Connection
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
						//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","����ID","����PWD");
						// ����
						System.out.println("����Ϸ�");
						Statement stmt=conn.createStatement();
						
						// delete
						// stmt.executeUpdate("delete from student where id ='1234567'");
						stmt.executeUpdate("delete from student where id ='"+txtId.getText()+"'");
						
						
						
						ResultSet rs=stmt.executeQuery("select * from student"); // ������ �� ��ü ��� �����ֱ� ��, ������ ���� ���� ��� �ð��� �ɸ�
						//JTable�ʱ�ȭ
						model.setNumRows(0);
						while(rs.next()) {
							//taList.append(rs.getString("id")+"\t"+rs.getString("name")+"\t"+rs.getString("dept")+"\n");

							String[] row = new String[3];
							row[0]=rs.getString("id");
							row[1]=rs.getString("name");
							row[2]=rs.getString("dept");
							model.addRow(row); // model�� ���߰�
						}
						// ������ ������ ���Զ����� ����(�ʱ�ȭ)
						txtId.setText("");
						txtName.setText("");
						txtDepartment.setText("");
						
						rs.close();
						stmt.close();
						conn.close();
					}catch(Exception e1) {
						e1.printStackTrace();
					}finally {}
					//����ó���� �޽������
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�", "message", JOptionPane.INFORMATION_MESSAGE);
				}
			}});
		this.add(btnDelete);
		
		this.setSize(280, 500);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Student();
	}

}

