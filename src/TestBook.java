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
	// 사용자 UI를 위해 필요한 변수들 선언 
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
	//생성자 - GUI를 구성한다.
	//------------------------------------------------------------------------------
	public TestBookDemo() 
	{
		book=new BookCollection();
		//------------------------------------------------------------------------------
		// 사용자 화면에 컴포넌트들 구성하여 배치
		// 각 컴포넌트 구성시 필요한 리스너 추가
		//------------------------------------------------------------------------------
		fc = new JFileChooser();
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("파일");
		openMenuItem = new JMenuItem("불러오기");
		openMenuItem.addActionListener(this);
		fileMenu.add(openMenuItem);
		addMenuItem = new JMenuItem("추가하기");
		addMenuItem.addActionListener(this);
		fileMenu.add(addMenuItem);
		saveMenuItem = new JMenuItem("저장하기");
		saveMenuItem.addActionListener(this);
		fileMenu.add(saveMenuItem);
		
		settingMenu = new JMenu("설정");
		goalMenuItem = new JMenuItem("목표변경");
		goalMenuItem.addActionListener(this);
		settingMenu.add(goalMenuItem);
		bookMenuItem = new JMenuItem("독서란?");
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
		
		lbGoal = new JLabel("올해의 목표:");
		tfGoal = new JTextField(3);
		tfGoal.setEditable(false);
		tfGoal.setText(Integer.toString(numGoal));
		lbTotal = new JLabel("올해 읽은 책:");
		tfTotal = new JTextField(3);
		numTotal = book.getBookCount();
		tfTotal.setText(Integer.toString(numTotal));
		tfTotal.setEditable(false);
		lbPercent = new JLabel("%  달성");
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
		panel2.setBorder(new TitledBorder("추가/수정"));
		panel2_1 = new JPanel();
		panel2_1.setLayout(new GridLayout(6, 2, 0, 5));
		panel2_2 = new JPanel();
		panel2_2.setLayout(new FlowLayout());
		panel2_3 = new JPanel();
		panel2_3.setLayout(new GridLayout(2, 2, 0, 5));
		
		//Calendar, Date 객체 생성
		/*calendar = Calendar.getInstance();
		value = calendar.getTime();
		calendar.add(Calendar.YEAR, -50);
		start = calendar.getTime();
		calendar.add(Calendar.YEAR, 50);
		end = calendar.getTime();
		dateModel1 = new SpinnerDateModel(value, start, end, Calendar.DAY_OF_MONTH);
		dateModel2 = new SpinnerDateModel(value, start, end, Calendar.DAY_OF_MONTH);*/
		
		//panel2_1
		lbTitle = new JLabel("제목");
		tfTitle = new JTextField(13);
		lbAuthor = new JLabel("작가");
		tfAuthor = new JTextField(10);
		lbImage = new JLabel("이미지");
		tfImage = new JTextField(10);
		lbPage = new JLabel("총페이지");
		tfPage = new JTextField(10);
		
		lbStartDate = new JLabel("읽기 시작한 날짜");
		tfStartDate = new JTextField(10);
		//spStartDate = new JSpinner(dateModel1);
		//spStartDate.setEditor(new JSpinner.DateEditor(spStartDate, "yyyy/MM/dd"));
		lbEndDate = new JLabel("다 읽은 날짜");
		tfEndDate = new JTextField(10);
		//spEndDate = new JSpinner(dateModel2);
		//spEndDate.setEditor(new JSpinner.DateEditor(spEndDate, "yyyy/MM/dd"));
		
		//panel2_2
		lbRate = new JLabel("평점");
		slRate = new JSlider(0, 5, 3);
		slRate.setMajorTickSpacing(1);
		slRate.setPaintTicks(true);
		slRate.setPaintLabels(true);
		
		//panel2_3
		lbQuote = new JLabel("마음에 드는 구절");
		taQuote = new JTextArea(7, 10);
		//taQuote.setWrapStyleWord(true);
		scrollPane1 = new JScrollPane(taQuote);
		//scrollPane1.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		
		lbReflection = new JLabel("감상");
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
		panel3.setBorder(new TitledBorder("읽은 책 목록"));
		
		btnAdd = new JButton("추가>>");
		btnEdit = new JButton("<<수정");
		btnDelete = new JButton("삭제");
		btnShowAll = new JButton("전체보기");
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
		panel4.setBorder(new TitledBorder("독서기록"));
		img = new ImageIcon();
		JLabel image = new JLabel(img);
		taDisplay = new JTextArea(28, 22);
		scroller2 = new JScrollPane(taDisplay);
		scroller2.setPreferredSize(new Dimension(250, 500));
		panel4.add(image);
		panel4.add(scroller2);

		//------------------------------------------------------------------------------
		//	Frame 속성
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
	// 리스너 인터페이스를 위한 각 메소드 구현
	//어떤 이벤트가 발생했는지에 따라 각각의 기능을 수행한다.
	//------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == btnAdd){
			//한 묶음으로 저장(책객체 생성), 제목을 리스트에 추가, (Integer.parseInt())
			// 입력폼에서 입력받은 정보를 가지고 Book 객체 생성
			Book m = new Book(tfTitle.getText(),tfAuthor.getText(),tfImage.getText(),Integer.parseInt(tfPage.getText()),
								tfStartDate.getText(),tfEndDate.getText(), slRate.getValue(), taQuote.getText(),taReflection.getText());
			
			//생성된 Book 객체를 BookCollection 객체(book)에 추가
			book.addBook(m);
			
			//리스트에 영화제목으로 목록 추가
			listM.addElement(tfTitle.getText());
			
			//입력폼 초기화
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
			
			taDisplay.setText(book.toString(num-1)); //책 객체 toString()
			setImage(m.getImage());
		}
		if(event.getSource() == btnEdit){
			//수정할때
			//목록에서 선택된 인덱스 값을 가지고 와서
			//BookCollection에서 그 인덱스에 해당하는 Book 객체 반환받아옴
			Book m = book.getBookAt(list.getSelectedIndex());
			if(editState==0){
				btnEdit.setText("수정>>");
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
				//수정완료일때
				btnEdit.setText("<<수정");
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
				
				//입력폼 초기화
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
				taDisplay.setText(book.toString(num-1)); //책 객체 toString()
				setImage(m.getImage());	
			}
		}
		//..삭제버튼 //delBook 리스트박스에서 빼주기
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
		
		//불러오기(파일 열기)
		if(event.getSource() == openMenuItem){
			int rv = fc.showOpenDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile(); ////////78쪽 Swing
				listM.clear();
				book.setBookCount(0);
				//파일입출력
				try{
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					//book(BookCollection) 객체를 파일로 부터 불러오기
					num=ois.readInt();
					for(int i=0; i<num; i++){
						Book m = (Book)ois.readObject();
						
						//생성된 Book 객체를 BookCollection 객체(book)에 추가
						book.addBook(m);
						//리스트에 영화제목으로 목록 추가
						listM.addElement(m.getTitle());
					}		
					//스트림 닫기
					ois.close();
					
					//올해 읽은 책, 달성 퍼센트 업데이트
					num = book.getBookCount();
					tfTotal.setText(Integer.toString(num));
					tfPercent.setText(Integer.toString(num*100/numGoal));
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "파일 열기 오류");
				}
			}
		}
		//추가하기(파일 추가)
				if(event.getSource() == addMenuItem){
					int rv = fc.showOpenDialog(this);
					if(rv == JFileChooser.APPROVE_OPTION){
						File file = fc.getSelectedFile(); ////////78쪽 Swing
						//파일입출력
						try{
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							//book(BookCollection) 객체를 파일로 부터 불러오기
							num=ois.readInt();
							for(int i=0; i<num; i++){
								Book m = (Book)ois.readObject();
								
								//생성된 Book 객체를 BookCollection 객체(book)에 추가
								book.addBook(m);
								//리스트에 영화제목으로 목록 추가
								listM.addElement(m.getTitle());
							}		
							//스트림 닫기
							ois.close();
							
							//올해 읽은 책, 달성 퍼센트 업데이트
							num = book.getBookCount();
							tfTotal.setText(Integer.toString(num));
							tfPercent.setText(Integer.toString(num*100/numGoal));
						} catch(Exception e){
							JOptionPane.showMessageDialog(null, "파일 열기 오류");
						}
					}
				}
		//저장하기 버튼
		if(event.getSource() == saveMenuItem){
			int rv = fc.showSaveDialog(this);
			
			if(rv == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile(); ////////78쪽 Swing
				//파일입출력
				try{
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					//book(BookCollection) 객체를 파일에다가 저장
					num = book.getBookCount();
					oos.writeInt(num);
					
					System.out.print(book);
					for(int i=0; i<num; i++){
						Book m = book.getBookAt(i);
						oos.writeObject(m);
					}			
					//스트림 닫기
					oos.close();
					JOptionPane.showMessageDialog(null, "저장이 완료되었습니다.");
				} catch(IOException e){
					JOptionPane.showMessageDialog(null, "저장오류");
				}
			}
		}
		if(event.getSource() == goalMenuItem){
			numGoal = Integer.parseInt(JOptionPane.showInputDialog("목표를 설정 해주세요."));
			tfGoal.setText(Integer.toString(numGoal));
			tfPercent.setText(Integer.toString(num*100/numGoal));
		}
		if(event.getSource() == bookMenuItem){
			String bookis = "책을 읽는다는 것은 사람을 읽는 것이고 세상을 읽는 것이다.\n "
					+ "책과 책을 펼쳐 든 내가, 이 세상에서 차지하는 공간은 얼마쯤 될까.\n기껏해야 내 앉은 키를 넘지  못할 것이다.\n"
					+ "그러나 책과 내 마음이 오가고 있는 공간은, \n온 우주를 다 담고 있다 할 만큼 드넓고도 신비로웠다.\n"
					+ "번쩍번쩍 섬광이 비치고\n때로는 우르르 천둥 소리가 들리는 듯하였다.\n-책만보는바보중에서";

			JOptionPane.showMessageDialog(null, bookis);
		}
	}
	//------------------------------------------------------------------------------
	//선택된 인덱스에 해당되는 영화 정보를 textArea 에서 출력한다.
	//------------------------------------------------------------------------------
	public void valueChanged(ListSelectionEvent event){
		Book m = book.getBookAt(list.getSelectedIndex());
		num = list.getSelectedIndex();
		taDisplay.setText(book.toString(num)); //책 객체 toString()
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


