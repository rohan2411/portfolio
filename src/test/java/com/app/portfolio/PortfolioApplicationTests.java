package com.app.portfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.app.portfolio.repository.SecurityRepository;
import com.app.portfolio.service.MarketDataProvider;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PortfolioApplicationTests {

	@Autowired
	private MarketDataProvider marketDataProvider;

	@MockBean
	private SecurityRepository securityRepository;

	@Test
	void contextLoads() {
		assertThat(marketDataProvider).isNotNull();
	}

	@Test
	void marketDataProviderBeanExists() {
		assertThat(marketDataProvider).isNotNull();
	}

}

