package com.campusdual.classroom;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contact implements ICallActions {

    private String name;
    private String surnames;
    private String mainPhone;
    private String code;
    private List<String> otherPhones = new ArrayList<>();

    public Contact() {
    }

    public Contact(String name, String surnames, String mainPhone) {
        this.name = name;
        this.surnames = surnames;
        this.mainPhone = mainPhone;
        this.code = generateCode(name, surnames);
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    // esto es lo que pide el test
    public String getPhone() {
        return mainPhone;
    }

    public String getCode() {
        return code;
    }

    public List<String> getOtherPhones() {
        return otherPhones;
    }

    public void addPhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            this.otherPhones.add(phone);
        }
    }

    private String generateCode(String name, String surnames) {
        String normName = normalize(name).trim();
        String normSurnames = normalize(surnames).trim();

        if (normName.isEmpty() && normSurnames.isEmpty()) {
            return "";
        }

        String firstLetterName = normName.isEmpty() ? "" : normName.substring(0, 1);

        if (!normSurnames.contains(" ")) {
            return firstLetterName + normSurnames;
        }

        String[] parts = normSurnames.split("\\s+");
        StringBuilder sb = new StringBuilder();
        sb.append(firstLetterName);
        sb.append(parts[0].substring(0, 1));
        for (int i = 1; i < parts.length; i++) {
            sb.append(parts[i]);
        }
        return sb.toString();
    }

    private String normalize(String input) {
        if (input == null) {
            return "";
        }
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").toLowerCase();
    }

    @Override
    public void callMyNumber() {
        System.out.println("El contacto " + this.name + " " + this.surnames
                + " se llama a sí mismo al número " + this.mainPhone);
    }

    @Override
    public void callOtherNumber(String number) {
        System.out.println("El contacto " + this.name + " " + this.surnames
                + " llama al número " + number);
    }

    @Override
    public void showContactDetails() {
        System.out.println("Contacto: " + this.name + " " + this.surnames);
        System.out.println("Código: " + this.code);
        System.out.println("Teléfono principal: " + this.mainPhone);
        if (!this.otherPhones.isEmpty()) {
            System.out.println("Otros teléfonos: " + this.otherPhones);
        }
    }

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Menú del contacto (" + this.code + ") ===");
            System.out.println("1. Llamar a mi número");
            System.out.println("2. Llamar a otro número");
            System.out.println("3. Mostrar detalles");
            System.out.println("4. Añadir teléfono adicional");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    this.callMyNumber();
                    break;
                case "2":
                    System.out.print("Número: ");
                    String num = sc.nextLine();
                    this.callOtherNumber(num);
                    break;
                case "3":
                    this.showContactDetails();
                    break;
                case "4":
                    System.out.print("Teléfono nuevo: ");
                    String newPhone = sc.nextLine();
                    this.addPhone(newPhone);
                    System.out.println("Añadido.");
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    @Override
    public String toString() {
        return this.code + " -> " + this.name + " " + this.surnames + " (" + this.mainPhone + ")";
    }
}
