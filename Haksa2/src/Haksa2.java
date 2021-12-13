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
	
	// �޴�
	JMenuBar mb = new JMenuBar();
	JMenu menu1 = new JMenu("�л�����");
	JMenu menu2 = new JMenu("��������");
	JMenuItem miStudent = new JMenuItem("�л�����");
	JMenuItem miBookRent= new JMenuItem("��������");
	
	//�г�
	JPanel panel; // �޴��� ȭ���� ����� �Ǵ� �г�
	public Haksa2() {
		this.setTitle("�л����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.menu1.add(this.miStudent);
		this.menu2.add(this.miBookRent);
		this.mb.add(this.menu1);
		this.mb.add(this.menu2);
		this.setJMenuBar(this.mb);
		
		this.miStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {			
				 panel.removeAll(); //���������Ʈ ����
				 panel.revalidate(); //�ٽ� Ȱ��ȭ
				 panel.repaint();    //�ٽ� �׸���
				 panel.add(new Student()); //ȭ�� ����.
				 panel.setLayout(null);//���̾ƿ��������
			}});
		
		this.miBookRent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); //���������Ʈ ����
				panel.revalidate(); //�ٽ� Ȱ��ȭ
				panel.repaint();    //�ٽ� �׸���
				panel.add(new BookRent()); //ȭ�� ����.
				panel.setLayout(null);//���̾ƿ��������
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
