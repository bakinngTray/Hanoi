package example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
    Hanoi hanoi;
    HanoiView hanoiView;
    Integer curTower = null;
    Thread threadForAuto = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hanoi");

        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(createFileMenu());

        root.setTop(menuBar);

        hanoi = new Hanoi(2);
        hanoiView = new HanoiView(hanoi);

        root.setCenter(hanoiView.getMainBoard());

        hanoiView.getMainBoard().setFocusTraversable(true);

        hanoiView.getMainBoard().setOnKeyPressed((KeyEvent ke) -> {
            if (threadForAuto != null)
                threadForAuto.interrupt();
            if (curTower == null){
                if(ke.getCode()== KeyCode.F) {
                    curTower = 0;
                }
                if(ke.getCode()==KeyCode.S) {
                    curTower = 1;
                }
                if(ke.getCode()==KeyCode.T) {
                    curTower = 2;
                }
            }
            else{
                if(ke.getCode()== KeyCode.F) {
                    hanoi.moveDisk(curTower, 0);
                    curTower = null;
                    hanoiView.drawRefresh();
                }
                if(ke.getCode()==KeyCode.S) {
                    hanoi.moveDisk(curTower, 1);
                    curTower = null;
                    hanoiView.drawRefresh();
                }
                if(ke.getCode()==KeyCode.T) {
                    hanoi.moveDisk(curTower, 2);
                    curTower = null;
                    hanoiView.drawRefresh();
                }
            }
            if(ke.getCode()==KeyCode.B){
                curTower = null;
            }
        });

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Thread createAutoHanoiTread(){
        Task<Void> task = new Task<>() {
            @Override public Void call(){
                try {
                    autoHanoi(hanoi.numberDisks, 0, 2, 1);
                } catch (InterruptedException e) {

                }
                return null;
            }
        };
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
        return t;
    }


    private void autoHanoi(int num, int from, int to, int with) throws InterruptedException {
        if (num<1) return;
        if (num == 1){
            hanoi.moveDisk(from, to);
            Platform.runLater(() -> {
                hanoiView.drawRefresh();
            });
            Thread.sleep(500);
        }
        else{
            autoHanoi(num - 1, from, with, to);
            autoHanoi(1, from, to, with);
            autoHanoi(num - 1, with, to, from);
        }
    }

    private Menu createFileMenu() {
        Menu menuFile = new Menu("File");

        MenuItem number_of_disks = new MenuItem("New Game");
        number_of_disks.setOnAction((ActionEvent t) -> {
            if (threadForAuto != null)
                threadForAuto.interrupt();
            SetNumberDisksDialog dialog = new SetNumberDisksDialog(hanoi);
            if (dialog.getResult() == ButtonType.OK){
                // hanoiView.setHanoi(hanoi);
                hanoiView.drawRefresh();
            }
        });

        MenuItem play = new MenuItem("Auto play");
        play.setOnAction((ActionEvent t) -> {
            if (threadForAuto != null)
                threadForAuto.interrupt();
            hanoi.setNumberDisks(hanoi.getNumberDisks());
            threadForAuto = createAutoHanoiTread();
        });

        MenuItem help = new MenuItem("Help");
        help.setOnAction((ActionEvent t) -> {
            help_info();
        });


        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((ActionEvent t) -> {
            Platform.exit();
        });

        menuFile.getItems().addAll(number_of_disks, play, help, new SeparatorMenuItem(), exit);
        return menuFile;
    }

    private void help_info() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("control:\n\n" +
                "Press F, S or T for select First, Second or Third tower\n" +
                "The first press selects the tower you want to move the disc from\n" +
                "and the second press the tower you want to move the disc where\n\n" +
                "Press B to deselect\n\n" +
                "Press any key to make the computer stop deciding to rearrange disks in auto mode");
        alert.initModality(Modality.NONE);
        alert.showAndWait();
    }

    public static void main (String[] args) {
        launch();
    }
}