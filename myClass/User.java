package myClass;
/**
 * User 클래스 : 이용자 객체의 생성과 사용을 위한 클래스
 *
 * @author (2023320022 편규빈)
 * @version (2026-10-14)
 */
public class User extends DB_Element
{
    // 이용자의 속성
    private String name;
    private Integer stID;

    /**
     * User 클래스의 객체 생성자 : 이용자의 속성(stID,name)을 받아 이용자 객체를 생성한다. 
     * 
     * @param 학번(stID), 이름(name)
     * 
     */

    public User(int stID, String name)
    {
        this.stID = stID ;
        this.name = name ;
    }

    /**
     * getID(): 이용자의 학번을 문자열로 반환하는 메소드 
     *
     * @return    학번 (stID)
     */
    public String getID()
    {
        return Integer.toString(stID); //학번(stID)의 타입을 String으로 변경하여 반환
    }

    /**
     * toString() : 이용자의 객체의 정보를 출력하는 메소드
     *
     * @return    학번과 이름
     */
    public String toString()
    {
        return "[" +stID+ "] " + name ;
    }
}

