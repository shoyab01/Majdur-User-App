package model;

public class horizontal_product_scroller {
private int image_View;
private String producttxt1,txt2,txt3;

    public horizontal_product_scroller(int image_View, String producttxt1, String txt2, String txt3) {
        this.image_View = image_View;
        this.producttxt1 = producttxt1;
        this.txt2 = txt2;
        this.txt3 = txt3;
    }

    public horizontal_product_scroller(){

    }

    public int getImage_View() {
        return image_View;
    }

    public void setImage_View(int image_View) {
        this.image_View = image_View;
    }

    public String getProducttxt1() {
        return producttxt1;
    }

    public void setProducttxt1(String producttxt1) {
        this.producttxt1 = producttxt1;
    }

    public String getTxt2() {
        return txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public String getTxt3() {
        return txt3;
    }

    public void setTxt3(String txt3) {
        this.txt3 = txt3;
    }
}
