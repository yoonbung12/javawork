package com.bitcamp.mm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitcamp.mm.service.EditService;

public class EditController {

	@Autowired
	EditService editServie;
	
	@RequestMapping(value = "/member/edit/{id}", method = RequestMethod.GET)
	public String dispatchEditForm(
					@PathVariable String id,
					Model model) {
			model.addAttribute("memberid", id);
			return "member/editForm";
	}
	
	@RequestMapping(value = "/member/edit", method = RequestMethod.POST)
	public String dispatchEditView(HttpServletRequest request) {
		try {
				editServie.editMember(request);
		} catch (FileUploadException e) {
				e.printStackTrace();
				request.setAttribute("result", 0);
		}
		return "member/editView";
	}
}
