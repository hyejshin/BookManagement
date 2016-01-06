import java.util.Vector;

public class BookCollection 
{
	private Vector<Book> collection; //a collection of Book objects
	private int bookCount; //number of books

	//------------------------------------------------------------------------------
	//생성자 - collection 을 생성하고, bookCount는 0으로 초기화한다.
	//------------------------------------------------------------------------------
	public BookCollection()
	{
		collection = new Vector<Book>(); 
		bookCount = 0;
	}
	
	//------------------------------------------------------------------------------
	//bookCount 을 반환한다.
	//------------------------------------------------------------------------------
	public int getBookCount()
	{
		return bookCount;
	}
	
	//------------------------------------------------------------------------------
	//bookCount 을 변경한다.
	//------------------------------------------------------------------------------
	public void setBookCount(int bookCount)
	{
		this.bookCount=bookCount;
	}
	
	//------------------------------------------------------------------------------
	//특정 index 에 저장된 Book 를 반환한다.
	//------------------------------------------------------------------------------
	public Book getBookAt(int index)
	{
		return collection.elementAt(index);
	}
	

	//------------------------------------------------------------------------------
	//collection 에 Book 를 추가하고  bookCount 를 1 증가시킨다.
	//------------------------------------------------------------------------------
	public void addBook(Book m)
	{
		collection.add(m);
		bookCount++;
	}
	
	//------------------------------------------------------------------------------
	//특정 index 에 저장된 Book를 삭제하고  bookCount 를 1 감소시킨다.
	//------------------------------------------------------------------------------
	public void delBook(int index){
		collection.remove(index);
		bookCount--;
	}
	
	//------------------------------------------------------------------------------
	//collection 에 담긴 영화 정보를 반환한다. 등록된 영화의 수와 각각의 영화 정보를 담고 있다.
	//------------------------------------------------------------------------------
	public String toString()
	{
		String report="★독서 다이어리★\n";
		report+=" 읽은 책 : "+bookCount+"개\n\n";
	
		for(int i=0;i<bookCount;i++)
			report+=collection.elementAt(i).toString()+"\n";
		return report;
	}
	public String toString(int i)
	{	String report=collection.elementAt(i).toString()+"\n";
		return report;
	}
}
