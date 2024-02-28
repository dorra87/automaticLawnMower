package com.alm.service;


import com.alm.model.AutomaticLawnMower;

public interface AutomaticLawnMowerService {
    AutomaticLawnMower execute(AutomaticLawnMower automaticLawnMower, String instructionsStr);
}
