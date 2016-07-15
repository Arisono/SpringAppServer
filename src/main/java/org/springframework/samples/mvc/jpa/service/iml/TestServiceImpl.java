package org.springframework.samples.mvc.jpa.service.iml;

import org.springframework.samples.mvc.jpa.service.TestService;
import org.springframework.stereotype.Service;

@Service("TestService")
public class TestServiceImpl implements TestService {

	@Override
	public void sys() {
       System.out.println("子接口打印程序");
	}

}
