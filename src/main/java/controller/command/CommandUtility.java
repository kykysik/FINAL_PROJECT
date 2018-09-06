package controller.command;

import model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
    private static final String LOGGED = "loggedUsers";
    private static final String LOGGED_IN = "loggedIn";
    private static final String ROLE = "role";
    private static final String NAME = "userName";

     public static void setUserRole(HttpServletRequest request,
                             User.ROLE role, String name) {
         ServletContext context = request.getSession().getServletContext();
         context.setAttribute(NAME, name);
         request.getSession().setAttribute(ROLE, role);
     }

     public static void setUserRole(HttpServletRequest request,
                             User.ROLE role) {

         request.getSession().setAttribute(ROLE, role);

     }

     public static boolean checkUserInContext(HttpServletRequest request, String userName){
         HashSet<String> loggedUsers =  (HashSet<String>)request.getSession().getServletContext()
                 .getAttribute(LOGGED);

         if(loggedUsers.stream().anyMatch(userName::equals) || loggedUsers.stream().anyMatch(userName::equals)){
             return true;
         }else {
             loggedUsers.add(userName);
             request.getSession().getServletContext()
                     .setAttribute(LOGGED, loggedUsers);
             return false;
         }


     }

     public static void storeLoginedUser(HttpSession session, User user) {
         session.setAttribute(LOGGED_IN, user);
     }


     public static User getLoginedUser(HttpSession session) {
          return  (User)session.getAttribute(LOGGED_IN);
      }

    public static void storeUserCookie(HttpServletResponse response, User user) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getLogin());
        // 1 день (Конвертированный в секунды)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Удалить Cookie пользователя
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 секунд. (Данный Cookie будет сразу недействителен)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

}
