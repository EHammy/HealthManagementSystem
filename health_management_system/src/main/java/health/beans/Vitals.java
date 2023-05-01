package health.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "vitals")
public class Vitals {
	@Id
	@GeneratedValue
	private long id;
    private LocalDate inputDate;
    private String bloodPressure;
    private int heartRate;
    private double temperature;
    private double weight;
    private double oxygen;

    public Vitals(int id, LocalDate inputDate, String bloodPressure, int heartRate, double temperature, double weight, double oxygen) {
        this.id = id;
        this.inputDate = inputDate;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.weight = weight;
        this.oxygen = oxygen;
    }

    public Vitals(LocalDate inputDate, String bloodPressure, int heartRate, double temperature, double weight, double oxygen) {
        this.inputDate = inputDate;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.weight = weight;
        this.oxygen = oxygen;
    }

    public Vitals(LocalDate inputDate, double systolic, double diastolic, int heartRate, double temperature, double weight, double oxygen) {
        this.inputDate = inputDate;
        this.bloodPressure = systolic + "/" + diastolic;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.weight = weight;
        this.oxygen = oxygen;
    }
}
