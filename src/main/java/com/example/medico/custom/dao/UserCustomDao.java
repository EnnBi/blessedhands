package com.example.medico.custom.dao;

import com.example.medico.model.VerificationToken;

public interface UserCustomDao {
	
	public void createVerificationToken(VerificationToken verificationToken);
	public VerificationToken getVerificationToken(String token);
	public void deleteVerificationToken(VerificationToken verificationToken);
}
