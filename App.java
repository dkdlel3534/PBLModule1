import DataBase.* ; 
import myClass.* ; 
/**
 * App : 도서관 관리 시스템(LMS)을 이용해 대출 작업 전반을 수행한다. 
 *
 * @author (2025957072 강서윤)
 * @version (2026-10-15)
 */
public class App
{
    public static void main(String[] args) {
        // 1. LibMS 생성  
        LibraryManagementSystem LMS = new LibraryManagementSystem() ;  
        
        // 2. 이용자 신규 등록 
        LMS.userDB= LMS.setUserDB("c:\\Temp\\UserData2025.txt") ; 
        
        // 3. 이용자DB  출력 
        LMS.printDB(LMS.userDB) ; 
        
        // 4. 신규 책 등록
        LibDB<Book> bookDB = LMS.setBookDB("c:\\Temp\\BookData2025.txt") ; 
        
        // 5. 책 DB 출력
        LMS.printDB(bookDB) ; 
        
        // 6 . 대출 작업 3건 수행 
        LMS.borrowBook("2025320001", "B02") ; 
        LMS.borrowBook("2024320002", "B03") ; 
        LMS.borrowBook("2025320003", "B04") ; 
        
        
        // 7. 대출DB(대출 현황) 출력 
        LMS.printLoanList(LMS.loanDB) ;  
    }
}