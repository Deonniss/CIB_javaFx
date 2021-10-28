package logic.algorithmFFS.entity;


public class VectorLink {

    private String s;
    private String r;
    private String v;
    private String x;
    private String y;
    private String e;
    private String res;
    private String yy;

    public VectorLink(String s, String r) {
        this.s = s;
        this.r = r;
    }

    public VectorLink(String s, String r, String v, String x, String y, String e, String res, String yy) {
        this.s = s;
        this.r = r;
        this.v = v;
        this.x = x;
        this.y = y;
        this.e = e;
        this.res = res;
        this.yy = yy;
    }

    public VectorLink() {
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public String getS() {
        return s;
    }

    public String getR() {
        return r;
    }

    public void setS(String s) {
        this.s = s;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
