import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public  class PhoneBook {
    public List<Person> contacts = new ArrayList<>();

    private List<String> toLowerCase(List<String> list) {
        List<String> listArr = new ArrayList<String>();
        for (String word : list) {
            listArr.add(word.toLowerCase());
        }
        return listArr;
    }

    public void addPerson(Person person) {
        contacts.add(person);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID:");
        person.setId(scanner.nextInt());
        person.setName(scanner.nextLine());
        System.out.println("Введите имя:");
        person.setName(scanner.nextLine());
        System.out.println("Введите фамилию:");
        person.setSurname(scanner.nextLine());
        System.out.println("Введите телефон:");
        person.setPhone(scanner.nextLine());
    }

    public void printAllPerson() {
        for (Person entry : contacts) {
            System.out.println("ID: " + entry.getId());
            System.out.println("Name: " + entry.getName());
            System.out.println("Surname: " + entry.getSurname());
            System.out.println("Phone: " + entry.getPhone());
            System.out.println();
        }
    }

    public void deletePerson() {
        System.out.println("Введите фамилию контакта, который хотите удалить");
        Scanner scanner = new Scanner(System.in);
        String Surname = scanner.nextLine();
        boolean isDelete = false;
        for (Person person : contacts) {
            if (person.getSurname().equals(Surname)) {
                contacts.remove(person);
                System.out.println("Контакт удален");
                isDelete = true;
                break;
            }
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
        String Surname = scanner.nextLine();
        List<Person> filteredContacts = contacts.stream()
                .filter(person -> person.getSurname().startsWith(Surname))
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
        String Surname = scanner.nextLine();

        for (Person person : contacts) {
            if (person.getSurname().equals(Surname)) {
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
                System.out.println("Контакт удален");
                System.out.println("Контакт отредактирован");
                break;
            }
        }

    }

    public void poiskBukva() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите букву или несколько букв, которые есть в фамилиии");
        String searchStar = scanner.nextLine();
        int count = 1;
        int i;
        String[] arrStar = searchStar.split("[*]");
        for (Person person : contacts) {
            for (i = 0; i <= arrStar.length - 1; i++) {
                if (person.getSurname().contains(arrStar[i]) && person.getSurname().contains(arrStar[arrStar.length - 1])) {
                    if (count == 1)
//                for (Person entry : contacts) {
//                    System.out.println("ID: " + entry.getId());
//                    System.out.println("Name: " + entry.getName());
//                    System.out.println("Surname: " + entry.getSurname());
//                    System.out.println("Phone: " + entry.getPhone());
                        System.out.println("Найдена фамилия по Вашему запросу: " + person.getSurname());
                    count++;

                }
            }
            count = 1;
        }
    }

    public void poiskPropusk() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию, в месте пропущенной буквы напишите _ , например: Пе_ров");
        String searchPropusk = scanner.nextLine();
        int count = 1;
        int slov =0;
        int i;
        String[] arrPropusk = searchPropusk.split("[_]");
        String[] arrPropuskDlina = searchPropusk.split("");
        if (arrPropuskDlina.length > arrPropusk.length) {
            for (Person person : contacts) {
                String[] arrSlovo = person.getSurname().split("");
                int dlina = arrSlovo.length;
                for (i = 0; i <= arrPropusk.length - 1; i++) {
                    if (person.getSurname().contains(arrPropusk[i]) && person.getSurname().contains(arrPropusk[arrPropusk.length - 1])) {
                        if (count == 1 && ((arrPropuskDlina.length) == dlina))
//                for (Person entry : contacts) {
//                    System.out.println("ID: " + entry.getId());
//                    System.out.println("Name: " + entry.getName());
//                    System.out.println("Surname: " + entry.getSurname());
//                    System.out.println("Phone: " + entry.getPhone());
                            System.out.println("Найдена фамилия по Вашему запросу: " + person.getSurname());
                        count++; slov++;
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

