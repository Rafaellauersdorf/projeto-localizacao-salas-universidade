import java.util.Scanner;

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
