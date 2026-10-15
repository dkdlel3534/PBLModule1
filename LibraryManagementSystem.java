import myClass.* ; 
import DataBase.* ; 
import java.util.*;
import java.io.* ; 
/**
 * LibraryManagementSystem 클래스의 설명을 작성하세요.
 *
 * @author (2025957072 강서윤, 2025320070 복창희, 2023320022 편규빈)
 * @version (2026-10-14)
 */
public class LibraryManagementSystem
{
    // LMS의 속성 
    LibDB<Book> bookDB ; 
    HashMap<User,Book> loanDB ; 
    LibDB<User> userDB ;

    /**
     * LibraryManagementSystem() : 도서관 관리 시스템 클래스의 생성자. 
     */
    public LibraryManagementSystem()
    {
        // 3가지 DB를 생성. 
        bookDB = new LibDB<Book> (); 
        loanDB = new HashMap<User,Book> () ; 
        userDB = new LibDB<User> () ; 
    }

    /**
     * borrowBook() : 이용자 ID와 책 ID를 이용해 대출정보를 생성해 loan DB에 저장하는 메소드
     *
     * @param       user ID, book ID
     */
    public void borrowBook(String userID, String bookID)
    {
        User user = userDB.findElement(userID);
        Book book = bookDB.findElement(bookID);
        
        loanDB.put(user, book);
    }

    /**
     * printDB() : 책 DB 또는 이용자 DB의 모든 요소를 출력하는 메소드
     *
     * @param    db(책 DB or 이용자 DB)
     */
    public static <T extends DB_Element> void printDB(LibDB <T> db)
    {
        db.printAllElements();
    }

    /**
     * printLoanList() : 대출 현황을 전부 출력하는 메소드
     *
     * @param     대출 DB
     * @return    void
     */
    public static void printLoanList(HashMap<User,Book> loanDB)
    {
        Set<User> keySet = loanDB.keySet(); // 모든 key 목록 가져오기
        Iterator<User> it = keySet.iterator(); // key 목록 순회를 위한 Iterator() 실행

        System.out.println("----- 대출 현황 -----") ; 
        while (it.hasNext()) {
            User user = it.next(); //User 객체 가져오기
            Book book = loanDB.get(user); // Book 객체 가져오기

            // 대출 현황 출력 (이용자 정보 => 책 정보)
            System.out.println(user.toString()+ " ===> " + book.toString());
        }
        System.out.println("-------------------") ; 
    }

    /**
     * setBookDB() : book 파일을 읽어들여 그것을 기반으로 책을 책DB에 신규등록하는 메소드. 
     *
     * @param     book 파일의 경로명(bookFile)
     * @return    책DB (bookDB)
     */
    public LibDB<Book> setBookDB(String bookFile)
    {
        try {
            // 파일 객체 , 파일 입력 스트림 생성 
            File file = new File(bookFile) ; 
            FileReader fr = new FileReader(file) ; 

            Scanner scan = new Scanner(fr) ; 
            Vector<String> v = new Vector<String> () ;

            while(scan.hasNext()) {  
                String bookInfo = scan.nextLine() ; // 하나의 book 객체 정보 읽기 
                StringTokenizer st = new StringTokenizer(bookInfo,"/") ;  

                while(st.hasMoreTokens()) {
                    v.add(st.nextToken()) ;   // vector에 book의 속성을 저장 
                }

                Object[] array = v.toArray() ;  // vector를 배열화.
                v.clear() ;   

                // Book객체의 속성 찾기 
                String author = (String)array[2] ; 
                String bookID = (String)array[0] ; 
                String publisher = (String)array[3] ; 
                String title = (String)array[1] ; 
                int year = Integer.parseInt((String)array[4]) ; 

                Book book = new Book(author,bookID,publisher,title,year) ;  // Book 객체 생성 
                bookDB.addElement(book) ; 
            }
            fr.close() ; 

        } catch(IOException e) {
            System.out.println("파일 입출력 오류 !") ; 
        }

        return bookDB ; 
    }

    /**
     * setUserDB() :  user 파일을 읽어들여 그것을 기반으로 이용자를 이용자DB에 신규등록하는 메소드. 
     *
     * @param     user 파일의 경로명(userFile)
     * @return    이용자DB(userDB)
     */
    public LibDB<User> setUserDB(String userFile)
    {
        try {
            // 파일 객체 , 파일 입력 스트림 생성  
            File file = new File(userFile) ; 
            FileReader fr = new FileReader(file) ; 

            Scanner scan = new Scanner(fr) ; 
            Vector<String> v = new Vector<String> () ; 

            while (scan.hasNext()) { 
                String userInfo = scan.nextLine() ;  // 하나의 user 정보 읽기 
                StringTokenizer st = new StringTokenizer(userInfo,"/") ; 

                while (st.hasMoreTokens()) {
                    v.add(st.nextToken()) ;   // vector에 user의 속성을 저장 . 
                }

                Object[] array = v.toArray() ;  // vecetor를 배열화 . 
                v.clear() ; 

                // user의 속성 찾기 
                String name = (String)array[1] ; 
                String a = (String)array[0] ; 
                Integer stID = Integer.valueOf(a) ; 

                User user = new User(stID,name) ; // user 객체 생성 
                userDB.addElement(user) ; 
            }
            fr.close() ; 

        } catch(IOException e) {
            System.out.println("파일 입출력 오류 !") ; 
        }
        return userDB ; 
    }

}