import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.net.http.HttpClient;


public class Main {
    public static String[] cutName(String name){
        return name.split(" ");
    }

    public static void getGithubPage(String githubUsername) {
        var client = HttpClient.newHttpClient();
        try{
            var requestUrl = HttpRequest.newBuilder(URI.create("https://api.github.com/users/"+githubUsername))
                    .header("accept","application/json")
                    .build();

            var responseAsync = client.sendAsync(requestUrl, HttpResponse.BodyHandlers.ofString());
            var response = responseAsync.get().body().split(",");
            for(int i=0;i<response.length;i++){
                if(response[i].contains("html_url")) {
                    System.out.println(response[i]);
                    var link =Arrays.copyOfRange(response[i].split(":"), 1, 3);
                    var s = new String(link[0]+":"+link[1]);
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec("rundll32 url.dll,FileProtocolHandler "+ s);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
    public static void main(String[] args) {
        ArrayList<String> myArray = new ArrayList<String>();

        myArray.add("Chukwuka Emi");
        myArray.add("Blue");

        HashMap<String, String> myInfo =new HashMap<>();

        String[] name = cutName(myArray.get(0));
        String fullName = name[0]+" "+name[1];
        myInfo.put("fullName",fullName);
        myInfo.put("favoriteColor",myArray.get(1));
        myInfo.put("github", "chukwuka-emi");
        try{
            getGithubPage(myInfo.get("github"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

}
