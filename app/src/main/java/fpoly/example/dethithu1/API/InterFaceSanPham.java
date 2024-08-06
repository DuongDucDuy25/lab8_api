package fpoly.example.dethithu1.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterFaceSanPham {
    @POST("/addxemay")
    Call<XeMay> addXeMay(@Body XeMay xeMay);

    @GET("/listxemay")
    Call<List<XeMay>> listxemay();

}
