package example;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SetNumberDisksDialog {
    private Hanoi hanoi;
    private Stage dialog;
    private Spinner<Integer> hanoiNumberDisks;
    private Font font;
    private GridPane root;
    private ButtonType result = ButtonType.CANCEL;

    public ButtonType getResult() {
        return result;
    }

    private void createSpinnerNumberDisksEdit() {
        Label number = new Label("Number of disks:");
        number.setFont(font);
        root.add(number, 0, 4);
        hanoiNumberDisks = new Spinner<>(2, 10, hanoi.getNumberDisks(), 1);
        hanoiNumberDisks.setStyle("-fx-font-size: 24 pt");
        hanoiNumberDisks.setEditable(true);
        root.add(hanoiNumberDisks, 1, 4);
    }

    private void createButtons() {
        Button btnOk = new Button("Ok");
        btnOk.setFont(Font.font(24));
        root.add(btnOk, 0, 5);
        btnOk.setOnAction((ActionEvent e) -> {
            handleOk();
        });
        Button btnCancel = new Button("Cancel");
        btnCancel.setFont(Font.font(24));
        root.add(btnCancel, 1, 5);
        btnCancel.setOnAction((ActionEvent e) -> {
            handleCancel();
        });
    }

    private void handleOk() {
        hanoi.setNumberDisks(hanoiNumberDisks.getValue());
        result = ButtonType.OK;
        dialog.close();
    }

    private void handleCancel() {
        result = ButtonType.CANCEL;
        dialog.close();
    }

    public SetNumberDisksDialog(Hanoi hanoi) {
        this.hanoi = hanoi;
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Set number of disks");
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);

        createSpinnerNumberDisksEdit();
        createButtons();

        Scene scene = new Scene(root, 600, 500);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
