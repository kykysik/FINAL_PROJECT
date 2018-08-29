package model.dao;

import model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao findByUserName();
    public abstract UserDao create();
    public abstract UserDao find();
    public abstract ProductsDao findAll();
    public abstract ProductsDao getNumberOfRows();
    public abstract PortionsDao findAllPortions();
    public abstract PortionsDao getNumberOfRowsPortions();




    public static DaoFactory getInstance(){

        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){

                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}