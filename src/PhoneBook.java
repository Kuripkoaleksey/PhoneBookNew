import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    public List<Person> contacts = new ArrayList<>();

    private List<String> toLowerCase(List<String> list) {
        List<String> listArr = new ArrayList<String>();
        for (String word : list) {
            listArr.add(word.toLowerCase());
        }
        return listArr;
    }

    public int mScanerint() throws Exception {
        int k = 0;
        Scanner scanner = new Scanner(System.in);
        boolean isInt = scanner.hasNextInt();
        if (isInt) k = scanner.nextInt();

        if (k >= 1) {
            return k;
        }
        throw new Exception("Для записи ID нужно вводить только цифры ,больше нуля, буквы вводить нельзчя");
    }

    public void addPerson(Person person) throws Exception {

        contacts.add(person);

        Scanner scanner = new Scanner(System.in);
        person.setId(contacts.size());
        System.out.println("Введите имя:");
        person.setName(scanner.nextLine());
        System.out.println("Введите фамилию:");
        person.setSurname(scanner.nextLine());
        System.out.println("Введите отчество:");
        person.setFathername(scanner.nextLine());
        System.out.println("Введите телефон:");
        person.setPhone(scanner.nextLine());
        System.out.println("Введите тип телефона (MOB,HOME, FAX):");
        person.setPhoneTypes(String.valueOf(PhoneTypes.valueOf(scanner.nextLine().toUpperCase())));
        System.out.print("Введите значение для gender (female/male): ");

        switch (String.valueOf(gender.valueOf(scanner.nextLine().toUpperCase()))) {
            case "FEMALE":
                System.out.println("Вы ввели женский пол");
                person.setGender("FEMALE");
                break;
            case "MALE":
                System.out.println("Вы ввели мужской пол");
                person.setGender("MALE");
                break;
            default:
                System.out.println("Некорректное значение для gender");
        }
        System.out.println("Введите дату вашего рождения в формате yyyy.mm.dd");
        String dateString = scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date date = format.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        person.setBirthdayStr(String.valueOf(Person.AgeCalculator.calculateAge(LocalDate.of(year, month, day), LocalDate.now())));
    }

    public void printAllPerson() {
        for (Person entry : contacts) {
            System.out.println("ID: " + entry.getId());
            System.out.println("Name: " + entry.getName());
            System.out.println("Surname: " + entry.getSurname());
            System.out.println("Fathername: " + entry.getFathername());
            System.out.println("Phone: " + entry.getPhone());
            System.out.println("Gender: " + entry.getGender());
            System.out.println("Phone Type: " + entry.getPhoneTypes());
            System.out.println("Возраст: " + entry.getBirthdayStr());
            System.out.println();
        }
    }

    public void deletePerson() {
        System.out.println("Введите фамилию контакта, который хотите удалить");
        int i = 1;
        Scanner scanner = new Scanner(System.in);
        String Surname = scanner.nextLine().toLowerCase();
        boolean isDelete = false;
        for (Person person : contacts) {
            if (person.getSurname().toLowerCase().equals(Surname)) {
                contacts.remove(person);
                System.out.println("Контакт удален");
                isDelete = true;
                break;
            }
        }
        if (isDelete == true) for (Person person : contacts) {
            person.setId(i);
            i++;
        }

        if (isDelete == false) {
            System.out.println("Контакт с такой фамилией не найден");
        }
    }

    public void saveDataFile(Person person) throws Exception {
        try {
            FileOutputStream fileWriter = new FileOutputStream("contacts.txt", true);
            {
                PrintWriter writer = new PrintWriter(fileWriter);
                writer.append(contacts.toString());
                writer.append("\n");
                writer.close();
                fileWriter.close();
                System.out.println("Данные сохранены в файл");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortirovka() {
        List<Person> sortedContacts = contacts.stream()
                .sorted(Comparator.comparing(Person::getSurname).reversed())
                .collect(Collectors.toList());
        for (Person entry : sortedContacts) {
//            System.out.println("ID: " + entry.getId());
//            System.out.println("Name: " + entry.getName());
            System.out.println("Surname: " + entry.getSurname());
//            System.out.println("Phone: " + entry.getPhone());
            System.out.println();
        }
    }

    public void filtacia() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите букву, с которой начинается фамилия");
        String Surname = scanner.nextLine().toLowerCase();
        List<Person> filteredContacts = contacts.stream()
                .filter(person -> person.getSurname().toLowerCase().startsWith(Surname))
                .collect(Collectors.toList());
        for (Person entry : filteredContacts) {
            System.out.println("ID: " + entry.getId());
            System.out.println("Name: " + entry.getName());
            System.out.println("Surname: " + entry.getSurname());
            System.out.println("Phone: " + entry.getPhone());
            System.out.println();
        }
    }

    public void redactirovanie() {
        System.out.println("Введите Фамилию контакта, который хотите отредактировать");
        int Id;
        Scanner scanner = new Scanner(System.in);
        String Surname = scanner.nextLine().toLowerCase();

        for (Person person : contacts) {
            if (person.getSurname().toLowerCase().equals(Surname)) {
                Id = person.getId();
                contacts.remove(person);
                contacts.add(person);
                person.setId(Id);
//               person.setName(scanner.nextLine());
                System.out.println("Введите имя:");
                person.setName(scanner.nextLine());
                System.out.println("Введите фамилию:");
                person.setSurname(scanner.nextLine());
                System.out.println("Введите телефон:");
                person.setPhone(scanner.nextLine());
                System.out.println("Контакт отредактирован");
                break;
            }
        }

    }

    public void poiskBukva() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите букву или несколько букв, которые есть в фамилиии");
        String searchStar = scanner.nextLine().toLowerCase();
        int count = 1;
        int slov = 0;
        int i;
        String[] arrStar = searchStar.split("[*]");
        for (Person person : contacts) {
            for (i = 0; i <= arrStar.length - 1; i++) {
                if (person.getSurname().toLowerCase().contains(arrStar[i]) && person.getSurname().toLowerCase().contains(arrStar[arrStar.length - 1])) {
                    if (count == 1)
//                for (Person entry : contacts) {
//                    System.out.println("ID: " + entry.getId());
//                    System.out.println("Name: " + entry.getName());
//                    System.out.println("Surname: " + entry.getSurname());
//                    System.out.println("Phone: " + entry.getPhone());
                        System.out.println("Найдена фамилия по Вашему запросу: " + person.getSurname());
                    count++;
                    slov++;

                }
            }
            count = 1;
        }
        if (slov == 0) {
            System.out.println("Фамилия по таким параметарм не найдена");
        }
    }

    public void poiskPropusk() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию, в месте пропущенной буквы напишите _ , например: Пе_ров");
        String searchPropusk = scanner.nextLine().toLowerCase();
        int count = 1;
        int slov = 0;
        int i;
        String[] arrPropusk = searchPropusk.split("[_]");
        String[] arrPropuskDlina = searchPropusk.split("");
        if (arrPropuskDlina.length > arrPropusk.length) {
            for (Person person : contacts) {
                String[] arrSlovo = person.getSurname().toLowerCase().split("");
                int dlina = arrSlovo.length;
                for (i = 0; i <= arrPropusk.length - 1; i++) {
                    if (person.getSurname().toLowerCase().contains(arrPropusk[i]) && person.getSurname().toLowerCase().contains(arrPropusk[arrPropusk.length - 1])) {
                        if (count == 1 && ((arrPropuskDlina.length) == dlina))
//                for (Person entry : contacts) {
//                    System.out.println("ID: " + entry.getId());
//                    System.out.println("Name: " + entry.getName());
//                    System.out.println("Surname: " + entry.getSurname());
//                    System.out.println("Phone: " + entry.getPhone());
                            System.out.println("Найдена фамилия по Вашему запросу: " + person.getSurname());
                        count++;
                        slov++;
                    }
                }
                count = 1;
            }
        }
        if (slov == 0) {
            System.out.println("Фамилия по таким параметарм не найдена");
        }
    }
}

