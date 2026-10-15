import DataBase.* ; 
import myClass.* ; 
/**
 * App 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */
public class App
{
    public static void main(String[] args) {
        // 1. LibMS 실행 
        LibraryManagementSystem LMS = new LibraryManagementSystem() ; 
        
        // 2. 이용자 신규 등록 
        LibDB<User> userDB = LMS.setUserDB("c:\\Temp\\UserData2025.txt") ; 
        
        // 3. 이용자DB  출력 
        LMS.printDB(userDB) ; 
        
        // 4. 신규 책 등록
        LibDB<Book> bookDB = LMS.setBookDB("c:\\Temp\\BookData2025 .txt") ; 
        
        // 5. 책 DB 출력
        LMS.printDB(bookDB) ; 
        
        // 6 .
        
        
        // 7. 대출 현황 출력
        // LMS.printLoanList(loanDB) ;  // setBookDB , setUserDB에 수정 예정 (loanDB에 객체 추가하는 것).  
    }
}