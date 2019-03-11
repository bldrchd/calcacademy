package com.sap.calcacademy.calculatordb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
    @NamedQueries({ @NamedQuery(name = "Calculation.findAll", query = "SELECT c FROM Calculation c")})
    
    public class Calculation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String expression;
        private String result;

        public Calculation() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((expression == null) ? 0 : expression.hashCode());
            result = prime * result + id;
            result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Calculation other = (Calculation) obj;
            if (expression == null) {
                if (other.expression != null)
                    return false;
            } else if (!expression.equals(other.expression))
                return false;
            if (id != other.id)
                return false;
            if (result == null) {
                if (other.result != null)
                    return false;
            } else if (!result.equals(other.result))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Calculation [id=" + id + ", expression=" + expression + ", result=" + result + "]";
        }
}
