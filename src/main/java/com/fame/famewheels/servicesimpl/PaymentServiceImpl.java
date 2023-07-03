package com.fame.famewheels.servicesimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.PaymentDto;
import com.fame.famewheels.entities.Payment;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.PaymentRepository;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PaymentDto createMember(PaymentDto payment, Integer UserId) {
		
		User user=this.userRepo.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));
		
		Payment member=this.modelMapper.map(payment, Payment.class);
		member.setUser(user);
		member.setStatus("Active");
		
		try {
			
			Payment mpayment=this.paymentRepo.save(member);
			return this.modelMapper.map(mpayment,  PaymentDto.class);
		}catch(Exception e) {
			throw new RuntimeException("Sorry member can not be created!", e);
		}
	
	}

}
