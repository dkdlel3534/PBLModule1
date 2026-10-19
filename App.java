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
        // 1. LibraryManagementSystem의 객체 생성 
        LibraryManagementSystem libMS = new LibraryManagementSystem() ;  
        
        // 2. 이용자 신규 등록 
        libMS.userDB= libMS.setUserDB("c:\\Temp\\UserData2025.txt") ; 
        
        // 3. 이용자 목록 출력 
        libMS.printDB(libMS.userDB) ; 
        
        // 4. 책 신규 등록
        libMS.bookDB = libMS.setBookDB("c:\\Temp\\BookData2025.txt") ; 
        
        // 5. 책 목록 출력
        libMS.printDB(libMS.bookDB) ; 
        
        // 6 . 대출 작업 3건 수행 
        libMS.borrowBook("2025320001", "B02") ; 
        libMS.borrowBook("2024320002", "B03") ; 
        libMS.borrowBook("2025320003", "B04") ; 
        
        
        // 7. 대출 목록 출력 
        libMS.printLoanList() ;  
    }
}

