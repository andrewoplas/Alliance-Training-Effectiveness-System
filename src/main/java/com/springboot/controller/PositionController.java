package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.Position;
import com.springboot.service.PositionService;

@Controller
@RequestMapping("/ates/users")
public class PositionController {
	
	@Autowired
	private PositionService positionService;
	
	
	@RequestMapping(value = "/position")
	public String displayPosition(ModelMap map) {
		// Display table of positions
		List<Position> positions = positionService.retrievePositions();
		
		map.addAttribute("positions", positions);
		return "/users/position";
	}
	
	@RequestMapping(value = "/position", method = RequestMethod.POST)
	public ResponseEntity<?> insertPosition(HttpServletRequest request) {
		// Request to insert position
		String description = request.getParameter("position");
		
		String result = positionService.insertPosition(description);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/position/edit", method = RequestMethod.POST)
	public ResponseEntity<?> editPosition(HttpServletRequest request) {
		// Request to edit position
		String description = request.getParameter("new-position");
		String id = request.getParameter("old-position");
		
		String result = positionService.editPosition(id, description);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/position/remove", method = RequestMethod.POST)
	public ResponseEntity<?> removePosition(HttpServletRequest request) {
		// request to remove position
		String id = request.getParameter("id");
		
		String result = positionService.removePosition(id);
		
		return ResponseEntity.ok(result);
	}
	
	
}