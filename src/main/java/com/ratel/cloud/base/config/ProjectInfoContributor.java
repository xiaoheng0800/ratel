package com.ratel.cloud.base.config;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.ratel.cloud.base.util.AbstractBaseController;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  ProjectInfoContributor.java   
 * @Package com.ratel.cloud.base.config   
 * @Description: rest方式对外公布微服务信息
 * @author: gaoheng
 * @date:   2017年11月25日 下午10:32:20   
 * @version V1.0
 */
@Component
public class ProjectInfoContributor extends AbstractBaseController implements InfoContributor {
    @Override
	public void contribute(Builder builder) {
		builder.withDetail("company.name", super.getMessage("company.name")) ;
		builder.withDetail("project.name", super.getMessage("project.name")) ;
		builder.withDetail("version", super.getMessage("version")) ;
		builder.withDetail("author",  super.getMessage("author")) ;
		builder.withDetail("deploy.date",  super.getMessage("deploy.date")) ;
	}

}
