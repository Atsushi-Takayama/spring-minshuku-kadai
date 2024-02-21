package com.example.samuraitravel.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.service.ReviewService;
@Controller
public class ReviewController {
  private final ReviewRepository reviewRepository;
  private final ReviewService reviewService;
  
  public ReviewController(ReviewRepository reviewRepository,HouseRepository houseRepository,ReviewService reviewService) {
    this.reviewRepository = reviewRepository;
    this.reviewService = reviewService;
  }
  @GetMapping
  public String index(Model model,@PageableDefault(page = 0, size = 6, sort = "id", direction = Direction.ASC)Pageable pageable) {
	   Page<Review> reviewPage;   
		   reviewPage = reviewRepository.findAll(pageable);
	   model.addAttribute("reviewPage", reviewPage);
	   return "houses/show";
  }
  @GetMapping("/register")
  public String register(Model model) {
	   model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
	   return "review/register";
  }
  
  @PostMapping("/create")
  public String create(@ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
	   if(bindingResult.hasErrors()) {
		   return "review/register";
	   }
	   reviewService.create(reviewRegisterForm);
	   redirectAttributes.addFlashAttribute("successMessage", "レビューを登録しました。");
	   return "redirect:/houses/show";
  }
  
  @GetMapping("/{id}/edit")
  public String edit(@PathVariable(name = "id") Integer id, Model model) {
	   Review review = reviewRepository.getReferenceById(id);
	   ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getStar(),review.getReview());
	
	   model.addAttribute("reviewEditForm", reviewEditForm);
	   return "review/edit";

  }
  
  @PostMapping("/{id}/update")
  public String update(@ModelAttribute@Validated ReviewEditForm reviewEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
	   if(bindingResult.hasErrors()) {
		   return "review/edit";
	   }
	   reviewService.update(reviewEditForm);
	   redirectAttributes.addFlashAttribute("successMessage", "レビューを編集しました。");
	   return "redirect:/houses/show";

  }
  
  @PostMapping("/{id}/delete")
  public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
	   reviewRepository.deleteById(id);
	   redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
	   return "redirect:/houses/show";
  }
  
}


