package dad.enviaremail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void onCerrarAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onEnviarAction(ActionEvent event) {
        String servidor = servidorText.getText();
        String puerto = puertoText.getText();
        String remitente = remitenteText.getText();
        String destinatario = destinatarioText.getText();
        String asunto = asuntoText.getText();
        String mensaje = mensajeText.getText();
        boolean usarSSL = sslCheckBox.isSelected();

        // Validación simple de los campos
        if (servidor.isEmpty() || puerto.isEmpty() || remitente.isEmpty() || destinatario.isEmpty() || asunto.isEmpty() || mensaje.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }

        // Llamar al método para enviar el correo
        enviarEmail(servidor, puerto, remitente, destinatario, asunto, mensaje, usarSSL);
    }

    @FXML
    void onVaciarAction(ActionEvent event) {
        servidorText.clear();
        puertoText.clear();
        remitenteText.clear();
        destinatarioText.clear();
        asuntoText.clear();
        mensajeText.clear();
        sslCheckBox.setSelected(false);
    }

    private void enviarEmail(String hostName, String smtpPort, String fromEmail, String toEmail, String subject, String message, boolean sslEnabled) {
        SimpleEmail email = new SimpleEmail();
        email.setHostName(hostName);
        email.setSmtpPort(Integer.parseInt(smtpPort));
        email.setAuthentication(fromEmail, ""); // Debes añadir el manejo de la contraseña en algún lugar seguro

        // Configuración de SSL o TLS según el valor del CheckBox
        if (sslEnabled) {
            email.setSSLOnConnect(true);
        } else {
            email.setStartTLSEnabled(true); // Usa TLS si no se usa SSL
        }

        try {
            // Configuración del correo
            email.setFrom(fromEmail);
            email.addTo(toEmail);
            email.setSubject(subject);
            email.setMsg(message);

            // Envío del correo
            email.send();
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Correo enviado con éxito.");
        } catch (EmailException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo enviar el correo: " + e.getMessage());
        }
    }

}
