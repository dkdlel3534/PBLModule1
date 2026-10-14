import myClass.* ; 
import DataBase.* ; 
import java.util.*;
import java.io.* ; 
/**
 * LibraryManagementSystem 클래스의 설명을 작성하세요.
 *
 * @author (2025957072 강서윤)
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