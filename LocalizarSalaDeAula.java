import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import java.util.Scanner;
import com.google.maps.StaticMapsApi;
import com.google.maps.StaticMapsRequest;
import com.google.maps.model.Path;
import com.google.maps.model.Size;

public class LocalizarSalaDeAula {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] salas = {"Sala A101", "Sala B203", "Sala C301", "Sala D402", "Sala E501"};
        String[] predios = {"Prédio A", "Prédio B", "Prédio C", "Prédio D", "Prédio E"};
        int salaEscolhida;
        
        System.out.println("Bem-vindo ao aplicativo de localização de salas de aula!");
        System.out.println("Digite o número da sala de aula que você está procurando: ");
        
        for (int i = 0; i < salas.length; i++) {
            System.out.println((i+1) + ". " + salas[i] + " (" + predios[i] + ")");

        
        }
        
        salaEscolhida = sc.nextInt();
        
        System.out.println("Você está procurando pela sala " + salas[salaEscolhida-1] + " no " + predios[salaEscolhida-1] + ".");
        System.out.println("O mapa do campus será exibido em breve...");
        // aqui pode ser incluído código para exibir um mapa do campus com a localização da sala de aula escolhida
    }
}


salaEscolhida = sc.nextInt();

String sala = salas[salaEscolhida-1];
String predio = predios[salaEscolhida-1];
String endereco = sala + ", " + predio + ", Paraná, Brasil";

GeoApiContext context = new GeoApiContext.Builder()
    .apiKey("sua_chave_de_api_do_google_maps_aqui")
    .build();
GeocodingResult[] results = GeocodingApi.geocode(context, endereco).await();
LatLng coordenadas = results[0].geometry.location;

System.out.println("Você está procurando pela sala " + sala + " no " + predio + ".");
System.out.println("As coordenadas geográficas da sala de aula são: " + coordenadas.toString());

System.out.println("O mapa do campus com a localização da sala de aula será exibido em breve...");

StaticMapsRequest req = StaticMapsApi