package com.topdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topdata.model.Profile;
import com.topdata.repository.ProfileRepository;



@Service
public class ProfileService {

	@Autowired
	private final ProfileRepository profileRepo;
	
	
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepo = profileRepository;
    }
	
	public Profile save(Profile profile) {
		return this.profileRepo.save(profile);
    }
	 public Profile findById(Object id) {
	        Long profileId = Long.parseLong(id.toString());
	        return profileRepo.findById(profileId).orElse(null);
	 }
}
