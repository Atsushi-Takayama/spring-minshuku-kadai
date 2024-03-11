package com.example.samuraitravel.form;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEditForm {
	@NotNull
	private Integer id;
	
	@NotNull
	private Integer houseId;
	
	@NotNull
	private Integer userId;
	
	@NotBlank(message = "評価を選択してください")
	private String star;
	
	@NotBlank(message = "評価を入力してください")
	private String review;


	


}
