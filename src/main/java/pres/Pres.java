package pres;

import dao.DaoImpl;
import metier.ImetierImpl;

public class Pres {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        ImetierImpl imetier = new ImetierImpl();
        //imetier.setDao(dao);
        System.out.println(imetier.calcul());
    }
}
