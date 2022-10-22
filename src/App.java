import Lobbies.Lobby;

public class App {
    public static void main(String[] args) throws Exception {
        Intro();
        Lobby lobby=new Lobby();
        lobby.ScanAndRun();
    }
    static void Intro(){
        cwl("Wellcome Console Store ");
        cwl("App started ...");
    }

    public static void cwl(Object msg){
        System.out.println(msg);
    }
    public static void cw(Object msg){
        System.out.print(msg);
    }
}
