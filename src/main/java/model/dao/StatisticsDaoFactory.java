package model.dao;

public abstract class StatisticsDaoFactory {

    private static StatisticsDaoFactory daoFactory;

    public abstract StatisticsDao findAll();
    public abstract StatisticsDao getNumberOfRows();
    public abstract StatisticsDao deleteAfterInsert();


    public static StatisticsDaoFactory getInstance(){

        if( daoFactory == null ){
            synchronized (StatisticsDaoFactory.class){
                if(daoFactory==null){

                    StatisticsDaoFactory temp = new JDBCStatisticsDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
