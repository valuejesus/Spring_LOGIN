package SpringLogin.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

//    @Autowired
//    UserService userService;
    	@GetMapping(path = "original_login")
    	public String login(@RequestParam(value = "error", required = false) String error,
    	        @RequestParam(value = "logout", required = false) String logout,
    	        Model model, HttpSession session) {

    	    model.addAttribute("showErrorMsg", false);
    	    model.addAttribute("showLogoutedMsg", false);

    	    if (error != null) {
    	    	model.addAttribute("showErrorMsg", true);
    	        if (session != null) {
    	            AuthenticationException ex = (AuthenticationException) session
    	                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    	            if (ex != null) {
    	                model.addAttribute("showErrorMsg", true);
    	                model.addAttribute("errorMsg", ex.getMessage());
    	            }
    	        }
    	    } else if (logout != null) {
    	        model.addAttribute("showLogoutedMsg", true);
    	    }
        return "original_login";
    }

    //FormのSubmitを押すとPostメソッドがリクエストされます。（）に書かれたURLのリクエストを受け取るとこのメソッドが発動します。
    @PostMapping("/original_login")
    public String postSignUp(Model model) {
        /*
        今回はリダイレクトを使います。画面遷移などファイル間をまたぐ場合はリダイレクト使います。
        イメージ的にはGetメソッドを呼び出していると考えていいでしょう。
        試しにリダイレクトせず通常のフォワード(return "ｘｘｘ";)とすると画面自体は表示されますがURLが変わりません。
        こうすると、遷移先で受け取りたいデータなどが受け取れないことがあるので、リダイレクトを使用したほうが良いでしょう。
        */
        return "redirect:/clientlist";//redirect:/userList
    }

}