package DataBase;
import java.util.*;
import myClass.*;
/**
 * LibDB<T> 클래스 : 책 DB와 이용지 DB를 만들어내기 위한 제네릭 클래스
 *
 * @author (2025320070 복창희)
 * @version (2026-10-14)
 */
public class LibDB<T extends DB_Element>
{
    // DB_Element를 상속하는 클래스의 객체(Book,User)만 저장 가능한 제네릭 컬렉션 선언
    private ArrayList<T> db ;

    /**
     * LibDB 클래스의 객체 생성자 : ArrayList<T>를 생성하여 LibDB에 참조시켜 LibDB 객체를 초기화함. 
     */
    public LibDB()
    {
        db = new ArrayList<T> (); 
    }

    /**
     * addElement() : DB에 요소(객체)를 추가하는 메소드
     *
     * @param    추가할 객체(Book 또는 User)
     * @return   void
     */
    public void addElement(T element)
    {
        db.add(element);
    }

    /**
     * findElement() : ID를 이용하여 DB내에 해당 ID를 보유한 객체를 검색하는 메소드
     *
     * @param    ID 값 ( 책의 등록 번호 or 이용자 학번)
     * @return   DB 내 객체 반환 (Book 또는 User)
     */
    public T findElement(String ID)
    {
        // Iterator 실행
        Iterator<T> it = db.iterator();

        T object = null;

        while(it.hasNext()) {
            object = it.next(); // DB 내 객체를 하나씩 읽어옴

            // ID와 객체의 id(학번 또는 책 등록번호)가 일치한다면 해당 객체를 반환
            if(ID.equals(object.getID())){
                break;
            }
        }
        return object;
    }
    
    /**
     * printAllElement() : DB의 객체 정보를 전부 출력하는 메소드
     */
    public void printAllElements()
    {
        if (db.size() > 0 && db.get(0) instanceof User){
            System.out.println("----- 이용자 목록 출력 -----");
        }
        else if (db.size() > 0 && db.get(0) instanceof Book){
            System.out.println("----- 책 목록 출력 -----");
        }
        for (T object : db){
            System.out.println(object.toString());
        }
        System.out.println("") ;  
    }
}