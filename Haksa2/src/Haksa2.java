import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Haksa2 extends JFrame {
	
	// 메뉴
	JMenuBar mb = new JMenuBar();
	JMenu menu1 = new JMenu("학생관리");
	JMenu menu2 = new JMenu("도서관리");
	JMenuItem miStudent = new JMenuItem("학생정보");
	JMenuItem miBookRent= new JMenuItem("도서대출");
	
	//패널
	JPanel panel; // 메뉴별 화면이 출력이 되는 패널
	public Haksa2() {
		this.setTitle("학사관리");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.menu1.add(this.miStudent);
		this.menu2.add(this.miBookRent);
		this.mb.add(this.menu1);
		this.mb.add(this.menu2);
		this.setJMenuBar(this.mb);
		
		this.miStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {			
				 panel.removeAll(); //모든컴포넌트 삭제
				 panel.revalidate(); //다시 활성화
				 panel.repaint();    //다시 그리기
				 panel.add(new Student()); //화면 생성.
				 panel.setLayout(null);//레이아웃적용안함
			}});
		
		this.miBookRent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); //모든컴포넌트 삭제
				panel.revalidate(); //다시 활성화
				panel.repaint();    //다시 그리기
				panel.add(new BookRent()); //화면 생성.
				panel.setLayout(null);//레이아웃적용안함
			}});
		
		panel = new JPanel();
		this.add(panel);
		
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Haksa2();
	}

}
