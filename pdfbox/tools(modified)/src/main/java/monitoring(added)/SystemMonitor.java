package monitoring;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;

// ========== Ali Maher ==========
// this file is modified by Ali Maher >>>

public class SystemMonitor {

    private TimeSeries cpuSeries;
    private Timer timer;
    private double avgCpuLoad = 0;

    public SystemMonitor() {
        cpuSeries = new TimeSeries("CPU Usage");
    }

    public void displayChart() {
        TimeSeriesCollection dataset = new TimeSeriesCollection(cpuSeries);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Real-Time CPU Usage",
                "Time",
                "CPU Load (%)",
                dataset,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));

        JFrame frame = new JFrame("CPU Usage Monitor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Use DISPOSE_ON_CLOSE to keep it open
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void startMonitoring() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double cpuLoad = osBean.getSystemCpuLoad() * 100;
                if (cpuLoad >= 0) {
                    cpuSeries.addOrUpdate(new Millisecond(), cpuLoad);
                    System.out.println("CPU Load: " + cpuLoad + "%");
                    avgCpuLoad += cpuLoad;
                }
            }
        }, 0, 1000);
    }

    public void stopMonitoring() {
        if (timer != null) {
            timer.cancel();
            System.out.println("CPU Monitoring stopped.");
            System.out.println("Average CPU Load: " + Math.round(avgCpuLoad / cpuSeries.getItemCount() * 100) / 100 + "%");
        }
    }
}
