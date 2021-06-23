package dba;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import data.Register;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DaoRisk {

    List<Register> registerList;
    HashMap<String, Float> risk2 = new HashMap<>();
    HashMap<String, Float> risk3 = new HashMap<>();

    public float getMKBDbuffer2(){return risk2.get("4.3.1");}
    public float getMKBDbuffer3(){return risk3.get("4.3.1");}
    public float getRoe2() { return risk2.get("8.4.3.5");}
    public float getRoe3() { return risk3.get("8.4.3.5");}


    public DaoRisk() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/jason")
                .uri(URI.create("http://172.25.96.215/risk/register/index.php"))
                .build();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String in = response.body();
            Gson gson =  new Gson();
            Type feedsType = new TypeToken<ArrayList<Register>>() {
            }.getType();
            registerList = gson.fromJson(in, feedsType);
            for (Register register1 : registerList) {
                risk2.put(register1.getRiskid(),register1.getDua());
                risk3.put(register1.getRiskid(),register1.getTiga());
//                System.out.println(register1.getRiskid() + " , " + register1.getParameter() + " , " + register1.getBobot());
            }
        } catch (IOException | InterruptedException | JsonIOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

}
