package Brands;

import java.util.*;
import java.util.concurrent.Callable;

import Lobbies.Lobby;
import Products.Product;

public class Brand {
    private int ID;
    private String Name;
    private ArrayList<Product> BrandProducts = new ArrayList<Product>();
    public ArrayList<String> ProductTypes = new ArrayList<String>();
    public Lobby MainLobby;

    public Brand(int id, String name, Lobby lobby) {
        this.ID = id;
        this.Name = name;
        this.MainLobby = lobby;
    }

    public void WriteReminders() {
        cwl("0 for return lobby");
        UpdateProductIDs();
        ListProducts();
    }

    public void ScanAndRun() {
        Scanner sc = new Scanner(System.in);
        String line = "";
        while (line.replace(" ", "").startsWith("0") == false) {
            WriteReminders();
            line = sc.nextLine();
            if (line.replace(" ", "").startsWith("0")) {
                line = "0";
                cwl("Moving to Main Lobby");
                MainLobby.ScanAndRun();
                break;
            } else {
                for (Product product : BrandProducts) {
                    if (line.startsWith(String.valueOf(product.gId()))) {
                        cwl("Moving to product :" + product.gBrand().gName() + " " + product.gName());
                        line = "0";
                        product.ScanAndRun();
                        break;
                    }
                }
            }
        }
        sc.close();
    }

    /**
     * customizable ScanAndRun command
     * 
     * @param action : Calls action in try_catch
     */
    public void ScanAndRun(Callable<Object> action) {
        Scanner sc = new Scanner(System.in);
        try {
            action.call();
        } catch (Exception e) {
            cwl("[Error at : ScanAndRun | Brand :" + this.Name + "]" + e.getMessage());
        }
        sc.close();
    }

    public int gId() {
        return this.ID;
    }

    public void sId(int newId) {
        this.ID = newId;
    }

    public String gName() {
        return this.Name;
    }

    public void AddProduct(Product product) {
        if (BrandProducts.contains(product) == false) {
            BrandProducts.add(product);
        } else {
            cwl("[Error-Brand : AddProduct ]> " + "Item Already Exists");
        }
        UpdateProductTypes();
    }

    public void RemoveProduct(Product product) {
        if (BrandProducts.contains(product) == false) {
            cwl("[Error-Brand : RemoveProduct ]> " + "Item Already Not Exists");
        } else {
            BrandProducts.remove(product);
        }
        UpdateProductTypes();
    }

    public void ListProducts() {
        for (Product product : BrandProducts) {
            cwl("ID :" + product.gId() + "\tType :" + product.gProdType() + "\tName :" + product.gName());
        }
    }

    private void UpdateProductIDs() {
        for (int i = 0; i < BrandProducts.size(); i++) {
            BrandProducts.get(i).sId(i + 1);
        }
    }

    private void UpdateProductTypes() {
        for (int i = 0; i < BrandProducts.size(); i++) {
            if (ProductTypes.contains(BrandProducts.get(i).gProdType()) == false) {
                ProductTypes.add(BrandProducts.get(i).gProdType());
            }
        }
    }

    public void ListProductTypes(String ListingChar) {
        UpdateProductTypes();
        for (int i = 0; i < ProductTypes.size(); i++) {
            cwl(ListingChar + ProductTypes.get(i));
        }
    }

    public void ListProductTypes() {
        UpdateProductTypes();
        for (int i = 0; i < ProductTypes.size(); i++) {
            cwl(i + "> " + ProductTypes.get(i));
        }
    }

    public void cwl(Object msg) {
        System.out.println(msg);
    }

    public void cw(Object msg) {
        System.out.print(msg);
    }
}
