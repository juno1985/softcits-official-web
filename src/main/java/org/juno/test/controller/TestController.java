package org.juno.test.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.juno.test.model.Student;
import org.softcits.core.dto.AjaxObj;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/test1")
	public String index(Model model, HttpSession session) {
		session.setAttribute("testName", new Student("jun",31));
		Student s = new Student("Serene",22);
		model.addAttribute("as", s);
		List<Integer> testIDs = new ArrayList<Integer>();
		testIDs.add(1);
		testIDs.add(2);
		testIDs.add(22);
		testIDs.add(32);
		model.addAttribute("ids",testIDs);
		return "test/index";
	}
	
	@RequestMapping("/upload")
	public @ResponseBody AjaxObj upload(MultipartFile attach ){
		System.out.println(attach.getOriginalFilename());
		AjaxObj ob = new AjaxObj(1,"from_Ajax");;
		return ob;
	}
	
	@RequestMapping(value="/JSONObject/{name}/{age}", method=RequestMethod.GET)
	public @ResponseBody Student JSONObj(@PathVariable String name, @PathVariable int age){
		//使用@ResponseBody注解，然后JSON类型对象到页面
		return new Student(name, age);
	}
}
