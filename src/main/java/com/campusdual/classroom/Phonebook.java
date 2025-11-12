package com.campusdual.classroom;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {

    // el test usa este nombre
    private Map<String, Contact> data = new HashMap<>();

    // el test llama a esto
    public Map<String, Contact> getData() {
        return data;
    }

    // el test llama a esto
    public void addContact(Contact contact) {
        if (contact != null && contact.getCode() != null && !contact.getCode().isEmpty()) {
            this.data.put(contact.getCode(), contact);
        }
    }

    // el test llama a esto
    public void deleteContact(String code) {
        this.data.remove(code);
    }

    // el test llama a esto
    public void showPhonebook() {
        if (this.data.isEmpty()) {
            System.out.println("No hay contactos.");
        } else {
            System.out.println("=== Listín telefónico ===");
            for (Contact c : this.data.values()) {
                System.out.println(c.toString());
            }
        }
    }

    // menú para el main
    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Listín telefónico ===");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Seleccionar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    Contact c = createContactFromInput(sc);
                    this.addContact(c);
                    System.out.println("Contacto añadido con código: " + c.getCode());
                    break;
                case "2":
                    this.showPhonebook();
                    break;
                case "3":
                    System.out.print("Código del contacto: ");
                    String code = sc.nextLine();
                    Contact selected = this.data.get(code);
                    if (selected != null) {
                        selected.showMenu();
                    } else {
                        System.out.println("No existe un contacto con ese código.");
                    }
                    break;
                case "4":
                    System.out.print("Código del contacto a eliminar: ");
                    String delCode = sc.nextLine();
                    this.deleteContact(delCode);
                    System.out.println("Contacto eliminado (si existía).");
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private Contact createContactFromInput(Scanner sc) {
        System.out.print("Nombre: ");
        String name = sc.nextLine();
        System.out.print("Apellidos: ");
        String surnames = sc.nextLine();
        System.out.print("Teléfono principal: ");
        String phone = sc.nextLine();
        return new Contact(name, surnames, phone);
    }
}
