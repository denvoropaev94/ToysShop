import model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
    static ArrayList<Toy> toys = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();
    public static void main(String[] args) {
        initDate();
        //-------------------------
        String toysInfo = String.format("Общее количество проданных игрушек %d на сумму %f",getCountOfSoldToys(),getAllPrice());
        System.out.println(toysInfo);
        System.out.println("---------------------------------------------");
        for (Employee employee:employees) {
            System.out.println(employee.getName() +" " + "продал(а) " + getProfitByEmployee(employee.getId()).toString());
        }
        System.out.println("---------------------------------------------");
        ArrayList<ToyAdditional> soldToysCount = getCountOfSoldToysByTypes();
        HashMap<ToyType,Double> soldToysPrices = getPriceOfSoldToysByTypes();
        String soldToyStr = "По типу: %s продано %d игрушек общей стоимостью %f";

        for (ToyAdditional toyAdditional:soldToysCount) {
            double price = soldToysPrices.get(toyAdditional.getType());
            System.out.println(String.format(soldToyStr,toyAdditional.getType().name(),toyAdditional.getCount(),price));
        }
        System.out.println("---------------------------------------------");
        int age = 28;
        String analyzeToysStr = "Покупатели младше %d лет выбирают тип игрушки - %s";
        System.out.println(String.format(analyzeToysStr,30,getMostPopularToyByTypeLessAge(28)));

        String analyzeToysStr2 = "Покупатели старше %d лет выбирают тип игрушки - %s";
        System.out.println(String.format(analyzeToysStr2,30,getMostPopularToyByTypeMoreAge(28)));
    }


    //Получить наиболее популярную игрушку по типу для покупателя старше определенного возраста(age)
    public static ToyType getMostPopularToyByTypeMoreAge(int age){
        ArrayList<Long> customersId = new ArrayList<>();
        for (Customer customer:customers) {
            if(customer.getAge()>age){
                customersId.add(customer.getId());
            }
        }
        return getMostPopularToyType(customersId);
    }




    //Получить наиболее популярную игрушку по типу для покупателя младше определенного возраста age
    public static ToyType getMostPopularToyByTypeLessAge(int age){
        ArrayList<Long> customersId = new ArrayList<>();
        for (Customer customer:customers) {
            if(customer.getAge()<age){
                customersId.add(customer.getId());
            }
        }
        return getMostPopularToyType(customersId);
    }

    private static ToyType getMostPopularToyType(ArrayList<Long> customersId) {
        int countNB = 0,countInt=0,countSt=0,countDol=0,countTran=0;

        for (Order order:orders) {
            if(customersId.contains(order.getCustomerId())){
                countNB+=getCountOfSoldToysByType(order,ToyType.newbornToys);
                countInt+=getCountOfSoldToysByType(order,ToyType.educationalToys);
                countSt+=getCountOfSoldToysByType(order,ToyType.staffedToys);
                countDol+=getCountOfSoldToysByType(order,ToyType.dolls);
                countTran+=getCountOfSoldToysByType(order,ToyType.toysTransport);

            }
        }
        ArrayList<ToyAdditional> result = new ArrayList<>();
        result.add(new ToyAdditional(ToyType.newbornToys,countNB));
        result.add(new ToyAdditional(ToyType.educationalToys,countInt));
        result.add(new ToyAdditional(ToyType.staffedToys,countSt));
        result.add(new ToyAdditional(ToyType.dolls,countDol));
        result.add(new ToyAdditional(ToyType.toysTransport,countTran));

        result.sort(new Comparator<ToyAdditional>() {
            @Override
            public int compare(ToyAdditional left, ToyAdditional right) {
                return right.getCount() - left.getCount();
            }
        });
        return result.get(0).getType();
    }


    //Получить количество проданных игрушек по типам
    public static ArrayList<ToyAdditional> getCountOfSoldToysByTypes(){
        ArrayList<ToyAdditional> result = new ArrayList<>();
        int countNB = 0,countInt=0,countSt=0,countDol=0,countTran=0;
        for (Order order:orders) {
            countNB+=getCountOfSoldToysByType(order,ToyType.newbornToys);
            countInt+=getCountOfSoldToysByType(order,ToyType.educationalToys);
            countSt+=getCountOfSoldToysByType(order,ToyType.staffedToys);
            countDol+=getCountOfSoldToysByType(order,ToyType.dolls);
            countTran+=getCountOfSoldToysByType(order,ToyType.toysTransport);

        }
        result.add(new ToyAdditional(ToyType.newbornToys,countNB));
        result.add(new ToyAdditional(ToyType.educationalToys,countInt));
        result.add(new ToyAdditional(ToyType.staffedToys,countSt));
        result.add(new ToyAdditional(ToyType.dolls,countDol));
        result.add(new ToyAdditional(ToyType.toysTransport,countTran));

        return result;
    }






    //Получить количество игрушек в одном заказе по определенному типу
    public static int getCountOfSoldToysByType(Order order,ToyType type) {
        int count = 0;
        for (long toyId : order.getToys()) {
            Toy toy = getToyId(toyId);
            if (toy != null && toy.getToyType()==type)
                count ++;
        }
        return count;
    }








    //получить стоимость всех проданных игрушек по типам
    public static HashMap<ToyType,Double> getPriceOfSoldToysByTypes(){
        HashMap<ToyType,Double> result = new HashMap<>();
        double priceNB = 0,priceInt=0,priceSt=0,priceDol=0,priceTran=0;
        for (Order order:orders) {
            priceNB+=getPriceOfSoldToysByType(order,ToyType.newbornToys);
            priceInt+=getPriceOfSoldToysByType(order,ToyType.educationalToys);
            priceSt+=getPriceOfSoldToysByType(order,ToyType.staffedToys);
            priceDol+=getPriceOfSoldToysByType(order,ToyType.dolls);
            priceTran+=getPriceOfSoldToysByType(order,ToyType.toysTransport);

        }
        result.put(ToyType.newbornToys,priceNB);
        result.put(ToyType.educationalToys,priceInt);
        result.put(ToyType.staffedToys,priceSt);
        result.put(ToyType.dolls,priceDol);
        result.put(ToyType.toysTransport,priceTran);
        return result;
    }

    //Получить общую сумму игрушек в одном заказе по определенному типу
    public static double getPriceOfSoldToysByType(Order order,ToyType type) {
        double price = 0;
        for (long toyId : order.getToys()) {
            Toy toy = getToyId(toyId);
            if (toy != null && toy.getToyType()==type)
                price += toy.getPrice();
        }
        return price;
    }




    //Получить общее количество и общую стоимость проданного товара для определенного продавцы
    public static Profit getProfitByEmployee(long employeeId){
        int count = 0; double price = 0;
        for (Order order:orders) {
            if(order.getEmployeeId() == employeeId){
                price+=getPriceOfSoldToyInOrder(order);
                count+=order.getToys().length;
            }

        }
        return new Profit(count,price);
    }




    //Получаем сумму со всез заказов
    public static double getAllPrice(){
        double price = 0;
        for (Order order:orders) {
            price += getPriceOfSoldToyInOrder(order);
        }
        return price;
    }





    //Получить общую сумму с одного заказа
    public static double getPriceOfSoldToyInOrder(Order order){
        double price = 0;
        for (long toyId:order.getToys()) {
            Toy toy = getToyId(toyId);
            if(toy != null)
                price+=toy.getPrice();
        }
        return price;
    }



    //Получить количество проданных игрушек
    public static int getCountOfSoldToys(){
        int count = 0;
        for (Order order:orders) {
            count+=order.getToys().length;
        }
        return count;
    }



    //Найти игрушку по id
    public static Toy getToyId(long id){
        Toy current = null;

        for (Toy toy:toys) {
            if (toy.getId() == id){
                current = toy;
                break;
            }
        }

        return current;
    }



        public  static void initDate(){
        //продавцы
        employees.add(new Employee(1,"Кононова Анна",45));
        employees.add(new Employee(2,"Иванов Петр",22));
        employees.add(new Employee(3,"Сидорова Анна",27));

        //покупатели
        customers.add(new Customer(1,"Бондаков Игорь",32));
        customers.add(new Customer(2,"Волкова Анастасия",18));
        customers.add(new Customer(3,"Воронова Лиза",33));

        //игрушки
        toys.add(new Toy(1,"Кубик",15.25,ToyType.newbornToys));
        toys.add(new Toy(2,"Пазл",24.8,ToyType.educationalToys));
        toys.add(new Toy(3,"Акула IKEA",50.5,ToyType.staffedToys));
        toys.add(new Toy(4,"Маша",77,ToyType.dolls));
        toys.add(new Toy(5,"Синий трактор",25.35,ToyType.toysTransport));
        toys.add(new Toy(6,"Прорезыватель",5,ToyType.newbornToys));
        toys.add(new Toy(7,"Шашки",10.99,ToyType.educationalToys));
        toys.add(new Toy(8,"Авакадо",71.10,ToyType.staffedToys));
        toys.add(new Toy(9,"Велосипед",99.99,ToyType.toysTransport));
        toys.add(new Toy(10,"Кен",9,ToyType.dolls));

        //заказы
        orders.add(new Order(1,2,1,new long[]{1,4,7,9}));
        orders.add(new Order(2,1,2,new long[]{5,9}));
        orders.add(new Order(3,3,1,new long[]{2,3,6}));
        orders.add(new Order(4,1,3,new long[]{1,7}));
        orders.add(new Order(5,2,3,new long[]{5,10}));



    }


}