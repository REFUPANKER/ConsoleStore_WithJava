package Products;

import java.util.Arrays;

import Brands.Brand;

import java.util.*;

public class Product {

    private int ID;
    private String Name;
    private Object[][] Properties;
    private String ProdType;
    private Brand prodBrand;

    /**
     * > index 0 must be name of property
     * > also property obj lenght must be higher than 0
     * 
     * @param properties : Product properties
     */
    public Product(int id, String name, String ProdType, Brand brand, Object[][] properties) {
        this.ID = id;
        this.Name = name;
        this.Properties = properties;
        this.ProdType = ProdType;
        prodBrand = brand;
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

    public String gProdType() {
        return this.ProdType;
    }

    public Brand gBrand() {
        return this.prodBrand;
    }

    public boolean CheckExists(String PropertyName) {
        for (int i = 0; i < Properties.length; i++) {
            if (Properties[i][0] == PropertyName) {
                return true;
            }
        }
        return false;
    }

    public String GetPropertyLine(String PropertyName) {
        if (Properties == null) {
            cwl("[Error at Product]> Properties not exist");
            return null;
        } else {
            String propLine = gId() + " " + gName() + " ";
            for (int i = 0; i < Properties.length; i++) {
                if (Properties[i][0] == PropertyName) {
                    for (int j = 0; j < Properties[i].length; j++) {
                        if (Properties[i][j] != null) {
                            propLine += Properties[i][j] + " ";
                        } else {
                            propLine += "Null" + " ";
                        }
                    }
                }
            }
            return propLine;
        }
    }

    public void ListProperties() {
        if (Properties == null) {
            cwl("[Error at Product]> Properties not exist");
            return;
        } else {
            for (int i = 0; i < Properties.length; i++) {
                if (Properties[i] != null) {
                    cwl(Arrays.toString(Properties[i]));
                }
            }
        }
    }

    void WriteReminders() {
        cwl("0 for return Brand");
        ListProperties();
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
                prodBrand.ScanAndRun();
                break;
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
}
