package com.example.samuraitravel.form;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRegisterForm {
	@NotBlank(message = "星の数を入力してください。")
	@Min(value = 1, message = "星の数は1つ以上に設定してください。")
	private Integer star;
	
	@NotNull(message = "レビューを入力してください。")
	private String review;
}
