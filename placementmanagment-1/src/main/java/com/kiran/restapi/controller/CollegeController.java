package com.kiran.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiran.restapi.entity.College;
import com.kiran.restapi.entity.User;
import com.kiran.restapi.repository.CollegeRepository;
import com.kiran.restapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CollegeController {

	@Autowired
	CollegeRepository collegeRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/colleges/all")
	public ResponseEntity<Object> getAllColleges() {
		List<College> allColleges = collegeRepository.findAll();

		if (!allColleges.isEmpty()) {
			return new ResponseEntity<>(allColleges, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No colleges found", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/colleges/add")
	public ResponseEntity<Object> addCollege(@RequestBody College college) {
		try {
			// Check if the college with the same name and location already exists
			Optional<College> existingCollege = collegeRepository
					.findByCollegeNameAndLocation(college.getCollege_name(), college.getLocation());

			if (existingCollege.isPresent()) {
				// College with the same name and location already exists, return an error
				// response
				return new ResponseEntity<>("College with the same name and location already exists.",
						HttpStatus.CONFLICT);
			}

			// Check if the college_admin exists based on the provided credentials
			User collegeAdmin = college.getCollege_admin();
			Optional<User> existingUser = userRepository.findByNameAndPassword(collegeAdmin.getName(),
					collegeAdmin.getPassword());

			if (existingUser.isPresent()) {
				// User with the provided credentials exists, proceed to create the college

				college.setCollege_admin(collegeAdmin);
				collegeRepository.save(college);
				// Clear the password before saving the college
				college.getCollege_admin().setPassword(null);
				return new ResponseEntity<>(college, HttpStatus.CREATED);
			} else {
				// User with the provided credentials not found, return an error response
				return new ResponseEntity<>("Invalid college_admin credentials. User not found.",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// Log the exception for debugging purposes
			e.printStackTrace();
			return new ResponseEntity<>("Failed to add college. Check server logs for details.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/colleges/update/{id}")
	public ResponseEntity<Object> patchUpdateCollege(@PathVariable long id, @RequestBody Map<String, Object> updates) {
	    try {
	        Optional<College> optionalCollege = collegeRepository.findById(id);
	        ObjectMapper objectMapper = new ObjectMapper();

	        if (optionalCollege.isPresent()) {
	            College college = optionalCollege.get();

	            // Check if college_admin data is provided in the updates
	            if (updates.containsKey("college_admin")) {
	                User updatedAdmin = objectMapper.convertValue(updates.get("college_admin"), User.class);

	                // Check if the provided admin credentials are valid
	                Optional<User> existingUser = userRepository.findByNameAndPassword(updatedAdmin.getName(),
	                        updatedAdmin.getPassword());

	                if (!existingUser.isPresent()) {
	                    // Invalid admin credentials, return an error response
	                    return new ResponseEntity<>("Invalid college_admin credentials. User not found.",
	                            HttpStatus.BAD_REQUEST);
	                }
	                // Check if the updated admin is the same as the current college admin
	                if (college.getCollege_admin().getId() != updatedAdmin.getId()) {
	                    // Admins are different, return an error response
	                    return new ResponseEntity<>("Invalid college_admin credentials. Admins do not match.",
	                            HttpStatus.BAD_REQUEST);
	                }
	                
	                // Update college_name if present in the request
	                if (updates.containsKey("college_name")) {
	                    String newCollegeName = (String) updates.get("college_name");

	                    // Check if the new college_name already exists
	                    Optional<College> existingCollegeWithSameName = collegeRepository
	                            .findByCollegeNameAndLocation(newCollegeName, college.getLocation());

	                    if (existingCollegeWithSameName.isPresent()) {
	                        // College with the same name already exists, return an error response
	                        return new ResponseEntity<>("College with the same name already exists.",
	                                HttpStatus.CONFLICT);
	                    }

	                    college.setCollege_name(newCollegeName);
	                }

	                // Update location if present in the request
	                if (updates.containsKey("location")) {
	                    college.setLocation((String) updates.get("location"));
	                }

	                // Save the updated college
	                collegeRepository.save(college);
	             // Clear the password before saving the college
					college.getCollege_admin().setPassword(null);
	                return new ResponseEntity<>(college, HttpStatus.OK);
	            }
	        } else {
	            // College with the given ID not found, return a not found response
	            return new ResponseEntity<>("College with the provided ID not found.", HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>("Unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
	    } catch (Exception e) {
	        // Log the exception for debugging purposes
	        e.printStackTrace();
	        return new ResponseEntity<>("Failed to update college. Check server logs for details.",
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PatchMapping("/colleges/admin/update/{id}")
	public ResponseEntity<Object> UpdateCollegeAdmin(@PathVariable long id, @RequestBody Map<String, Object> updates) {
		try {
			Optional<College> optionalCollege = collegeRepository.findById(id);
			ObjectMapper objectMapper = new ObjectMapper();

			if (optionalCollege.isPresent()) {
				College college = optionalCollege.get();

				// Check if college_admin data is provided in the updates
				if (updates.containsKey("college_admin")) {
					User updatedAdmin = objectMapper.convertValue(updates.get("college_admin"), User.class);
					User newAdmin = objectMapper.convertValue(updates.get("new_college_admin"), User.class);

					// Check if the provided admin credentials are valid
					Optional<User> existingUser = userRepository.findByNameAndPassword(updatedAdmin.getName(),
							updatedAdmin.getPassword());

					if (!existingUser.isPresent()) {
						// Invalid admin credentials, return an error response
						return new ResponseEntity<>("Invalid college_admin credentials. User not found.",
								HttpStatus.BAD_REQUEST);
					}

					// Check if the provided new admin credentials are valid
					Optional<User> newExistingUser = userRepository.findByNameAndPassword(newAdmin.getName(),
					        newAdmin.getPassword());


					if (!newExistingUser.isPresent()) {
						// Invalid admin credentials, return an error response
						return new ResponseEntity<>("Invalid new college_admin credentials. User not found.",
								HttpStatus.BAD_REQUEST);
					}

					// Check if the updated admin is the same as the current college admin
					if (college.getCollege_admin().getId() != updatedAdmin.getId()) {
						// Admins are different, return an error response
						return new ResponseEntity<>("Invalid college_admin credentials. Admins do not match.",
								HttpStatus.BAD_REQUEST);
					}

					college.setCollege_admin(newExistingUser.get());
					// Save the updated college
					collegeRepository.save(college);
					// Clear the password before saving the college
					college.getCollege_admin().setPassword(null);
					return new ResponseEntity<>(college, HttpStatus.OK);
				}
			} else {
				// College with the given ID not found, return a not found response
				return new ResponseEntity<>("College with the provided ID not found.", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>("Unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			// Log the exception for debugging purposes
			e.printStackTrace();
			return new ResponseEntity<>("Failed to update college. Check server logs for details.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/colleges/{id}")
	public ResponseEntity<Object> getCollege(@PathVariable long id) {
	    Optional<College> optionalCollege = collegeRepository.findById(id);

	    if (optionalCollege.isPresent()) {
	        College college = optionalCollege.get();

	        
	        college.setCollege_admin(null);
	        return new ResponseEntity<>(college, HttpStatus.OK);
	    } else {
	        // Return a more informative response including the error message
	        return new ResponseEntity<>("College with the provided ID not found.", HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/colleges/{id}")
		public ResponseEntity<Object> deleteCollge(@PathVariable long id, @RequestBody Map<String, Object> updates) {
			try {
				Optional<College> optionalCollege = collegeRepository.findById(id);
				ObjectMapper objectMapper = new ObjectMapper();

				if (optionalCollege.isPresent()) {
					College college = optionalCollege.get();

					// Check if college_admin data is provided in the updates
					if (updates.containsKey("college_admin")) {
						User updatedAdmin = objectMapper.convertValue(updates.get("college_admin"), User.class);
						// Check if the provided admin credentials are valid
						Optional<User> existingUser = userRepository.findByNameAndPassword(updatedAdmin.getName(),
								updatedAdmin.getPassword());

						if (!existingUser.isPresent()) {
							// Invalid admin credentials, return an error response
							return new ResponseEntity<>("Invalid college_admin credentials. User not found.",
									HttpStatus.BAD_REQUEST);
						}


						// Check if the updated admin is the same as the current college admin
						if (college.getCollege_admin().getId() != updatedAdmin.getId()) {
							// Admins are different, return an error response
							return new ResponseEntity<>("Invalid college_admin credentials. Admins do not match.",
									HttpStatus.BAD_REQUEST);
						}

					        collegeRepository.delete(college);
					        String successMessage = "College '" + college.getCollege_name() + "' with ID " + college.getId() + " deleted successfully.";
					        return new ResponseEntity<>(successMessage, HttpStatus.OK);
					}
				} else {
					// College with the given ID not found, return a not found response
					return new ResponseEntity<>("College with the provided ID not found.", HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>("Unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception e) {
				// Log the exception for debugging purposes
				e.printStackTrace();
				return new ResponseEntity<>("Failed to update college. Check server logs for details.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

//    @PostMapping("/colleges/{id}/placements/schedule")
//    public ResponseEntity<Boolean> schedulePlacement(@PathVariable long id, @RequestBody Placement placement) {
//        Optional<College> optionalCollege = collegeRepository.findById(id);
//
//        if (optionalCollege.isPresent()) {
//            College college = optionalCollege.get();
//            placement.setCollege(college);  // Set the college for the placement
//            placementController.addPlacement(placement);
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
//        }
//    }
}
