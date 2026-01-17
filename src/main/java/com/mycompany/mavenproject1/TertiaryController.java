package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;


public class TertiaryController implements Initializable {

    @FXML 
    private ListView<Object[]> miListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // 1. Configurar el ASPECTO visual de las filas del ListView
        miListView.setCellFactory(param -> new ListCell<Object[]>() {
            @Override
            protected void updateItem(Object[] item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String linea = String.format("%s | Cant: %s | Pos: %s | %s (%s)",
                            item[0], item[1], item[2], item[3], item[4]);
                    setText(linea);
                }
            }
        });

        cargarDatosDesdeArchivo();
    }

    public void cargarDatosDesdeArchivo() {
        GuardarConsultarHistorial gestor = new GuardarConsultarHistorial();
        
        List<Object[]> datos = gestor.consultarMasRecientes();

        ObservableList<Object[]> items = FXCollections.observableArrayList(datos);
        
        miListView.setItems(items);
    }

    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
}