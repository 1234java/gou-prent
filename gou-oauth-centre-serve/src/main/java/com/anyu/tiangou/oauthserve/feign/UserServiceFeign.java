package com.anyu.tiangou.oauthserve.feign;

import com.anyu.tiangou.user.service.api.IUserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart Administrator
 * @create 2020-07-30 16:12
 */
@FeignClient(name="user")
public interface UserServiceFeign  extends IUserService {
}
