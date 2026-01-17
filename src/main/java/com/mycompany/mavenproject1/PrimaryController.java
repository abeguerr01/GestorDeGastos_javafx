package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class PrimaryController {
    
    @FXML
    private Label labelGastoTitulo;
    
    GuardarConsultarArchivos gca = new GuardarConsultarArchivos();
    
    private void cargarDatos(){
        int[] array = gca.leerArray();
        
        Integer totalSumaArray = 0;
        for(int i: array){
            totalSumaArray += i;
        }
        labelGastoTitulo.setText(totalSumaArray.toString());
    }
    
    private void cargarTabla(){
        int[] array = gca.leerArray();
        
        // 1. Configurar los ejes. El X para los meses y el Y para el dinero 
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mes");
        yAxis.setLabel("Gasto Total (€)");

        // 2. Crear el gráfico de barras por código
        BarChart<String, Number> graficoMensual = new BarChart<>(xAxis, yAxis);
        graficoMensual.setTitle("Evolución de Gastos");

        // 3. Crear la serie de datos
        XYChart.Series<String, Number> serieTotal = new XYChart.Series<>();
        serieTotal.setName("Gastos 2024");

        // 4. Añadir los meses y el gasto total de cada uno
        serieTotal.getData().add(new XYChart.Data<>("Enero", array[0]));
        serieTotal.getData().add(new XYChart.Data<>("Febrero", array[1]));
        serieTotal.getData().add(new XYChart.Data<>("Marzo", array[2]));
        serieTotal.getData().add(new XYChart.Data<>("Abril", array[3]));
        serieTotal.getData().add(new XYChart.Data<>("Mayo", array[4]));
        serieTotal.getData().add(new XYChart.Data<>("Junio", array[5]));
        serieTotal.getData().add(new XYChart.Data<>("Julio", array[6]));
        serieTotal.getData().add(new XYChart.Data<>("Agosto", array[7]));
        serieTotal.getData().add(new XYChart.Data<>("Septiembre", array[8]));
        serieTotal.getData().add(new XYChart.Data<>("Octubre", array[9]));
        serieTotal.getData().add(new XYChart.Data<>("Noviembre", array[10]));
        serieTotal.getData().add(new XYChart.Data<>("Diciembre", array[11]));
        

        // 5. Vincular los datos al gráfico
        graficoMensual.getData().add(serieTotal);

        // 6. Añadir el gráfico al AnchorPane 
        panelGrafico.getChildren().clear(); // Limpia por si acaso
        panelGrafico.getChildren().add(graficoMensual);      
    
        // TAMAÑO
        // 1. Hacemos que el gráfico use todo el espacio disponible del AnchorPane
        AnchorPane.setTopAnchor(graficoMensual, 0.0);
        AnchorPane.setBottomAnchor(graficoMensual, 0.0);
        AnchorPane.setLeftAnchor(graficoMensual, 0.0);
        AnchorPane.setRightAnchor(graficoMensual, 0.0);
    }

// ========================================================================== //
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void swichToTertiary() throws IOException {
        App.setRoot("tertiary");
    }
    
// ========================================================================== //

    @FXML
    private AnchorPane panelGrafico; 
    
    @FXML
    public void initialize() {
        cargarDatos();
        cargarTabla();
    }
}
