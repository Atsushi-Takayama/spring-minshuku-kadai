package com.example.samuraitravel.form;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewEditForm {
	@NotNull
	private Integer id;
	
	@NotBlank(message = "評価を選択してください")
	private Integer star;
	
	@NotBlank(message = "評価を入力してください")
	private String review;


}
