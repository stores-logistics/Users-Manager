package sa.course.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
@NamedQueries({@NamedQuery(name = Course.FIND_ALL, query = "SELECT u FROM Course u"),
              @NamedQuery( name = Student.FIND_BY_USERNAME, query = "SELECT u FROM Course u WHERE u.userName = :username")})
public class Course {

    public static final String FIND_ALL = "Course.findAll";
    public static final String FIND_BY_USERNAME = "Course.findByUsername";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String name;
    private String lastName;
    private String cabin;
    private String creditCard;
    //
    private String userName;
    private String password;
    private String phoneNumber;
    private String address;
    private String city;
    private int age;
    private String avatar;
    private String storeId;
    private String type;

    public Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }

    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }

    public String getLastName(){
      return lastName;
    }
    public void setLastName(String lastName){
      this.lastName = lastName;
    }

    public String getCabin(){
      return cabin;
    }
    public void setCabin(String cabin){
      this.cabin = cabin;
    }

    public String getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
    //
    public String getUserName(){
      return userName;
    }
    public void setUserName(String userName){
      this.userName = userName;
    }

    public String getPassword(){
      return password;
    }
    public void setPassword(String password){
      this.password = password;
    }

    public String getPhoneNumber(){
      return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
      this.phoneNumber = phoneNumber;
    }

    public String getAddress(){
      return address;
    }
    public void setAddress(String address){
      this.address = address;
    }

    public String getCity(){
      return city;
    }
    public void setCity(String city){
      this.city = city;
    }

    public int getAge(){
      return age;
    }
    public void setAge(int age){
      this.age = age;
    }
    
    public String getAvatar(){
      return avatar;
    }
    public void setAvatar(String avatar){
      this.avatar = avatar;
    }

    public String getStoreId(){
      return storeId;
    }
    public void setStoreId(String storeId){
      this.storeId = storeId;
    }

    public String getType(){
      return type;
    }
    public void setType(String type){
      this.type = type;
    }
}
