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

     public static void setUserRole(HttpServletRequest request,
                             User.ROLE role, String name) {
         ServletContext context = request.getSession().getServletContext();
         context.setAttribute("userName", name);
         request.getSession().setAttribute("role", role);
     }

     public static void setUserRole(HttpServletRequest request,
                             User.ROLE role) {

         request.getSession().setAttribute("role", role);

     }

     public static boolean checkUserInContext(HttpServletRequest request, String userName){
         HashSet<String> loggedUsers =  (HashSet<String>)request.getSession().getServletContext()
                 .getAttribute("loggedUsers");

         if(loggedUsers.stream().anyMatch(userName::equals) || loggedUsers.stream().anyMatch(userName::equals)){
             return true;
         }else {
             loggedUsers.add(userName);
             request.getSession().getServletContext()
                     .setAttribute("loggedUsers", loggedUsers);
             return false;
         }


     }

     public static void storeLoginedUser(HttpSession session, User user) {
         session.setAttribute("loggedIn", user);
     }


     public static User getLoginedUser(HttpSession session) {
          return  (User)session.getAttribute("loggedIn");
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
