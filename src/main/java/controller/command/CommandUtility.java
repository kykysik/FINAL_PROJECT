package controller.command;

import model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

 class CommandUtility {

     static void setUserRole(HttpServletRequest request,
                             User.ROLE role, String name) {
         ServletContext context = request.getSession().getServletContext();
         context.setAttribute("userName", name);
         request.getSession().setAttribute("role", role);
     }

     static void setUserRole(HttpServletRequest request,
                             User.ROLE role) {
         request.getSession().setAttribute("role", role);
     }

     static boolean checkUserInContext(HttpServletRequest request, String userName){
         HashSet<String> loggedUsers =  (HashSet<String>)request.getSession().getServletContext()
                 .getAttribute("loggedUsers");

         if(loggedUsers.stream().anyMatch(userName::equals)){
             return true;
         }else {
             loggedUsers.add(userName);
             request.getSession().getServletContext()
                     .setAttribute("loggedUsers", loggedUsers);
             return false;
         }


     }

     static void storeLoginedUser(HttpSession session, User user) {
         session.setAttribute("loggedIn", user);
     }


     static User getLoginedUser(HttpSession session) {
          return  (User)session.getAttribute("loggedIn");
      }
}
