package sa.user.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({@NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u"),
              @NamedQuery( name = User.FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username")})
public class User {

    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_BY_USERNAME = "User.findByUsername";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String name;
    private String lastName;
    private String cabin;
    private String creditCard;
    //
    @Column(name = "username", unique = true)
    private String username;
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
    public String getUsername(){
      return username;
    }
    public void setUsername(String username){
      this.username = username;
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
