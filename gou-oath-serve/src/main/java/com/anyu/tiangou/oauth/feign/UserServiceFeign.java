package com.anyu.tiangou.oauth.feign;

import com.anyu.tiangou.user.service.api.IUserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart Administrator
 * @create 2020-08-04 11:28
 */
@FeignClient(name="user")
public interface UserServiceFeign extends IUserService {
}
