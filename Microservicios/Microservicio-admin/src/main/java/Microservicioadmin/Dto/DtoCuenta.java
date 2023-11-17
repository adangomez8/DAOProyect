package Microservicioadmin.Dto;

<<<<<<< HEAD
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
=======
import lombok.Data;

import java.time.LocalDate;

@Data
>>>>>>> e1b5f64502fe0e1f6a8941f4ebdcd11cf87e3ac7
public class DtoCuenta {
	
	private Integer idCuenta;
	private double saldo;
	private LocalDate fechaAlta;

	public DtoCuenta() {
	}

	public DtoCuenta(Integer idCuenta, double saldo, LocalDate fechaAlta) {
		super();
		this.idCuenta = idCuenta;
		this.saldo = saldo;
		this.fechaAlta = fechaAlta;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "DtoCuenta [idCuenta=" + idCuenta + ", saldo=" + saldo + ", fechaAlta=" + fechaAlta + "]";
	}


}
