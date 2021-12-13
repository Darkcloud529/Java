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
	JButton btnInsert=null; //등록 Create
	JButton btnSelect=null; //목록 Read
	JButton btnUpdate=null; //수정 Update
	JButton btnDelete=null; //삭제 Delete
	
	//search
	JButton btnSearch=null; // 학번으로 학생 검색
	
	// 테이블.모델
	DefaultTableModel model=null; // 테이블의 데이터
	JTable table=null;
	public Student() {
		
		this.setLayout(new FlowLayout());
		
		this.add(new JLabel("학번"));
		this.txtId=new JTextField(14);
		this.add(this.txtId);
		
		this.btnSearch=new JButton("검색");
		this.add(this.btnSearch);
		this.btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					//oracle jdbc드라이버 로드
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
					//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","계정ID","계정PWD");
					// 연결
					System.out.println("연결완료");
					Statement stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery("select * from student where id='"+txtId.getText()+"'");
					
					//JTable초기화
					model.setNumRows(0);
					while(rs.next()) { // rs.next() 한 줄씩 읽어서 실행
						String[] row = new String[3];
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						model.addRow(row); // model에 행추가
						
						//검색 실행 후 이름, 학과도 채워진다
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
		
		this.add(new JLabel("이름"));
		this.txtName=new JTextField(20);
		this.add(this.txtName);
		
		this.add(new JLabel("학과"));
		this.txtDepartment=new JTextField(20);
		this.add(this.txtDepartment);
		
		this.add(new JLabel("주소"));
		this.txtAddress=new JTextField(20);
		this.add(this.txtAddress);
		
		//테이블 생성
		String[] colname= {"학번","이름","학과"};
		this.model=new DefaultTableModel(colname,0);
		this.table = new JTable(model); // model과 table 바인딩
		table.setPreferredScrollableViewportSize(new Dimension(250,270)); // 테이블 크기
		this.add(this.table);
		JScrollPane sp=new JScrollPane(table); // 스크롤 구현
		this.add(sp);
		
		this.table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) { // 테이블에 마우스를 클릭하면 클릭한 곳에 정보를 읽어오는 함수
				table=(JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				txtId.setText((String)model.getValueAt(table.getSelectedRow(), 0)); // 학번
				txtName.setText((String)model.getValueAt(table.getSelectedRow(), 1)); // 이름
				txtDepartment.setText((String)model.getValueAt(table.getSelectedRow(), 2)); // 학과
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
		
		this.btnInsert=new JButton("등록");
		this.add(btnInsert);
		this.btnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("등록");
				//Oracle과 연동해서 insert하는 작업 코딩
				try {
					//oracle jdbc드라이버 로드
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
					//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","계정ID","계정PWD");
					// 연결
					System.out.println("연결완료");
					Statement stmt=conn.createStatement();
					// insert
					// stmt.executeUpdate("insert into student values('1234567','왕건','국문학과')");
					stmt.executeUpdate("insert into student values('"+txtId.getText()+"','"+txtName.getText()+"','"+txtDepartment.getText()+"')");
					
					ResultSet rs=stmt.executeQuery("select * from student");
					//JTable초기화
					model.setNumRows(0);
					while(rs.next()) {
						//taList.append(rs.getString("id")+"\t"+rs.getString("name")+"\t"+rs.getString("dept")+"\n");
						String[] row = new String[3];
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						model.addRow(row); // model에 행추가
					}
					rs.close();
					stmt.close();
					conn.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {}
				
			}});
		
		this.btnSelect=new JButton("목록");
		this.add(btnSelect);
		this.btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//oracle jdbc드라이버 로드
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
					//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","계정ID","계정PWD");
					// 연결
					System.out.println("연결완료");
					
					Statement stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery("select * from student");
					
					//JTable초기화
					model.setNumRows(0);
					
					while(rs.next()) {
						String[] row = new String[3];
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						model.addRow(row); // model에 행추가
					}
					rs.close();
					stmt.close();
					conn.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {}
				
			}});
		
		this.btnUpdate=new JButton("수정");
		this.add(btnUpdate);
		this.btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					//oracle jdbc드라이버 로드
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
					//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","계정ID","계정PWD");
					// 연결
					System.out.println("연결완료");
					Statement stmt=conn.createStatement();
				
					// update
					// stmt.executeUpdate("update student set name ='홍길동' where id='1234567'");
					stmt.executeUpdate("update student set name ='"+txtName.getText()+"', dept='"+txtDepartment.getText()+"' where id='"+txtId.getText()+"'");
					
					ResultSet rs=stmt.executeQuery("select * from student where id='"+txtId.getText()+"'"); // 수정한 건에 대해서만 출력
					//JTable초기화
					model.setNumRows(0);
					while(rs.next()) {
						
						String[] row = new String[3];
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						model.addRow(row); // model에 행추가
					}
					rs.close();
					stmt.close();
					conn.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {}
			}});
		
		this.btnDelete=new JButton("삭제");
		this.btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					// Oralce연동. 삭제(delete)처리.
					try {
						//oracle jdbc드라이버 로드
						Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
						//Connection
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
						//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","계정ID","계정PWD");
						// 연결
						System.out.println("연결완료");
						Statement stmt=conn.createStatement();
						
						// delete
						// stmt.executeUpdate("delete from student where id ='1234567'");
						stmt.executeUpdate("delete from student where id ='"+txtId.getText()+"'");
						
						
						
						ResultSet rs=stmt.executeQuery("select * from student"); // 삭제된 후 전체 목록 보여주기 단, 데이터 양이 많은 경우 시간이 걸림
						//JTable초기화
						model.setNumRows(0);
						while(rs.next()) {
							//taList.append(rs.getString("id")+"\t"+rs.getString("name")+"\t"+rs.getString("dept")+"\n");

							String[] row = new String[3];
							row[0]=rs.getString("id");
							row[1]=rs.getString("name");
							row[2]=rs.getString("dept");
							model.addRow(row); // model에 행추가
						}
						// 삭제한 내용을 기입란에서 삭제(초기화)
						txtId.setText("");
						txtName.setText("");
						txtDepartment.setText("");
						
						rs.close();
						stmt.close();
						conn.close();
					}catch(Exception e1) {
						e1.printStackTrace();
					}finally {}
					//삭제처리후 메시지출력
					JOptionPane.showMessageDialog(null, "삭제되었습니다", "message", JOptionPane.INFORMATION_MESSAGE);
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

