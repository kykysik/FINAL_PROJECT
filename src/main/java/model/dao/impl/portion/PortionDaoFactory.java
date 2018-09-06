package model.dao.impl.portion;

public abstract class PortionDaoFactory {
    private static PortionDaoFactory daoFactory;

    public abstract PortionsDao findAll();
    public abstract PortionsDao getNumberOfRows();
    public abstract PortionsDao delete();
    public abstract PortionsDao find();
    public abstract PortionsDao editPortion();
    public abstract PortionsDao deleteById();
    public abstract PortionsDao createPortionProduct();
    public abstract PortionsDao create();
    public abstract PortionsDao findPortion();
    public abstract PortionsDao eatPortion();
    public abstract PortionsDao findAllByDate();
    public abstract PortionsDao deleteUserPortion();

    public static PortionDaoFactory getInstance(){

        if( daoFactory == null ){
            synchronized (PortionDaoFactory.class){
                if(daoFactory==null){

                    PortionDaoFactory temp = new JDBCPortionDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
