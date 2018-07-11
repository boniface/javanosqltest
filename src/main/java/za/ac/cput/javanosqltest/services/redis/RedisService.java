package za.ac.cput.javanosqltest.services.redis;

import za.ac.cput.javanosqltest.domain.Result;

public interface RedisService {
    Result create(Long number);
    Result read();
    Result update();
    Result delete();
}
