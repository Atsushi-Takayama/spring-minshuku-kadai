package com.example.samuraitravel.service;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.ReviewRepository;
@Service
public class ReviewService {
   private final ReviewRepository reviewRepository; 
   
   public ReviewService(ReviewRepository reviewRepository) {
   this.reviewRepository = reviewRepository;        
   }
   
   @Transactional
   public void create(ReviewRegisterForm reviewRegisterForm) {
	   Review review = new Review();
	   
	   review.setStar(reviewRegisterForm.getStar());
	   review.setReview(reviewRegisterForm.getReview());
	   
	   reviewRepository.save(review);
   }
   
   @Transactional
   public void update(ReviewEditForm reviewEditForm) {
 	  Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
 	 
 	  review.setId(reviewEditForm.getId()); 
 	  review.setStar(reviewEditForm.getStar());
 	  review.setReview(reviewEditForm.getReview());
 	  reviewRepository.save(review);
   }
   public String generateNewFileName(String fileName) {
	   String[] fileNames = fileName.split("\\.");                
	   for (int i = 0; i < fileNames.length - 1; i++) {
		   fileNames[i] = UUID.randomUUID().toString();            
		   }
	   String hashedFileName = String.join(".", fileNames);
	   return hashedFileName;
   }   
}
