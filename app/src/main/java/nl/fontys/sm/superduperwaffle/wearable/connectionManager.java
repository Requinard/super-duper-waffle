package nl.fontys.sm.superduperwaffle.wearable;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jeroen0606 on 16-6-2016.
 */
public class connectionManager {
    private List<Integer> heartRateSensor;
    private List<Integer> skinConductionSensor;
    public connectionManager() {
        heartRateSensor = new ArrayList<>();
        skinConductionSensor = new ArrayList<>();
        fillDummyData();
    }

    public int calibrateHeartRateSensor() {
        int sum = 0;
        for(Integer value : heartRateSensor) {
            sum += value;
        }
        return sum/heartRateSensor.size();
    }

    public int calibrateSkinConductionSensor() {
        int sum = 0;
        for(Integer value : skinConductionSensor) {
            sum += value;
        }
        return sum/skinConductionSensor.size();
    }

    public double calculateStressHeartSensor(Double value, Double average) {
        return value/average;
    }

    public double calculateStressSkinConductionSensor(Double value, Double average) {
        return value/average;
    }

    public double calculateStressLevel(Sensor sensor, Double averageHeart, Double averageSkin) {
        double marge = 7;
        double x = 7;
        double heart;
        double skin;
        switch (sensor) {
            case both:
                heart = calculateStressHeartSensor(getHeartSensorValue(), averageHeart);
                skin = calculateStressSkinConductionSensor(getSkinSensorValue(), averageSkin);
                double average = (heart + skin) / 2;
                return (average * x) - marge;
            case heart:
                heart = calculateStressHeartSensor(getHeartSensorValue(), averageHeart);
                return (heart * x) - marge;
            case skin:
                skin = calculateStressSkinConductionSensor(getSkinSensorValue(), averageSkin);
                return (skin * x) - marge;
        }
        return 0;
    }

    public double getHeartSensorValue() {
        Random random = new Random();
        return random.nextInt(120) + 80;
    }

    public double getSkinSensorValue() {
        Random random = new Random();
        return random.nextInt(60) + 40;
    }

    public void fillDummyData() {
        //Fill heartrate sensor data
        heartRateSensor.add(90);
        heartRateSensor.add(100);
        heartRateSensor.add(80);
        heartRateSensor.add(70);
        heartRateSensor.add(75);
        heartRateSensor.add(82);
        heartRateSensor.add(96);
        heartRateSensor.add(87);

        //Fill skin-conduction data
        skinConductionSensor.add(50);
        skinConductionSensor.add(55);
        skinConductionSensor.add(57);
        skinConductionSensor.add(65);
        skinConductionSensor.add(45);
        skinConductionSensor.add(49);
        skinConductionSensor.add(48);
        skinConductionSensor.add(62);
    }
}
