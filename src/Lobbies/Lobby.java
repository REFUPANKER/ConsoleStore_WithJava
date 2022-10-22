package Lobbies;

import Brands.Brand;
import Products.Product;

import java.util.*;

public class Lobby {
    private ArrayList<Brand> Brands = new ArrayList<Brand>();

    public Lobby() {
        InintBrands();
    }

    private void InintBrands() {
        Brand Lenovo = new Brand(100, "Lenovo", this);
        Lenovo.AddProduct(new Product(1, "V14 G2", "Laptop", Lenovo, new Object[][] {
                { "cpu", "4 core", "8 thread" },
                { "ram", "16gb" }
        }));

        Brand Apple = new Brand(2, "Apple", this);
        Apple.AddProduct(new Product(1, "iMac Pro", "Laptop", Apple, new Object[][] {
                { "cpu", "4 core", "8 thread" },
                { "ram", "16gb" }
        }));
        for (int i = 5; i < 14; i++) {
            Apple.AddProduct(new Product(i + 1, "iPhone " + i, "Phone", Apple, new Object[][] {
                    { "Camera", "15mbp" },
                    { "Memory", "256gb" },
                    { "ram", "16gb" }
            }));
        }
        Brand Asus = new Brand(3, "Asus", this);
        Asus.AddProduct(new Product(1, "TxL", "Laptop", Asus, new Object[][]{
            {"Cpu","8core","16 thread"},
            {"Ram","32gb","cl 14"},
        }));
        AddBrand(Lenovo);
        AddBrand(Apple);
        AddBrand(Asus);
    }

    public void AddBrand(Brand brand) {
        if (this.Brands.contains(brand) == false) {
            this.Brands.add(brand);
            cwl("[Brand Added]> " + brand.gName());
        } else {
            cwl("[Error : Brand-Add | Lobby]>Brand Already Exists");
        }
    }

    public void RemoveBrand(Brand brand) {
        if (this.Brands.contains(brand) == true) {
            this.Brands.remove(brand);
            cwl("[Brand Removed]> " + brand.gName());
        } else {
            cwl("[Error : Brand-Add | Lobby]>Brand Not Exists");
        }
    }

    public void ListBrands() {
        cwl("");
        for (int i = 0; i < Brands.size(); i++) {
            cw("\n");
            Brands.get(i).sId(i+1);
            cwl("ID :" + Brands.get(i).gId() + "\t" + "Brand Name :" + Brands.get(i).gName());
            cwl("Product Types");
            Brands.get(i).ListProductTypes("->");
        }
        cwl("");
    }

    void WriteReminders() {
        cwl("0 for exit");
        ListBrands();
    }

    public void ScanAndRun() {
        Scanner sc = new Scanner(System.in);
        String line = "";
        while (line.replace(" ", "").startsWith("0") == false) {
            cwl("=== Lobby ===");
            WriteReminders();
            line = sc.nextLine();
            if (line.replace(" ", "").startsWith("0")) {
                sc.close();
                cwl("Leaving from Lobby");
                cwl("App stopped ");
            } else {
                for (int i = 0; i < Brands.size(); i++) {
                    if (line.replace(" ", "").startsWith("" + Brands.get(i).gId())) {
                        line = "0";
                        cwl("Moving to :" + Brands.get(i).gName());
                        Brands.get(i).ScanAndRun();
                        break;
                    }
                }
            }
        }
        sc.close();
    }

    public void cwl(Object msg) {
        System.out.println(msg);
    }

    public void cw(Object msg) {
        System.out.print(msg);
    }

    public ArrayList<Product> ArrayToProdList(Product[] prods) {
        ArrayList<Product> prodList = new ArrayList<Product>();
        for (Product product : prods) {
            prodList.add(product);
        }
        return prodList;
    }
}