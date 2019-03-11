package com.sap.calcacademy.calculator.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sap.calcacademy.calculator.db.*;
import com.sap.calcacademy.calculator.db.model.Calculation;

public class CalculationDAO {

	private final EntityManager em;

	public CalculationDAO(EntityManager em) {
		this.em = em;
	}

	public List<Calculation> getAll() {
		Query q = em.createNamedQuery("Calculation.findAll");
		List<Calculation> all = q.getResultList();
		return all;
	}

	public void insertCalculation(Calculation calculation) {
		em.getTransaction().begin();
		em.persist(calculation);
		em.getTransaction().commit();
	}

	public void insert(String expression, String result) {
		em.getTransaction().begin();
		Calculation calculation = new Calculation();
		calculation.setExpression(expression);
		calculation.setResult(result);
		em.persist(calculation);
		em.getTransaction().commit();
	}

	public void deleteById(int id) {
		Calculation cal = em.find(Calculation.class, id);
		em.getTransaction().begin();
		em.remove(cal);
		em.getTransaction().commit();
	}

	public void deleteAll() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Calculation.deleteAll");
		q.executeUpdate();
		em.getTransaction().commit();
	}

	public List<Calculation> getNotCalculated() {
		Query q = em.createNamedQuery("Calculation.getUncalculated");
		List<Calculation> all = q.getResultList();
		return all;
	}

	public Calculation getById(int id) {
		Calculation calculation = em.find(Calculation.class, id);
		return calculation;
	}

	public void setResult(String expression, String result) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Calculation.updateResultByExpression");
		q.setParameter("result", result);
		q.setParameter("expression", expression);
		q.executeUpdate();
		em.getTransaction().commit();
	}

	public void setResult(int id, String result) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Calculation.updateResultById");
		q.setParameter("result", result);
		q.setParameter("id", id);
		q.executeUpdate();
		em.getTransaction().commit();
	}

}
