package com.example.JPA_Thymeleaf_Demo.web;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.JPA_Thymeleaf_Demo.entity.User;
import com.example.JPA_Thymeleaf_Demo.param.UserParam;
import com.example.JPA_Thymeleaf_Demo.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String index() {
		return "redirect:/list";
	}

	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "6") Integer size) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(page, size, sort);
		Page<User> users = userRepository.findList(pageable);
		model.addAttribute("users", users);
		return "user/list";
	}

	@RequestMapping("/toAdd")
	public String toAdd() {
		return "user/userAdd";
	}

	@RequestMapping("/add")
	public String add(@Valid UserParam userParam, BindingResult result, ModelMap modelMap) {
		String errorMsg = "";
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				errorMsg = errorMsg + error.getCode() + "-" + error.getDefaultMessage();
			}
			modelMap.addAttribute("errorMsg", errorMsg);
			return "user/userAdd";
		}
		User u = userRepository.findByUserName(userParam.getUserName());
		if (u != null) {
			modelMap.addAttribute("errorMsg", "用户已存在");
			return "user/userAdd";
		}
		User user = new User();
		BeanUtils.copyProperties(userParam, user);
		user.setRegTime(new Date());
		userRepository.save(user);
		// return "redirect:/list" 代表添加成功后，直接跳转到用户列表页面
		return "redirect:/list";
	}

	@RequestMapping("/toEdit")
	public String toEdit(Model model, Long id) {
		User user = userRepository.findById(id);
		model.addAttribute("user", user);
		return "user/userEdit";
	}

	@RequestMapping("/edit")
	public String edit(@Valid UserParam userParam, BindingResult result, ModelMap modelMap) {
		String errorMsg = "";
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			for (ObjectError error : errorList) {
				errorMsg = errorMsg + error.hashCode() + "-" + error.getDefaultMessage() + ";";
			}
			return "user/userEdit";
		}
		User user = new User();
		BeanUtils.copyProperties(userParam, user);
		user.setRegTime(new Date());
		userRepository.save(user);
		return "redirect:/list";
	}

	@RequestMapping("/delete")
	public String delete(Long id) {
		userRepository.delete(id);
		return "redirect:list";
	}
}
