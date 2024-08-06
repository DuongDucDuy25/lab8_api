package fpoly.example.dethithu1.API;

import com.google.gson.annotations.SerializedName;

public class XeMay {
    @SerializedName("_id")
    private String id;
    @SerializedName("tenxeph44929")
    private String tenxeph44929;
    @SerializedName("mausacph44929")
    private String mausacph44929;
    @SerializedName("giabanph44929")
    private Double giabanph44929;
    @SerializedName("motaph44929")
    private String motaph44929;
    @SerializedName("hinhanhph44929")
    private String hinhanhph44929;

    public XeMay() {
    }

    public XeMay(String id, String tenxeph44929, String mausacph44929, Double giabanph44929, String motaph44929, String hinhanhph44929) {
        this.id = id;
        this.tenxeph44929 = tenxeph44929;
        this.mausacph44929 = mausacph44929;
        this.giabanph44929 = giabanph44929;
        this.motaph44929 = motaph44929;
        this.hinhanhph44929 = hinhanhph44929;
    }

    public XeMay(String tenxeph44929, String mausacph44929, Double giabanph44929, String motaph44929, String hinhanhph44929) {
        this.tenxeph44929 = tenxeph44929;
        this.mausacph44929 = mausacph44929;
        this.giabanph44929 = giabanph44929;
        this.motaph44929 = motaph44929;
        this.hinhanhph44929 = hinhanhph44929;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenxeph44929() {
        return tenxeph44929;
    }

    public void setTenxeph44929(String tenxeph44929) {
        this.tenxeph44929 = tenxeph44929;
    }

    public String getMausacph44929() {
        return mausacph44929;
    }

    public void setMausacph44929(String mausacph44929) {
        this.mausacph44929 = mausacph44929;
    }

    public Double getGiabanph44929() {
        return giabanph44929;
    }

    public void setGiabanph44929(Double giabanph44929) {
        this.giabanph44929 = giabanph44929;
    }

    public String getMotaph44929() {
        return motaph44929;
    }

    public void setMotaph44929(String motaph44929) {
        this.motaph44929 = motaph44929;
    }

    public String getHinhanhph44929() {
        return hinhanhph44929;
    }

    public void setHinhanhph44929(String hinhanhph44929) {
        this.hinhanhph44929 = hinhanhph44929;
    }
}
