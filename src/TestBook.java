import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class TestBookDemo extends JFrame implements ActionListener, ListSelectionListener
{
	BookCollection book;
	//------------------------------------------------------------------------------
	// ����� UI�� ���� �ʿ��� ������ ���� 
	//------------------------------------------------------------------------------
	private JPanel panel1, panel1_1, panel1_2, panel1_3, panel2, panel2_1, panel2_2, panel2_3, panel3, panel3_1_1, panel3_1, panel3_2, panel4;
	private JFileChooser fc;
	
	
	//private JLabel labelTitle;
	private JMenuBar menuBar;
	private JMenu fileMenu, settingMenu;
	private JMenuItem openMenuItem, addMenuItem, saveMenuItem, goalMenuItem, bookMenuItem;
	private JLabel lbMainTitle, lbGoal, lbTotal, lbPercent;
	private JTextField tfGoal, tfTotal, tfPercent;
	private int numGoal=10, numTotal=0, percent, num, editState=0;
	private JLabel lbTitle, lbAuthor, lbImage, lbPage, lbStartDate, lbEndDate, lbRate, lbQuote, lbReflection;
	private JTextField tfTitle, tfAuthor, tfImage, tfPage, tfStartDate, tfEndDate;
	private Calendar calendar;
	private Date value, start, end;
	//SpinnerDateModel dateModel1,  dateModel2;
	//private JSpinner spStartDate, spEndDate;
	private JSlider slRate;
	private JTextArea taQuote, taReflection, taList, taDisplay;
	private JScrollPane scrollPane1, scrollPane2, scroller1, scroller2;
	private JButton btnAdd, btnEdit, btnDelete, btnShowAll;
	private DefaultListModel listM;
	private JList list;
	private ImageIcon img; 
	
	//------------------------------------------------------------------------------
	//������ - GUI�� �����Ѵ�.
	//------------------------------------------------------------------------------
	public TestBookDemo() 
	{
		book=new BookCollection();
		//------------------------------------------------------------------------------
		// ����� ȭ�鿡 ������Ʈ�� �����Ͽ� ��ġ
		// �� ������Ʈ ������ �ʿ��� ������ �߰�
		//------------------------------------------------------------------------------
		fc = new JFileChooser();
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("����");
		openMenuItem = new JMenuItem("�ҷ�����");
		openMenuItem.addActionListener(this);
		fileMenu.add(openMenuItem);
		addMenuItem = new JMenuItem("�߰��ϱ�");
		addMenuItem.addActionListener(this);
		fileMenu.add(addMenuItem);
		saveMenuItem = new JMenuItem("�����ϱ�");
		saveMenuItem.addActionListener(this);
		fileMenu.add(saveMenuItem);
		
		settingMenu = new JMenu("����");
		goalMenuItem = new JMenuItem("��ǥ����");
		goalMenuItem.addActionListener(this);
		settingMenu.add(goalMenuItem);
		bookMenuItem = new JMenuItem("������?");
		bookMenuItem.addActionListener(this);
		settingMenu.add(bookMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(settingMenu);

		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1_1 = new JPanel();
		panel1_2 = new JPanel();
		panel1_3 = new JPanel();
		//panel1_2.setLayout(new BoxLayout(panel1_2, BoxLayout.Y_AXIS));
		panel1_3.setLayout(new GridLayout(4, 2, 0, 5));
		ImageIcon image1 = new ImageIcon("book.jpg");
		JLabel lbbook = new JLabel();
		lbbook.setIcon(image1);
		ImageIcon image2 = new ImageIcon("title.png");
		lbMainTitle = new JLabel();
		lbMainTitle.setIcon(image2);
		numGoal=10;
		numTotal=book.getBookCount();
		percent = numTotal*100/numGoal;
		JLabel space1 = new JLabel(" ");
		JLabel space2 = new JLabel(" ");
		
		lbGoal = new JLabel("������ ��ǥ:");
		tfGoal = new JTextField(3);
		tfGoal.setEditable(false);
		tfGoal.setText(Integer.toString(numGoal));
		lbTotal = new JLabel("���� ���� å:");
		tfTotal = new JTextField(3);
		numTotal = book.getBookCount();
		tfTotal.setText(Integer.toString(numTotal));
		tfTotal.setEditable(false);
		lbPercent = new JLabel("%  �޼�");
		tfPercent = new JTextField(3);
		tfPercent.setText(Integer.toString(numTotal*100/numGoal));
		tfPercent.setEditable(false);
		
		panel1_1.add(lbbook);
		panel1_2.add(lbMainTitle);
		panel1_3.add(space1);
		panel1_3.add(space2);
		panel1_3.add(lbGoal);
		panel1_3.add(tfGoal);
		panel1_3.add(lbTotal);
		panel1_3.add(tfTotal);
		panel1_3.add(tfPercent);
		panel1_3.add(lbPercent);
		panel1.add(panel1_1, BorderLayout.WEST);
		panel1.add(panel1_2, BorderLayout.CENTER);
		panel1.add(panel1_3, BorderLayout.EAST);
		
		
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.setBorder(new TitledBorder("�߰�/����"));
		panel2_1 = new JPanel();
		panel2_1.setLayout(new GridLayout(6, 2, 0, 5));
		panel2_2 = new JPanel();
		panel2_2.setLayout(new FlowLayout());
		panel2_3 = new JPanel();
		panel2_3.setLayout(new GridLayout(2, 2, 0, 5));
		
		//Calendar, Date ��ü ����
		/*calendar = Calendar.getInstance();
		value = calendar.getTime();
		calendar.add(Calendar.YEAR, -50);
		start = calendar.getTime();
		calendar.add(Calendar.YEAR, 50);
		end = calendar.getTime();
		dateModel1 = new SpinnerDateModel(value, start, end, Calendar.DAY_OF_MONTH);
		dateModel2 = new SpinnerDateModel(value, start, end, Calendar.DAY_OF_MONTH);*/
		
		//panel2_1
		lbTitle = new JLabel("����");
		tfTitle = new JTextField(13);
		lbAuthor = new JLabel("�۰�");
		tfAuthor = new JTextField(10);
		lbImage = new JLabel("�̹���");
		tfImage = new JTextField(10);
		lbPage = new JLabel("��������");
		tfPage = new JTextField(10);
		
		lbStartDate = new JLabel("�б� ������ ��¥");
		tfStartDate = new JTextField(10);
		//spStartDate = new JSpinner(dateModel1);
		//spStartDate.setEditor(new JSpinner.DateEditor(spStartDate, "yyyy/MM/dd"));
		lbEndDate = new JLabel("�� ���� ��¥");
		tfEndDate = new JTextField(10);
		//spEndDate = new JSpinner(dateModel2);
		//spEndDate.setEditor(new JSpinner.DateEditor(spEndDate, "yyyy/MM/dd"));
		
		//panel2_2
		lbRate = new JLabel("����");
		slRate = new JSlider(0, 5, 3);
		slRate.setMajorTickSpacing(1);
		slRate.setPaintTicks(true);
		slRate.setPaintLabels(true);
		
		//panel2_3
		lbQuote = new JLabel("������ ��� ����");
		taQuote = new JTextArea(7, 10);
		//taQuote.setWrapStyleWord(true);
		scrollPane1 = new JScrollPane(taQuote);
		//scrollPane1.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		
		lbReflection = new JLabel("����");
		taReflection = new JTextArea(7, 10);
		scrollPane2 = new JScrollPane(taReflection);
		//scrollPane2.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		
		panel2_1.add(lbTitle);
		panel2_1.add(tfTitle);
		panel2_1.add(lbAuthor);
		panel2_1.add(tfAuthor);
		panel2_1.add(lbImage);
		panel2_1.add(tfImage);
		panel2_1.add(lbPage);
		panel2_1.add(tfPage);
		panel2_1.add(lbStartDate);
		panel2_1.add(tfStartDate);
		panel2_1.add(lbEndDate);
		panel2_1.add(tfEndDate);
		panel2_2.add(lbRate);
		panel2_2.add(slRate);
		panel2_3.add(lbQuote);
		panel2_3.add(scrollPane1);
		panel2_3.add(lbReflection);
		panel2_3.add(scrollPane2);

		panel2.add(panel2_1);
		panel2.add(panel2_2);
		panel2.add(panel2_3);
		
		
		panel3 = new JPanel();
		panel3_1 = new JPanel();
		panel3_1_1 = new JPanel();
		panel3_1.setLayout(new BorderLayout());
		panel3_1_1.setLayout(new GridLayout(4, 1, 2, 10));
		panel3_2 = new JPanel();
		panel3.setBorder(new TitledBorder("���� å ���"));
		
		btnAdd = new JButton("�߰�>>");
		btnEdit = new JButton("<<����");
		btnDelete = new JButton("����");
		btnShowAll = new JButton("��ü����");
		listM = new DefaultListModel();
		list = new JList();
		list.setModel(listM);

		//list.setBorder(BorderFactory.createLineBorder(Color.black, 0));
		scroller1 = new JScrollPane(list);
		scroller1.setPreferredSize(new Dimension(150, 500));
		
		
		btnAdd.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);
		btnShowAll.addActionListener(this);
		list.addListSelectionListener(this);
		
		panel3_1_1.add(btnAdd);
		panel3_1_1.add(btnEdit);
		panel3_1_1.add(btnDelete);
		panel3_1_1.add(btnShowAll);
		panel3_1.add(panel3_1_1, BorderLayout.CENTER);
		panel3_2.add(scroller1);
		
		panel3.add(panel3_1);
		panel3.add(panel3_2);
		
		
		panel4 = new JPanel();
		panel4.setBorder(new TitledBorder("�������"));
		img = new ImageIcon();
		JLabel image = new JLabel(img);
		taDisplay = new JTextArea(28, 22);
		scroller2 = new JScrollPane(taDisplay);
		scroller2.setPreferredSize(new Dimension(250, 500));
		panel4.add(image);
		panel4.add(scroller2);

		//------------------------------------------------------------------------------
		//	Frame �Ӽ�
		//------------------------------------------------------------------------------
		Container c = getContentPane();
		
		setLayout(new BorderLayout());
		c.add(panel1, BorderLayout.NORTH);
		c.add(panel2, BorderLayout.WEST);
		c.add(panel3, BorderLayout.CENTER);
		c.add(panel4, BorderLayout.EAST);
		
		setJMenuBar(menuBar);
		setTitle("Book Diary");
		setSize(1020, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void setImage(String imgpath){
		panel4.removeAll();
		img = new ImageIcon(imgpath);
		JLabel image = new JLabel(img);
		panel4.add(image);
		panel4.add(scroller2);
	}
	//------------------------------------------------------------------------------
	// ������ �������̽��� ���� �� �޼ҵ� ����
	//� �̺�Ʈ�� �߻��ߴ����� ���� ������ ����� �����Ѵ�.
	//------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == btnAdd){
			//�� �������� ����(å��ü ����), ������ ����Ʈ�� �߰�, (Integer.parseInt())
			// �Է������� �Է¹��� ������ ������ Book ��ü ����
			Book m = new Book(tfTitle.getText(),tfAuthor.getText(),tfImage.getText(),Integer.parseInt(tfPage.getText()),
								tfStartDate.getText(),tfEndDate.getText(), slRate.getValue(), taQuote.getText(),taReflection.getText());
			
			//������ Book ��ü�� BookCollection ��ü(book)�� �߰�
			book.addBook(m);
			
			//����Ʈ�� ��ȭ�������� ��� �߰�
			listM.addElement(tfTitle.getText());
			
			//�Է��� �ʱ�ȭ
			tfTitle.setText("");
			tfAuthor.setText("");
			tfImage.setText("");
			tfPage.setText("");
			tfStartDate.setText("");
			tfEndDate.setText("");
			slRate.setValue(3);
			taQuote.setText("");
			taReflection.setText("");

			num = book.getBookCount();
			tfTotal.setText(Integer.toString(num));
			tfPercent.setText(Integer.toString(num*100/numGoal));
			
			taDisplay.setText(book.toString(num-1)); //å ��ü toString()
			setImage(m.getImage());
		}
		if(event.getSource() == btnEdit){
			//�����Ҷ�
			//��Ͽ��� ���õ� �ε��� ���� ������ �ͼ�
			//BookCollection���� �� �ε����� �ش��ϴ� Book ��ü ��ȯ�޾ƿ�
			Book m = book.getBookAt(list.getSelectedIndex());
			if(editState==0){
				btnEdit.setText("����>>");
				editState=1;
			
				tfTitle.setText(m.getTitle());
				tfAuthor.setText(m.getAuthor());
				tfImage.setText(m.getImage());
				tfPage.setText(Integer.toString(m.getPage()));
				tfStartDate.setText(m.getStartDate());
				tfEndDate.setText(m.getEndDate());
				slRate.setValue(m.getRate());
				taQuote.setText(m.getQuote());
				taReflection.setText(m.getReflection());
			}
			else{
				//�����Ϸ��϶�
				btnEdit.setText("<<����");
				editState=0;
				
				m.setTitle(tfTitle.getText());
				m.setAuthor(tfAuthor.getText());
				m.setImage(tfImage.getText());
				m.setPage(Integer.parseInt(tfPage.getText()));
				m.setStartDate(tfStartDate.getText());
				m.setEndDate(tfEndDate.getText());
				m.setRate(slRate.getValue());
				m.setQuote(taQuote.getText());
				m.setReflection(taReflection.getText());
				
				//�Է��� �ʱ�ȭ
				tfTitle.setText("");
				tfAuthor.setText("");
				tfImage.setText("");
				tfPage.setText("");
				tfStartDate.setText("");
				tfEndDate.setText("");
				slRate.setValue(3);
				taQuote.setText("");
				taReflection.setText("");
				
				num = book.getBookCount();
				taDisplay.setText(book.toString(num-1)); //å ��ü toString()
				setImage(m.getImage());	
			}
		}
		//..������ư //delBook ����Ʈ�ڽ����� ���ֱ�
		if(event.getSource() == btnDelete){
			num = list.getSelectedIndex();
			listM.remove(num-1);
			book.delBook(num);
			
			//num = book.getBookCount();		
			//System.out.println("bookcount: "+num);
			//tfTotal.setText(Integer.toString(num));
			//tfPercent.setText(Integer.toString(num*100/numGoal));
		}
		if(event.getSource() == btnShowAll){
			taDisplay.setText(book.toString());
		}
		
		//�ҷ�����(���� ����)
		if(event.getSource() == openMenuItem){
			int rv = fc.showOpenDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile(); ////////78�� Swing
				listM.clear();
				book.setBookCount(0);
				//���������
				try{
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					//book(BookCollection) ��ü�� ���Ϸ� ���� �ҷ�����
					num=ois.readInt();
					for(int i=0; i<num; i++){
						Book m = (Book)ois.readObject();
						
						//������ Book ��ü�� BookCollection ��ü(book)�� �߰�
						book.addBook(m);
						//����Ʈ�� ��ȭ�������� ��� �߰�
						listM.addElement(m.getTitle());
					}		
					//��Ʈ�� �ݱ�
					ois.close();
					
					//���� ���� å, �޼� �ۼ�Ʈ ������Ʈ
					num = book.getBookCount();
					tfTotal.setText(Integer.toString(num));
					tfPercent.setText(Integer.toString(num*100/numGoal));
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "���� ���� ����");
				}
			}
		}
		//�߰��ϱ�(���� �߰�)
				if(event.getSource() == addMenuItem){
					int rv = fc.showOpenDialog(this);
					if(rv == JFileChooser.APPROVE_OPTION){
						File file = fc.getSelectedFile(); ////////78�� Swing
						//���������
						try{
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							//book(BookCollection) ��ü�� ���Ϸ� ���� �ҷ�����
							num=ois.readInt();
							for(int i=0; i<num; i++){
								Book m = (Book)ois.readObject();
								
								//������ Book ��ü�� BookCollection ��ü(book)�� �߰�
								book.addBook(m);
								//����Ʈ�� ��ȭ�������� ��� �߰�
								listM.addElement(m.getTitle());
							}		
							//��Ʈ�� �ݱ�
							ois.close();
							
							//���� ���� å, �޼� �ۼ�Ʈ ������Ʈ
							num = book.getBookCount();
							tfTotal.setText(Integer.toString(num));
							tfPercent.setText(Integer.toString(num*100/numGoal));
						} catch(Exception e){
							JOptionPane.showMessageDialog(null, "���� ���� ����");
						}
					}
				}
		//�����ϱ� ��ư
		if(event.getSource() == saveMenuItem){
			int rv = fc.showSaveDialog(this);
			
			if(rv == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile(); ////////78�� Swing
				//���������
				try{
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					//book(BookCollection) ��ü�� ���Ͽ��ٰ� ����
					num = book.getBookCount();
					oos.writeInt(num);
					
					System.out.print(book);
					for(int i=0; i<num; i++){
						Book m = book.getBookAt(i);
						oos.writeObject(m);
					}			
					//��Ʈ�� �ݱ�
					oos.close();
					JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
				} catch(IOException e){
					JOptionPane.showMessageDialog(null, "�������");
				}
			}
		}
		if(event.getSource() == goalMenuItem){
			numGoal = Integer.parseInt(JOptionPane.showInputDialog("��ǥ�� ���� ���ּ���."));
			tfGoal.setText(Integer.toString(numGoal));
			tfPercent.setText(Integer.toString(num*100/numGoal));
		}
		if(event.getSource() == bookMenuItem){
			String bookis = "å�� �д´ٴ� ���� ����� �д� ���̰� ������ �д� ���̴�.\n "
					+ "å�� å�� ���� �� ����, �� ���󿡼� �����ϴ� ������ ���� �ɱ�.\n�ⲯ�ؾ� �� ���� Ű�� ����  ���� ���̴�.\n"
					+ "�׷��� å�� �� ������ ������ �ִ� ������, \n�� ���ָ� �� ��� �ִ� �� ��ŭ ��а� �ź�ο���.\n"
					+ "��½��½ ������ ��ġ��\n���δ� �츣�� õ�� �Ҹ��� �鸮�� ���Ͽ���.\n-å�����¹ٺ��߿���";

			JOptionPane.showMessageDialog(null, bookis);
		}
	}
	//------------------------------------------------------------------------------
	//���õ� �ε����� �ش�Ǵ� ��ȭ ������ textArea ���� ����Ѵ�.
	//------------------------------------------------------------------------------
	public void valueChanged(ListSelectionEvent event){
		Book m = book.getBookAt(list.getSelectedIndex());
		num = list.getSelectedIndex();
		taDisplay.setText(book.toString(num)); //å ��ü toString()
		setImage(m.getImage());
	}
}


public class TestBook
{
	public static void main(String[] args) 
	{
		new TestBookDemo();
	}
}


