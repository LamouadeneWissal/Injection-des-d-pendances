package metier;

import dao.IDao;
public class ImetierImpl implements Imetier {
    private IDao dao;
    public ImetierImpl(IDao dao) {
        this.dao = dao;
    }

    public ImetierImpl() {
    }

    @Override
    public double calcul() {
        double t = dao.getData();
        double res = t * 23;
        return res;

    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
