package dba;

import data.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataRoni {
    @GET("getUser")
    Call<User> getUser(
            @Query("username") String username,
            @Query("password") String password
    );
}
