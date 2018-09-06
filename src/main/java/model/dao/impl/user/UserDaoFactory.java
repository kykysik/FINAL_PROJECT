package model.dao.impl.user;

public abstract class UserDaoFactory {
    private static UserDaoFactory daoFactory;

    public abstract UserDao findByUserName();
    public abstract UserDao create();
    public abstract UserDao find();
    public abstract UserDao findForCookie();

    public static UserDaoFactory getInstance(){

        if( daoFactory == null ){
            synchronized (UserDaoFactory.class){
                if(daoFactory==null){

                    UserDaoFactory temp = new JDBCUserDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}