package model.dao.impl.product;


public abstract class ProductDaoFactory {
    private static ProductDaoFactory daoFactory;

    public abstract ProductsDao findAll();
    public abstract ProductsDao getNumberOfRows();
    public abstract ProductsDao delete();
    public abstract ProductsDao find();
    public abstract ProductsDao update();
    public abstract ProductsDao findAllById();
    public abstract ProductsDao create();
    public abstract ProductsDao eatProduct();
    public abstract ProductsDao findAllByDate();
    public abstract ProductsDao deleteUserProduct();


    public static ProductDaoFactory getInstance(){

        if( daoFactory == null ){
            synchronized (ProductDaoFactory.class){
                if(daoFactory==null){

                    ProductDaoFactory temp = new JDBCProductDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
