package com.foodbox.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VerifiedCredential {
	private String key;
	private boolean isNewUser;
}
