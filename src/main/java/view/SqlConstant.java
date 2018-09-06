package view;

public interface SqlConstant {
    String PORTIONS_PRE_CREATE = "SELECT COUNT(1) AS count FROM portions WHERE name = ?";
    String PORTIONS_CREATE_SQL = " INSERT INTO portions(name, calories) VALUES (?, ?)";
    String CREATE_PORTION_PRODUCT = "INSERT INTO portions_products(portions_id, products_id, amount) VALUES (?, ?, ?)";
    String FIND_PORTION = "SELECT id FROM portions WHERE name = ?";
    String FIND_ALL_PORTIONS = "SELECT id, name, calories FROM portions LIMIT ?, ?";
    String FIND_ALL_BY_DATE_PORTIONS = "SELECT user_id, portions.id, name, calories, amount, date " +
            "FROM portions JOIN user_portions u ON portions.id = u.portions_id " +
            "JOIN user u2 ON u.user_id = u2.id WHERE user_id = ? AND date = ? LIMIT ?, ?";
    String FIND_PORTIONS = "SELECT po.id, po.name, po.calories,pr.id as prId, pr.name as prName, pr.fats, pr.calories as productCalories," +
            "pr.proteins, pr.carbohydrates, product.amount " +
            "FROM portions po " +
            "JOIN portions_products product ON po.id = product.portions_id " +
            "JOIN products pr ON product.products_id = pr.id " +
            "WHERE po.id = ?";

    String GET_NUMBER_PORTIONS = "SELECT COUNT(id) as id FROM portions";
    String EAT_PORTIONS = "INSERT INTO user_portions(user_id, portions_id, amount, date) VALUES (?, ?, ?, ?)";
    String EDIT_PORTIONS = "INSERT INTO portions_products SET portions_id = ?, products_id = ?, amount = ?";
    String UPDATE_PORTIONS = "UPDATE portions SET name = ?, calories = ? WHERE  id = ?";
    String DELETE_BY_ID_PORTIONS = "DELETE FROM portions_products WHERE portions_id = ? AND products_id = ?";
    String DELETE_USER_PORTIONS = "DELETE FROM user_portions WHERE id = ?";
    String DELETE_PORTIONS = "DELETE FROM portions WHERE id = ? ";
    String PRODUCTS_PRE_CREATE = "SELECT COUNT(1) AS count FROM products WHERE name = ?";
    String PRODUCTS_CREATE_SQL = "INSERT INTO products(name, fats, proteins, carbohydrates, calories) VALUES (?, ?, ?, ?, ?)";
    String EAT_PRODUCTS = "INSERT INTO user_products(user_id, products_id, date, amount) VALUES (?, ?, ?, ?)";
    String FIND_ALL_BY_DATE_PRODUCTS = "SELECT user_id, products.id, name, calories, amount, date FROM products " +
            "JOIN user_products product ON products.id = product.products_id " +
            "JOIN user u3 ON product.user_id = u3.id WHERE user_id = ? AND date = ? LIMIT ?, ?";
    String FIND_ALL_BY_ID_SQL = "SELECT id,name,fats,proteins,carbohydrates,calories FROM products WHERE id = ?";
    String FIND_ALL_PRODUCTS = "SELECT id, name,proteins,carbohydrates,fats,calories FROM products LIMIT ?, ?";
    String GET_NUMBER_PRODUCTS = "SELECT COUNT(id) as id FROM products";
    String UPDATE_PRODUCTS = "UPDATE products SET name = ?, proteins = ?, fats = ?, carbohydrates = ?, calories = ? WHERE  id = ?";
    String FIND_PRODUCTS = "SELECT id, name,fats,proteins,carbohydrates,calories FROM products WHERE id = ?";
    String DELETE_PRODUCTS = "DELETE FROM products WHERE id = ? ";
    String DELETE_USER_PRODUCTS = "DELETE FROM user_products WHERE id = ?";
    String INSERT_SELECT_STATISTICS = "" +
            "INSERT INTO statistics(name, proteins, fats, carbohydrates, calories, amount, date, user_id) " +
            "SELECT products.name, proteins, fats, carbohydrates, calories, u.amount, u.date, user_id  " +
            "FROM products " +
            "JOIN user_products u ON products.id = u.products_id " +
            "JOIN user u2 ON u.user_id = u2.id WHERE u.date != ? " +
            "UNION ALL SELECT p.name, proteins, fats, carbohydrates, p.calories, u.amount, u.date, user_id " +
            "FROM user " +
            "JOIN user_portions u ON user.id = u.portions_id " +
            "JOIN portions p ON u.portions_id = p.id " +
            "JOIN portions_products product ON p.id = product.portions_id " +
            "JOIN products p2 ON product.products_id = p2.id WHERE u.date != ?";
    String DELETE_AFTER_INSERT_PRODUCTS = "DELETE  FROM user_products WHERE date <> ?";
    String DELETE_AFTER_INSERT_PORTIONS = "DELETE FROM user_portions WHERE date <> ?";
    String FIND_ALL_STATISTICS = "SELECT user_id, id, name, calories, amount, date, `type` FROM " +
            "(SELECT user_id, u.id, name, calories, amount, date, 'portion' as type" +
            " FROM portions JOIN user_portions u ON portions.id = u.portions_id " +
            "JOIN user u2 ON u.user_id = u2.id " +
            "UNION ALL " +
            "SELECT user_id, product.id, name, calories, amount, date, 'product' as type FROM products " +
            "JOIN user_products product ON products.id = product.products_id " +
            "JOIN user u3 ON product.user_id = u3.id " +
            "UNION ALL " +
            "SELECT user_id, statistics.id, name, calories, amount, date, 'statistics' as type FROM statistics) " +
            "X WHERE user_id = ? AND date = ? LIMIT ?, ?";
    String GET_NUMBER_ROWS_STATISTICS = "SELECT COUNT(products_id) as idAll, user_id, date FROM user_products WHERE user_id = ? AND date = ? UNION ALL " +
            "SELECT COUNT(portions_id) as idAll, user_id, date FROM user_portions WHERE user_id = ? AND date = ? " +
            "UNION ALL SELECT COUNT(id) as idAll, user_id, date FROM statistics WHERE user_id = ? AND date = ?  ";
    String DELETE_FROM_STATISTICS = "DELETE FROM statistics WHERE id = ?";
    String USER_PRE_CREATE = "SELECT COUNT(1) AS count FROM user WHERE login = ?";
    String USER_CREATE = "INSERT INTO user(login, password, second_name, first_name, middle_name, gender, " +
            "birth_date, life_activity, height, weight, norm_calories) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String FIND_BY_USER_NAME = "Select login FROM  user WHERE login = ?";
    String FIND_USER = "SELECT id, password, login, first_name, second_name, middle_name, gender, life_activity," +
            "height, weight, birth_date, norm_calories FROM user WHERE login = ? AND password = ?";
    String FIND_FOR_COOKIE = "SELECT id, login, password, first_name, second_name, middle_name, gender, life_activity," +
            "height, weight, birth_date, norm_calories FROM user WHERE login = ?";
    String DELETE_USER = "DELETE * FROM user WHERE id = ?";




}
