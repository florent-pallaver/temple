<?php

namespace temple\_generated\org\lesgaillards\model ;

/**
 * Generated class, dot not modify
 */
class UserProxy extends \org\lesgaillards\model\User {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getName() {
		$this->lazyLoad() ;
		return $this->instance->getName();
	}
	public function getEmail() {
		$this->lazyLoad() ;
		return $this->instance->getEmail();
	}
	public function setEmail($email) {
		$this->lazyLoad() ;
		return $this->instance->setEmail($email);
	}
	public function getHash() {
		$this->lazyLoad() ;
		return $this->instance->getHash();
	}
	public function setHash($password) {
		$this->lazyLoad() ;
		return $this->instance->setHash($password);
	}
	public function getGender() {
		$this->lazyLoad() ;
		return $this->instance->getGender();
	}
	public function setGender($gender) {
		$this->lazyLoad() ;
		return $this->instance->setGender($gender);
	}
	public function getType() {
		$this->lazyLoad() ;
		return $this->instance->getType();
	}
	public function getLastname() {
		$this->lazyLoad() ;
		return $this->instance->getLastname();
	}
	public function getFirstname() {
		$this->lazyLoad() ;
		return $this->instance->getFirstname();
	}
	public function getSubscription() {
		$this->lazyLoad() ;
		return $this->instance->getSubscription();
	}
	public function getPaymentMode() {
		$this->lazyLoad() ;
		return $this->instance->getPaymentMode();
	}
	public function getInsurance() {
		$this->lazyLoad() ;
		return $this->instance->getInsurance();
	}
	public function getJob() {
		$this->lazyLoad() ;
		return $this->instance->getJob();
	}
	public function getAddress1() {
		$this->lazyLoad() ;
		return $this->instance->getAddress1();
	}
	public function getAddress2() {
		$this->lazyLoad() ;
		return $this->instance->getAddress2();
	}
	public function getZip() {
		$this->lazyLoad() ;
		return $this->instance->getZip();
	}
	public function getCity() {
		$this->lazyLoad() ;
		return $this->instance->getCity();
	}
	public function getPhone() {
		$this->lazyLoad() ;
		return $this->instance->getPhone();
	}
	public function getBirthdate() {
		$this->lazyLoad() ;
		return $this->instance->getBirthdate();
	}
	public function getEmergencyContact() {
		$this->lazyLoad() ;
		return $this->instance->getEmergencyContact();
	}
	public function getEmergencyPhone() {
		$this->lazyLoad() ;
		return $this->instance->getEmergencyPhone();
	}
	public function getMedicalContact() {
		$this->lazyLoad() ;
		return $this->instance->getMedicalContact();
	}
	public function getMedicalPhone() {
		$this->lazyLoad() ;
		return $this->instance->getMedicalPhone();
	}
	public function getAllergies() {
		$this->lazyLoad() ;
		return $this->instance->getAllergies();
	}
	public function getBlood() {
		$this->lazyLoad() ;
		return $this->instance->getBlood();
	}
	public function getPhotoPolicy() {
		$this->lazyLoad() ;
		return $this->instance->getPhotoPolicy();
	}
	public function getPoloSize() {
		$this->lazyLoad() ;
		return $this->instance->getPoloSize();
	}
	public function getShortsSize() {
		$this->lazyLoad() ;
		return $this->instance->getShortsSize();
	}
	public function getSocksSize() {
		$this->lazyLoad() ;
		return $this->instance->getSocksSize();
	}
	public function getHosting() {
		$this->lazyLoad() ;
		return $this->instance->getHosting();
	}
	public function getPseudo() {
		$this->lazyLoad() ;
		return $this->instance->getPseudo();
	}
	public function getCreationDate() {
		$this->lazyLoad() ;
		return $this->instance->getCreationDate();
	}
	public function getValidationDate() {
		$this->lazyLoad() ;
		return $this->instance->getValidationDate();
	}
	public function getValidation() {
		$this->lazyLoad() ;
		return $this->instance->getValidation();
	}
	public function isActive() {
		$this->lazyLoad() ;
		return $this->instance->isActive();
	}
	public function setType($type) {
		$this->lazyLoad() ;
		return $this->instance->setType($type);
	}
	public function setLastname($lastname) {
		$this->lazyLoad() ;
		return $this->instance->setLastname($lastname);
	}
	public function setFirstname($firstname) {
		$this->lazyLoad() ;
		return $this->instance->setFirstname($firstname);
	}
	public function setSubscription($subscription) {
		$this->lazyLoad() ;
		return $this->instance->setSubscription($subscription);
	}
	public function setPaymentMode($payment) {
		$this->lazyLoad() ;
		return $this->instance->setPaymentMode($payment);
	}
	public function setInsurance($insurance) {
		$this->lazyLoad() ;
		return $this->instance->setInsurance($insurance);
	}
	public function setJob($job) {
		$this->lazyLoad() ;
		return $this->instance->setJob($job);
	}
	public function setAddress1($address1) {
		$this->lazyLoad() ;
		return $this->instance->setAddress1($address1);
	}
	public function setAddress2($address2) {
		$this->lazyLoad() ;
		return $this->instance->setAddress2($address2);
	}
	public function setZip($zip) {
		$this->lazyLoad() ;
		return $this->instance->setZip($zip);
	}
	public function setCity($city) {
		$this->lazyLoad() ;
		return $this->instance->setCity($city);
	}
	public function setPhone($phone) {
		$this->lazyLoad() ;
		return $this->instance->setPhone($phone);
	}
	public function setBirthdate($birthdate) {
		$this->lazyLoad() ;
		return $this->instance->setBirthdate($birthdate);
	}
	public function setEmergencyContact($emergency_contact) {
		$this->lazyLoad() ;
		return $this->instance->setEmergencyContact($emergency_contact);
	}
	public function setEmergencyPhone($emergency_phone) {
		$this->lazyLoad() ;
		return $this->instance->setEmergencyPhone($emergency_phone);
	}
	public function setMedicalContact($medical_contact) {
		$this->lazyLoad() ;
		return $this->instance->setMedicalContact($medical_contact);
	}
	public function setMedicalPhone($medical_phone) {
		$this->lazyLoad() ;
		return $this->instance->setMedicalPhone($medical_phone);
	}
	public function setAllergies($allergies) {
		$this->lazyLoad() ;
		return $this->instance->setAllergies($allergies);
	}
	public function setBlood($blood) {
		$this->lazyLoad() ;
		return $this->instance->setBlood($blood);
	}
	public function setPhotoPolicy($photoPolicy) {
		$this->lazyLoad() ;
		return $this->instance->setPhotoPolicy($photoPolicy);
	}
	public function setPoloSize($poloSize) {
		$this->lazyLoad() ;
		return $this->instance->setPoloSize($poloSize);
	}
	public function setShortsSize($shortsSize) {
		$this->lazyLoad() ;
		return $this->instance->setShortsSize($shortsSize);
	}
	public function setSocksSize($socksSize) {
		$this->lazyLoad() ;
		return $this->instance->setSocksSize($socksSize);
	}
	public function setHosting($hosting) {
		$this->lazyLoad() ;
		return $this->instance->setHosting($hosting);
	}
	public function setPseudo($pseudo) {
		$this->lazyLoad() ;
		return $this->instance->setPseudo($pseudo);
	}
	public function setCreationDate($creationDate) {
		$this->lazyLoad() ;
		return $this->instance->setCreationDate($creationDate);
	}
	public function setValidationDate($validationDate) {
		$this->lazyLoad() ;
		return $this->instance->setValidationDate($validationDate);
	}
	public function getExperience() {
		$this->lazyLoad() ;
		return $this->instance->getExperience();
	}
	public function setExperience($experience) {
		$this->lazyLoad() ;
		return $this->instance->setExperience($experience);
	}
	public function getPositions() {
		$this->lazyLoad() ;
		return $this->instance->getPositions();
	}
	public function setPositions($positions) {
		$this->lazyLoad() ;
		return $this->instance->setPositions($positions);
	}
	public function getGoal() {
		$this->lazyLoad() ;
		return $this->instance->getGoal();
	}
	public function setGoal($goal) {
		$this->lazyLoad() ;
		return $this->instance->setGoal($goal);
	}
	public function jsonSerialize() {
		$this->lazyLoad() ;
		return $this->instance->jsonSerialize();
	}
	public function __toString() {
		$this->lazyLoad() ;
		return $this->instance->__toString();
	}

}			