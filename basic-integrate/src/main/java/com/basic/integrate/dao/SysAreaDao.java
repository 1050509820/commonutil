package com.basic.integrate.dao;

import java.util.List;
import java.util.Map;

import com.basic.integrate.dto.SysAreaDTO;

public interface SysAreaDao {
    List<SysAreaDTO> getAreas(Map<?, ?> map);
}
