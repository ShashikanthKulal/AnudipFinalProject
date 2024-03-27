package com.medical;

import com.medical.dao.CustomerDao;
import com.medical.dao.CustomerDaoImpl;
import com.medical.dao.MedicineDao;
import com.medical.dao.MedicineDaoImpl;
import com.medical.dao.OrderDao;
import com.medical.dao.OrderDaoImpl;
import com.medical.dao.PaymentDao;
import com.medical.dao.PaymentDaoImpl;
import com.medical.entities.Customer;
import com.medical.entities.Medicine;
import com.medical.entities.Order;
import com.medical.entities.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
            SessionFactory sf = meta.getSessionFactoryBuilder().build();

            Session session = sf.openSession();

            MedicineDao medicineDao = new MedicineDaoImpl(session);
            OrderDao orderDao = new OrderDaoImpl(session);
            PaymentDao paymentDao = new PaymentDaoImpl(session);
            CustomerDao customerDao = new CustomerDaoImpl(session);

            Scanner sc = new Scanner(System.in);

            int choice;

            do {
                System.out.println("\n---------------------| MEDICAL SHOP SYSTEM |-----------------------");
                System.out.println("1. Manage Medicines");
                System.out.println("2. Manage Orders");
                System.out.println("3. Manage Payments");
                System.out.println("4. Manage Customers");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        manageMedicines(sc, medicineDao);
                        break;
                    case 2:
                        manageOrders(sc, orderDao, customerDao);
                        break;
                    case 3:
                        managePayments(sc, paymentDao);
                        break;
                    case 4:
                        manageCustomers(sc, customerDao);
                        break;
                    case 5:
                        System.out.println("Exiting the application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter a valid option.");
                }
            } while (choice != 5);

            session.close();
            sf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void manageMedicines(Scanner sc, MedicineDao medicineDao) {
        int choice;
        do {
            System.out.println("\n------------------| MANAGE MEDICINES |------------------");
            System.out.println("1. Add Medicine");
            System.out.println("2. Update Medicine");
            System.out.println("3. Delete Medicine");
            System.out.println("4. Search Medicine");
            System.out.println("5. List All Medicines");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addMedicine(sc, medicineDao);
                    break;
                case 2:
                    updateMedicine(sc, medicineDao);
                    break;
                case 3:
                    deleteMedicine(sc, medicineDao);
                    break;
                case 4:
                    searchMedicine(sc, medicineDao);
                    break;
                case 5:
                    listAllMedicines(medicineDao);
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        } while (choice != 6);
    }

    private static void addMedicine(Scanner sc, MedicineDao medicineDao) {
        System.out.println("\n------------------| ADD MEDICINE |------------------");
        System.out.print("Enter Medicine Name: ");
        String name = sc.next();
        System.out.print("Enter Medicine Price: ");
        double price = sc.nextDouble();

        Medicine medicine = new Medicine(name, price);
        medicineDao.insert(medicine);
        System.out.println("Medicine added successfully.");
    }

    private static void updateMedicine(Scanner sc, MedicineDao medicineDao) {
        System.out.println("\n------------------| UPDATE MEDICINE |------------------");
        System.out.print("Enter Medicine ID: ");
        int id = sc.nextInt();
        System.out.print("Enter New Medicine Name: ");
        String name = sc.next();
        System.out.print("Enter New Medicine Price: ");
        double price = sc.nextDouble();

        medicineDao.update(id, name, price);
        System.out.println("Medicine updated successfully.");
    }

    private static void deleteMedicine(Scanner sc, MedicineDao medicineDao) {
        System.out.println("\n------------------| DELETE MEDICINE |------------------");
        System.out.print("Enter Medicine ID: ");
        int id = sc.nextInt();

        medicineDao.delete(id);
        System.out.println("Medicine deleted successfully.");
    }

    private static void searchMedicine(Scanner sc, MedicineDao medicineDao) {
        System.out.println("\n------------------| SEARCH MEDICINE |------------------");
        System.out.print("Enter Medicine ID: ");
        int id = sc.nextInt();

        Medicine medicine = medicineDao.search(id);
        if (medicine != null) {
            System.out.println("Medicine found: " + medicine);
        } else {
            System.out.println("Medicine not found.");
        }
    }

    private static void listAllMedicines(MedicineDao medicineDao) {
        System.out.println("\n------------------| ALL MEDICINES |------------------");
        List<Medicine> medicines = medicineDao.select();
        if (!medicines.isEmpty()) {
            System.out.println("Medicine ID\tMedicine Name\tMedicine Price");
            for (Medicine medicine : medicines) {
                System.out.println(medicine.getId() + "\t\t" + medicine.getName() + "\t\t" + medicine.getPrice());
            }
        } else {
            System.out.println("No medicines found.");
        }
    }

    private static void manageOrders(Scanner sc, OrderDao orderDao, CustomerDao customerDao) {
        int choice;
        do {
            System.out.println("\n------------------| MANAGE ORDERS |------------------");
            System.out.println("1. Add Order");
            System.out.println("2. Update Order Status");
            System.out.println("3. Delete Order");
            System.out.println("4. Search Order");
            System.out.println("5. List All Orders");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addOrder(sc, orderDao, customerDao);
                    break;
                case 2:
                    updateOrderStatus(sc, orderDao);
                    break;
                case 3:
                    deleteOrder(sc, orderDao);
                    break;
                case 4:
                    searchOrder(sc, orderDao);
                    break;
                case 5:
                    listAllOrders(orderDao);
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        } while (choice != 6);
    }

    private static void addOrder(Scanner sc, OrderDao orderDao, CustomerDao customerDao) {
        System.out.println("\n------------------| ADD ORDER |------------------");
        System.out.print("Enter Customer ID: ");
        int customerId = sc.nextInt(); // Assuming the customer ID is provided
        System.out.print("Enter Order Date and Time (YYYY-MM-DD HH:mm:ss): ");
        String dateTime = sc.next(); // Assuming the date and time are provided as a string
        // Parse the date and time string to LocalDateTime if needed

        // Assuming the customer is retrieved from the database based on the provided ID
        Customer customer = customerDao.search(customerId);
        if (customer != null) {
            Order order = new Order(LocalDateTime.parse(dateTime), customer);
            orderDao.insert(order);
            System.out.println("Order added successfully.");
        } else {
            System.out.println("Customer not found with ID: " + customerId);
        }
    }

    private static void updateOrderStatus(Scanner sc, OrderDao orderDao) {
        System.out.println("\n------------------| UPDATE ORDER STATUS |------------------");
        System.out.print("Enter Order ID: ");
        int orderId = sc.nextInt();
        System.out.print("Enter New Status: ");
        String status = sc.next();

        orderDao.update(orderId, status);
        System.out.println("Order status updated successfully.");
    }

    private static void deleteOrder(Scanner sc, OrderDao orderDao) {
        System.out.println("\n------------------| DELETE ORDER |------------------");
        System.out.print("Enter Order ID: ");
        int orderId = sc.nextInt();

        orderDao.delete(orderId);
        System.out.println("Order deleted successfully.");
    }

    private static void searchOrder(Scanner sc, OrderDao orderDao) {
        System.out.println("\n------------------| SEARCH ORDER |------------------");
        System.out.print("Enter Order ID: ");
        int orderId = sc.nextInt();

        Order order = orderDao.search(orderId);
        if (order != null) {
            System.out.println("Order found: " + order);
        } else {
            System.out.println("Order not found.");
        }
    }

    private static void listAllOrders(OrderDao orderDao) {
        System.out.println("\n------------------| ALL ORDERS |------------------");
        List<Order> orders = orderDao.select();
        if (!orders.isEmpty()) {
            System.out.println("Order ID\tCustomer ID\tOrder Date and Time\t\tStatus");
            for (Order order : orders) {
                System.out.println(order.getId() + "\t\t" + order.getCustomer().getId() + "\t\t" +
                        order.getOrderDateTime() + "\t\t" + order.getStatus());
            }
        } else {
            System.out.println("No orders found.");
        }
    }
    private static void managePayments(Scanner sc, PaymentDao paymentDao) {
        int choice;
        do {
            System.out.println("\n------------------| MANAGE PAYMENTS |------------------");
            System.out.println("1. Add Payment");
            System.out.println("2. Update Payment");
            System.out.println("3. Delete Payment");
            System.out.println("4. Search Payment");
            System.out.println("5. List All Payments");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addPayment(sc, paymentDao);
                    break;
                case 2:
                    updatePayment(sc, paymentDao);
                    break;
                case 3:
                    deletePayment(sc, paymentDao);
                    break;
                case 4:
                    searchPayment(sc, paymentDao);
                    break;
                case 5:
                    listAllPayments(paymentDao);
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        } while (choice != 6);
    }

    private static void addPayment(Scanner sc, PaymentDao paymentDao) {
        System.out.println("\n------------------| ADD PAYMENT |------------------");
        System.out.print("Enter Order ID: ");
        int orderId = sc.nextInt();
        System.out.print("Enter Payment Amount: ");
        double amount = sc.nextDouble();
        System.out.print("Enter Payment Date and Time (YYYY-MM-DD HH:mm:ss): ");
        String dateTime = sc.next();

        Payment payment = new Payment(orderId, amount, LocalDateTime.parse(dateTime));
        paymentDao.insert(payment);
        System.out.println("Payment added successfully.");
    }

    private static void updatePayment(Scanner sc, PaymentDao paymentDao) {
        System.out.println("\n------------------| UPDATE PAYMENT |------------------");
        System.out.print("Enter Payment ID: ");
        int paymentId = sc.nextInt();
        System.out.print("Enter New Payment Amount: ");
        double amount = sc.nextDouble();
        System.out.print("Enter New Payment Date and Time (YYYY-MM-DD HH:mm:ss): ");
        String dateTime = sc.next();

        Payment payment = new Payment(paymentId, amount, LocalDateTime.parse(dateTime));
        paymentDao.update(payment);
        System.out.println("Payment updated successfully.");
    }

    private static void deletePayment(Scanner sc, PaymentDao paymentDao) {
        System.out.println("\n------------------| DELETE PAYMENT |------------------");
        System.out.print("Enter Payment ID: ");
        int paymentId = sc.nextInt();

        paymentDao.delete(paymentId);
        System.out.println("Payment deleted successfully.");
    }

    private static void searchPayment(Scanner sc, PaymentDao paymentDao) {
        System.out.println("\n------------------| SEARCH PAYMENT |------------------");
        System.out.print("Enter Payment ID: ");
        int paymentId = sc.nextInt();

        Payment payment = paymentDao.search(paymentId);
        if (payment != null) {
            System.out.println("Payment found: " + payment);
        } else {
            System.out.println("Payment not found.");
        }
    }

    private static void listAllPayments(PaymentDao paymentDao) {
        System.out.println("\n------------------| ALL PAYMENTS |------------------");
        List<Payment> payments = paymentDao.selectAll();
        if (!payments.isEmpty()) {
            System.out.println("Payment ID\tOrder ID\tPayment Amount\tPayment Date and Time");
            for (Payment payment : payments) {
                System.out.println(payment.getId() + "\t\t" + payment.getOrder() + "\t\t" +
                        payment.getAmount() + "\t\t" + payment.getPaymentDateTime());
            }
        } else {
            System.out.println("No payments found.");
        }
    }

    private static void manageCustomers(Scanner sc, CustomerDao customerDao) {
        int choice;
        do {
            System.out.println("\n------------------| MANAGE CUSTOMERS |------------------");
            System.out.println("1. Add Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. Search Customer");
            System.out.println("5. List All Customers");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addCustomer(sc, customerDao);
                    break;
                case 2:
                    updateCustomer(sc, customerDao);
                    break;
                case 3:
                    deleteCustomer(sc, customerDao);
                    break;
                case 4:
                    searchCustomer(sc, customerDao);
                    break;
                case 5:
                    listAllCustomers(customerDao);
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        } while (choice != 6);
    }

    private static void addCustomer(Scanner sc, CustomerDao customerDao) {
        System.out.println("\n------------------| ADD CUSTOMER |------------------");
        System.out.print("Enter Customer Name: ");
        String name = sc.next();
        System.out.print("Enter Customer Phone: ");
        String phone = sc.next();

        Customer customer = new Customer(name, phone);
        customerDao.insert(customer);
        System.out.println("Customer added successfully.");
    }

    private static void updateCustomer(Scanner sc, CustomerDao customerDao) {
        System.out.println("\n------------------| UPDATE CUSTOMER |------------------");
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        System.out.print("Enter New Customer Name: ");
        String name = sc.next();
        System.out.print("Enter New Customer Phone: ");
        String phone = sc.next();

        customerDao.update(id, name, phone);
        System.out.println("Customer updated successfully.");
    }

    private static void deleteCustomer(Scanner sc, CustomerDao customerDao) {
        System.out.println("\n------------------| DELETE CUSTOMER |------------------");
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        customerDao.delete(id);
        System.out.println("Customer deleted successfully.");
    }

    private static void searchCustomer(Scanner sc, CustomerDao customerDao) {
        System.out.println("\n------------------| SEARCH CUSTOMER |------------------");
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        Customer customer = customerDao.search(id);
        if (customer != null) {
            System.out.println("Customer found: " + customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void listAllCustomers(CustomerDao customerDao) {
        System.out.println("\n------------------| ALL CUSTOMERS |------------------");
        List<Customer> customers = customerDao.select();
        if (!customers.isEmpty()) {
            System.out.println("Customer ID\tCustomer Name\tCustomer Phone");
            for (Customer customer : customers) {
                System.out.println(customer.getId() + "\t\t" + customer.getName() + "\t\t" + customer.getPhone());
            }
        } else {
            System.out.println("No customers found.");
        }
    }
}


