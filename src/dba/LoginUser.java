package dba;
import com.google.gson.Gson;
import data.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class LoginUser {
    String un, pss;
    boolean r;

    public Boolean LoginUser(String nama, String psw) {
        Gson g = new Gson();
        User user;
        Boolean r;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/jason")
                .uri(URI.create("http://172.25.96.215/risk/getuser/index.php?username="+nama+"&password="+psw))
                .build();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String in = response.body();
            user = g.fromJson(in, User.class);
            this.r=(user.getNamo().toLowerCase(Locale.ROOT).equals(nama.toLowerCase(Locale.ROOT)));
        }catch (NullPointerException e){
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.r;
    }

}