package com.skilldistillery.journeyjournals.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.journeyjournals.entities.DestinationImage;
import com.skilldistillery.journeyjournals.entities.PlaceImage;
import com.skilldistillery.journeyjournals.services.DestinationImageService;
import com.skilldistillery.journeyjournals.services.PlaceImageService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class ImageController {
	
	@Autowired
	private DestinationImageService service;
	
	@Autowired 
	private PlaceImageService placeService;
	
	
	@GetMapping("destinationImages")
	private List<DestinationImage> listOfImagesByUser(HttpServletResponse res, Principal principal) {
		List<DestinationImage> image = service.index(principal.getName());
		if(image == null) {
			res.setStatus(404);
			return null;
		}
		return image;
	}
	
	@GetMapping("dImages")
	private List<DestinationImage> listOfImagesAll(HttpServletResponse res) {
		List<DestinationImage> image = service.index();
		if(image == null) {
			res.setStatus(404);
			return null;
		}
		return image;
	}
	
	@GetMapping("destinationImages/{id}")
	private DestinationImage showById(HttpServletResponse res, @PathVariable int id, Principal principal) {
		DestinationImage image = service.show(principal.getName(), id);
		if(image == null) {
			res.setStatus(404);
		}
		return image;
	}
	
	@PostMapping("destinationImages/{id}/destinations")
	private DestinationImage create(HttpServletResponse res, @RequestBody DestinationImage des,  @PathVariable int id, Principal principal) {
		try {
			DestinationImage image = service.create(principal.getName(), des, id);
			return image;
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}	
	}
	
	@PutMapping("destinationImages/{id}")
		private DestinationImage update(HttpServletResponse res, @PathVariable int id, @RequestBody DestinationImage des, Principal principal) {
		try {
			des = service.update(principal.getName(), id, des);
			res.setStatus(201);
			return des;
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@DeleteMapping("destinationImages/{id}")
	private void destory(HttpServletResponse res,@PathVariable int id, Principal principal) {
		if(id != 0) {
			service.destroy(principal.getName(), id);
			res.setStatus(204);
		}else {
			res.setStatus(404);
		}
		
	}
	
	@GetMapping("placeImages")
	private List<PlaceImage> indexAll() {
		return placeService.index();
	}
	
	@GetMapping("placeImagesUser")
	private List<PlaceImage> indexAllByUser(Principal principal){
		List<PlaceImage> images = placeService.index(principal.getName());
		return images;
	}
	
	@PostMapping("placeImages/{id}/place")
	private PlaceImage createPlaceImage(@RequestBody PlaceImage image, Principal principal,@PathVariable int id, HttpServletResponse res) {
		try {
			PlaceImage createdImage = placeService.create(principal.getName(), image, id);
			return createdImage;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@PutMapping("placeImages/{id}")
	private PlaceImage updatePlaceImage(@RequestBody PlaceImage image, @PathVariable int id, Principal principal, HttpServletResponse res) {
		
		try {
			image = placeService.update(principal.getName(), id, image);
	         res.setStatus(201);
	         return image;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@DeleteMapping("placeImages/{id}")
	private void deletePlaceImage(@PathVariable int id, Principal principal, HttpServletResponse res) {
		if(placeService.destroy(principal.getName(), id)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}
	

}
