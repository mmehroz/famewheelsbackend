package com.fame.famewheels.services;

import com.fame.famewheels.dto.PaymentDto;

public interface PaymentService {

	
	PaymentDto createMember(PaymentDto payment, Integer UserId);
}
