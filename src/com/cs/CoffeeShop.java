package com.cs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CoffeeShop {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to The Coffee Shop...!!! ");
        System.out.println("Please enter your name to process with your order : ");
        String name = scanner.next();
        Map<String, Map<Integer, String>> map = init(name);
        calculateAndPrintInvoice(map);
        scanner.close();
    }

    public static Map<String, Map<Integer, String>> init(String name) {

        Scanner scanner = new Scanner(System.in);
        Map<String, Map<Integer, String>> map = new HashMap<String, Map<Integer, String>>();
        Map<Integer, String> ratemap = new HashMap<>();
        ratemap.put(1, "120");
        ratemap.put(2, "95");
        ratemap.put(3, "111");
        ratemap.put(4, "110");
        ratemap.put(5, "106");
        ratemap.put(6, "125");
        map.put("rateMap", ratemap);

        Map<Integer, String> nameMap = new HashMap<>();
        nameMap.put(1, "Espresso (Short Black)");
        nameMap.put(2, "Macchiato");
        nameMap.put(3, "Long Black (Americano)");
        nameMap.put(4, "Cafe Latte");
        nameMap.put(5, "Cappuccino");
        nameMap.put(6, "Flat White");
        map.put("nameMap", nameMap);

        System.out.printf("\nDear " + name + ", Please select your desired coffee no :\n\n"
                + "S.No.\tName\t\tRate\n============================================\n"
                + "1) Espresso (Short Black)\tINR-120\n" + "2) Macchiato\t\t\tINR-95 \n"
                + "3) Long Black (Americano)\tINR-111\n" + "4) Cafe Latte\t\t\tINR-110\n"
                + "5) Cappuccino\t\t\tINR-106\n" + "6) Flat White\t\t\tINR-125\n\n"
                + "Enter a S.No (Seperate with comma for more than one) : ");// or use comma to enter more than one :
        // ");

        List<String> products = Arrays.asList(scanner.next().split(","));
        Map<Integer, String> proQuanMap = new HashMap<>();
        for (String string : products) {
            if (!string.isEmpty()) {
                if (!ratemap.containsKey(string)) {
                    System.out.println("Please enter quantity for " + nameMap.get(Integer.parseInt(string)) + " : ");
                    proQuanMap.put(Integer.valueOf(string), scanner.next());
                } else {
                    System.out.println("Invalid selection : " + string);// Please enter 1 to select again or other key
                    // to exit : ");
                    System.exit(0);
                }
            }
        }
        map.put("proQuanMap", proQuanMap);
        scanner.close();
        return map;
    }

    public static void calculateAndPrintInvoice(Map<String, Map<Integer, String>> map) {
        Map<Integer, String> rateMap = map.get("rateMap");
        Map<Integer, String> nameMap = map.get("nameMap");
        Map<Integer, String> proQuanMap = map.get("proQuanMap");
        float total = 0.00F;
        System.out.println("\n\n********** Your Order Details **********\n");
        System.out.println("Cost\tQty\tAmount\tProduct Name");
        System.out.println("--------------------------------------------");

        for (int item : proQuanMap.keySet()) {
            rateMap.get(item);
            int rate = Integer.parseInt(String.valueOf(rateMap.get(item)));
            int quantity = Integer.parseInt(proQuanMap.get(item));
            int cost = rate * quantity;
            String itemName = String.valueOf(nameMap.get(item));
            total += cost;
            System.out.println(rate + "\t\t" + quantity + "\t" + cost + "\t" + itemName);
        }
        System.out.println("--------------------------------------------");
        System.out.println("Total : " + total);
        float discount = (total / 100) * 10;
        System.out.println("\nDiscount 10% :" + discount);
        float gst = ((total - discount) / 100) * 5;

        System.out.println("\n" + "GST 5% : " + gst);
        System.out.println("--------------------------------------------");
        System.out.println("Total amount to pay : " + (total - discount + gst));
        System.out.println("--------------------------------------------");
        System.out.println("\nThank you !! Visit Again!!");

    }

}
