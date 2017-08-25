package com.archsystemsinc.ipms.spring.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource( "classpath*:jasper-views.xml" )
public class JasperView{
	
	public JasperView(){
		super();
	}
	
}
