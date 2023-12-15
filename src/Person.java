import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

enum PhoneTypes {
    MOB,HOME,FAX
}

enum gender {
    FEMALE, MALE;
}

public class Person {
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", fathername='" + fathername + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneTypes='" + phoneTypes + '\'' +
                ", birthdate=" + birthdate +
                ", birthdayStr='" + birthdayStr + '\'' +
                '}';
    }

//    @Override
//    public String toString() {
//        String gender;
//        return "Person{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", surname='" + surname + '\'' +
//                ", phone='" + phone + '\'' +
//                ", fathername='" + fathername + '\'' +
//                ", phoneTypes=" + phoneTypes +
//                ", birthdate=" + birthdate +
//                ", birthdayStr='" + birthdayStr + '\'' +
//                '}';
//    }

    private int id;
    private String name;
    private String surname;
    private String phone;
    private String fathername;
    private String gender;
    private String phoneTypes;
    Date birthdate = new Date();
    String birthdayStr = birthdate.toString();


    public Person() {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.fathername = fathername;
        this.gender= gender;
        this.phoneTypes = phoneTypes;
        this.birthdate = birthdate;
        this.birthdayStr = birthdayStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneTypes() {
        return phoneTypes;
    }

    public void setPhoneTypes(String phoneTypes) {
        this.phoneTypes = phoneTypes;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdayStr() {
        return birthdayStr;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }






    public class AgeCalculator {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            LocalDate birthDate = LocalDate.of(scanner.nextInt(), scanner.nextInt()+1,scanner.nextInt());
            LocalDate currentDate = LocalDate.now();
            int age = calculateAge(birthDate, currentDate);
            System.out.println("Age: " + age);
        }

        public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
            Period period = Period.between(birthDate, currentDate);
            return period.getYears();
        }
    }}

