import myClass.* ; 
import DataBase.* ; 
import java.util.*;
import java.io.* ; 
/**
 * LibraryManagementSystem : 도서관 관리 시스템에서 수행하는 여러 가지 작업을 정의해 놓은 클래스. 
 *                           (책 대출, DB 출력, 책 등록 및 이용자 등록)
 *
 * @author (2025957072 강서윤, 2025320070 복창희, 2023320022 편규빈)
 * @version (2026-10-17)
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
     * @param       학번(user ID), 책 등록번호(book ID)
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
     * @return   void
     */
    public <T extends DB_Element> void printDB(LibDB <T> db)
    {
        db.printAllElements();
    }

    /**
     * printLoanList() : 대출 현황을 전부 출력하는 메소드
     *
     * @param     대출 DB
     * @return    void
     */
    public void printLoanList()
    {
        Set<User> keySet = loanDB.keySet(); // 모든 key 목록 가져오기
        Iterator<User> it = keySet.iterator(); // key 목록 순회를 위한 Iterator() 실행

        System.out.println("----- 대출 현황 -----") ;
        try{
            while (it.hasNext()) {
                User user = it.next(); //User 객체 가져오기
                Book book = loanDB.get(user); // Book 객체 가져오기

                // 대출 현황 출력 (이용자 정보 => 책 정보)
                System.out.println(user.toString()+ " ===> " + book.toString());
            }
        }
        catch(NullPointerException e){
            System.out.println("대출 정보가 존재하지 않습니다.");
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
        try{
            // 파일 객체 생성 
            File file = new File(bookFile) ; 
            
            // 문자 입력 스트림 생성 
            FileReader fr = new FileReader(file) ; 

            // FileReader를 이용하여 Scanner 객체 생성 
            Scanner scan = new Scanner(fr) ; 

            while (scan.hasNext()) { 
                String bookInfo = scan.nextLine() ; // bookFile의 한줄 읽어오기 
                StringTokenizer st = new StringTokenizer(bookInfo, "/") ;  // 한 줄에 대한 토큰화 

                String[] tokens = new String[st.countTokens()] ; 

                int i = 0 ; 
                while(st.hasMoreTokens()) {
                    tokens[i++] = st.nextToken() ;  // String[]에 토큰 저장 
                }

                // 책의 속성을 인덱싱하여 찾기 
                String author = tokens[2] ; 
                String bookID = tokens[0] ; 
                String publisher = tokens[3] ; 
                String title = tokens[1] ; 
                int year = Integer.parseInt(tokens[4]) ; 

                // 책 객체 생성 
                Book book = new Book(author, bookID, publisher, title, year) ; 

                // 책DB에 책 객체 등록 
                bookDB.addElement(book) ; 
            }
            scan.close() ;   // Input stream 닫기 
        } catch(IOException e) {
            System.out.println("bookFile - 파일 입출력 오류 발생 !")  ; 
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
            // 파일 객체 생성 
            File file = new File(userFile) ; 
            
            // 문자 입력 스트림 생성 
            FileReader fr = new FileReader(file) ; 

            // FileReader를 이용하여 Scanner 생성 
            Scanner scan = new Scanner(fr) ; 

            while(scan.hasNext()) {
                String bookInfo = scan.nextLine() ; // userFile 한줄 읽어오기 
                StringTokenizer st = new StringTokenizer(bookInfo,"/") ; // 한줄에 대한 토큰화 
                String[] tokens = new String[st.countTokens()] ;  

                int i = 0 ; 
                while(st.hasMoreTokens()) {
                    tokens[i++] = st.nextToken() ;  // String[]에 토큰을 저장 
                }
                
                // 이용자의 속성 인텍싱으로 찾기 
                Integer stID = Integer.valueOf(tokens[0]);
                String name = tokens[1] ; 
                
                // 이용자 객체 생성 
                User user = new User(stID, name) ; 
                
                // 이용자DB에 이용자 등록 
                userDB.addElement(user) ; 
            }
            scan.close() ;  // Input stream 닫기 
        } catch (IOException e) {
            System.out.println("userFile - 파일 입출력 오류 발생 !") ; 
        }
        
        return userDB ; 
    }
}