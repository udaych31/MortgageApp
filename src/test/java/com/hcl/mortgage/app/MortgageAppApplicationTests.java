package com.hcl.mortgage.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.mortgage.app.dto.LoanDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MortgageAppApplicationTests {

	@Test
	public void contextLoads() {
		  LoanDTO loanDTO=new LoanDTO();
		  assertThat(loanDTO.getId()).isNull();	
	}

}
