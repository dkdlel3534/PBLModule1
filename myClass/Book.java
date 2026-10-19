package myClass;
/**
 * Book 클래스 : 책 객체의 생성과 사용을 위한 클래스
 *
 * @author (2023320022 편규빈)
 * @version (2026-10-14)
 */
public class Book extends DB_Element
{
    // 책의 속성
    private String author ;
    private String bookID ;
    private String publisher ;
    private String title ;
    private int year ;

    /**
     * Book 클래스 생성자 : 책의 모든 속성을 받아 책 객체를 생성한다. 
     */
    public Book(String author, String bookID, String publisher, String title, int year)
    {
        this.author = author ;
        this.bookID = bookID ;
        this.publisher = publisher ;
        this.title = title;
        this.year = year ;
    }

    /**
     * getID : 책의 등록번호를 문자열로 반환하는 메소드
     *
     * 
     * @return    책의 등록번호 (bookID)
     */
    public String getID()
    {
        return bookID;
    }
    
    /**
     * toString() : 책 객체의 정보를 출력하는 메소드
     *
     * @return    책 객체의 정보
     */
    public String toString()
    {
        return "(" + bookID + ")" + " " +title+ ", " + publisher + ", " + year ;
    }
}

