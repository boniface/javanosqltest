package za.ac.cput.javanosqltest.services;

import za.ac.cput.javanosqltest.domain.Result;

public interface Service {
    Result create(Long number);
    Result read();
    Result update();
    Result delete();
}
