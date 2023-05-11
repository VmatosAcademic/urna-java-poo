
import java.io.FileNotFoundException;

import controller.Controller;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        Controller controller = new Controller();
        System.out.println("Iniciando");
        controller.iniciar();
    }
}
