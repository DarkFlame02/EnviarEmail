package dad.enviaremail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // view

    @FXML
    private TextField asuntoText;

    @FXML
    private TextField destinatarioText;

    @FXML
    private TextArea mensajeText;

    @FXML
    private TextField puertoText;

    @FXML
    private TextField remitenteText;

    @FXML
    private GridPane root;

    @FXML
    private TextField servidorText;

    @FXML
    private CheckBox sslCheckBox;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public GridPane getRoot() {
        return root;
    }

    @FXML
    void onCerrarAction(ActionEvent event) {

    }

    @FXML
    void onEnviarAction(ActionEvent event) {

    }

    @FXML
    void onVaciarAction(ActionEvent event) {

    }

}