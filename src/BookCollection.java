import java.util.Vector;

public class BookCollection 
{
	private Vector<Book> collection; //a collection of Book objects
	private int bookCount; //number of books

	//------------------------------------------------------------------------------
	//������ - collection �� �����ϰ�, bookCount�� 0���� �ʱ�ȭ�Ѵ�.
	//------------------------------------------------------------------------------
	public BookCollection()
	{
		collection = new Vector<Book>(); 
		bookCount = 0;
	}
	
	//------------------------------------------------------------------------------
	//bookCount �� ��ȯ�Ѵ�.
	//------------------------------------------------------------------------------
	public int getBookCount()
	{
		return bookCount;
	}
	
	//------------------------------------------------------------------------------
	//bookCount �� �����Ѵ�.
	//------------------------------------------------------------------------------
	public void setBookCount(int bookCount)
	{
		this.bookCount=bookCount;
	}
	
	//------------------------------------------------------------------------------
	//Ư�� index �� ����� Book �� ��ȯ�Ѵ�.
	//------------------------------------------------------------------------------
	public Book getBookAt(int index)
	{
		return collection.elementAt(index);
	}
	

	//------------------------------------------------------------------------------
	//collection �� Book �� �߰��ϰ�  bookCount �� 1 ������Ų��.
	//------------------------------------------------------------------------------
	public void addBook(Book m)
	{
		collection.add(m);
		bookCount++;
	}
	
	//------------------------------------------------------------------------------
	//Ư�� index �� ����� Book�� �����ϰ�  bookCount �� 1 ���ҽ�Ų��.
	//------------------------------------------------------------------------------
	public void delBook(int index){
		collection.remove(index);
		bookCount--;
	}
	
	//------------------------------------------------------------------------------
	//collection �� ��� ��ȭ ������ ��ȯ�Ѵ�. ��ϵ� ��ȭ�� ���� ������ ��ȭ ������ ��� �ִ�.
	//------------------------------------------------------------------------------
	public String toString()
	{
		String report="�ڵ��� ���̾��\n";
		report+=" ���� å : "+bookCount+"��\n\n";
	
		for(int i=0;i<bookCount;i++)
			report+=collection.elementAt(i).toString()+"\n";
		return report;
	}
	public String toString(int i)
	{	String report=collection.elementAt(i).toString()+"\n";
		return report;
	}
}
