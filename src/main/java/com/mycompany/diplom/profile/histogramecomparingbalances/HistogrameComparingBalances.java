/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.histogramecomparingbalances;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author valik
 */
public class HistogrameComparingBalances extends JDialog {    
     /**
      * Creates a new demo instance.
      *
      * @param title  the frame title.
      */
     public HistogrameComparingBalances () {
         
         CategoryDataset dataset = createDataset();
         JFreeChart chart = createChart(dataset);
         ChartPanel chartPanel = new ChartPanel(chart, false);
         chartPanel.setPreferredSize(new Dimension(600, 370));
         setContentPane(chartPanel);
     }
 
     /**
      * Returns a sample dataset.
      * 
      * @return The dataset.
      */
     private static CategoryDataset createDataset() {
         
         List<AllUsersInfo> allUsersInfoList = new ArrayList<AllUsersInfo>();
         
         allUsersInfoList = AllUsersDao.getInstance().findAllUsersInfo();
                           
         // row keys...
         String series1 = "Users";         
         // create the dataset...
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         
         for (int i = 0; i < allUsersInfoList.size(); i++) {
            dataset.addValue(allUsersInfoList.get(i).getBalance(), series1, allUsersInfoList.get(i).getName());
         }
         
         return dataset;        
     }
     
     /**
      * Creates a sample chart.
      * 
      * @param dataset  the dataset.
      * 
      * @return The chart.
      */
    private static JFreeChart createChart(CategoryDataset dataset) {         
         // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
             "Statistics",              // chart title
             "Names",                   // domain axis label
             "Value",                   // range axis label
             dataset,                   // data
             PlotOrientation.VERTICAL,  // orientation
             true,                      // include legend
             true,                      // tooltips?
             false                      // URLs?
        );
 
        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
 
         // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);
 
         // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

         // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
 
         // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
         
         // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 
                0.0f, 0.0f, new Color(0, 0, 64));
        
        renderer.setSeriesPaint(0, gp0);
 
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                 CategoryLabelPositions.createUpRotationLabelPositions(
                         Math.PI / 6.0));
         // OPTIONAL CUSTOMISATION COMPLETED.
         
        return chart;         
    }
}
