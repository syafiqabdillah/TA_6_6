package com.apap.farmasi.service;

import java.io.IOException;
import java.util.List;

import com.apap.farmasi.model.StaffModel;

public interface StaffService {
	List<StaffModel> getAll() throws IOException;
}
