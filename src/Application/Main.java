package Application;

public class Main {
    static void main() {
        boolean bool = false;
        int choix;

        while (!bool) {
            choix = Controller.menu();
            switch (choix) {
                case 1 :
                    Controller.ajouterDisque();
            }
        }

    }
}
