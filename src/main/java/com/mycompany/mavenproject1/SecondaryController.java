package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class SecondaryController implements Initializable{

    @FXML
    private Spinner<Integer> miSpinner;
    
    @FXML
    private TextField miTextField;
    
    @FXML
    private TextField miTextField_desc;
    
    @FXML
    private RadioButton miRadioButton_ingreso;
    
    @FXML
    private RadioButton miRadioButton_gasto;

    // Variables donde se guardará la información
    private int valorGasto;
    private int valorMes;
    
    private boolean confirmacionMarcada;
    String tipoAccion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactory = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999, 1); //No creo que te gastes mas de 999999999€ de golpe.
        
        miSpinner.setValueFactory(valueFactory);
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void confirmarGuardado() throws IOException {
        GuardarConsultarArchivos gac = new GuardarConsultarArchivos();
        GuardarConsultarHistorial gah = new GuardarConsultarHistorial();
        
        try {
            // 1. Obtener la posición del Spinner
            this.valorGasto = miSpinner.getValue();

            // 2. Obtener el número del TextField (convertido a int)
            String descripcion = miTextField_desc.getText();
            String texto = miTextField.getText();
            if (texto.isEmpty()) {
                System.out.println("El campo de texto está vacío");
                return;
            }
            
            this.valorMes = Integer.parseInt(texto);
            if (valorMes < 1 || valorMes > 12){
                return;
            }
            
            
            // 3. Obtener el estado del Radio Button
            this.confirmacionMarcada = miRadioButton_gasto.isSelected();
            if (confirmacionMarcada){
                this.tipoAccion = "Gasto";
            }else{
                this.tipoAccion = "Ingreso";
            }

            // Mostrar en consola para verificar
            System.out.println("--- Datos capturados ---");
            System.out.println("Gasto: " + valorGasto);
            System.out.println("Mes: " + valorMes);
            System.out.println("Descripción: " + descripcion);
            System.out.println("¿Es un gasto?: " + tipoAccion);
            System.out.println("------------------------");

            if (confirmacionMarcada){
            gac.sumaMod(valorMes, valorGasto);
            }
            else{
                gac.restaMod(valorMes, valorGasto);
            }
            gah.añadirAlHistorial(valorGasto, valorMes,descripcion, tipoAccion);
            
            miSpinner.getValueFactory().setValue(1);
            miTextField.setText("");
            miTextField_desc.setText("");
            
            Alert exito = new Alert(AlertType.INFORMATION);
            exito.setTitle("Guardado");
            exito.setContentText("Movimiento registrado con éxito.");
            exito.showAndWait();
            
        } catch (NumberFormatException e) {
            Alert error = new Alert(AlertType.ERROR);
            error.setTitle("Error de validación");
            error.setContentText("Error: Introduce un número válido en el campo de texto.");
            error.showAndWait();
            System.err.println("Error: Introduce un número válido en el campo de texto.");
        }
    }
}