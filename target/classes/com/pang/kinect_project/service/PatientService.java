package com.pang.kinect_project.service;

import com.pang.kinect_project.pojo.Patient;
import com.pang.kinect_project.result.Result;

import java.util.List;

public interface PatientService extends BaseService<Patient>{
    Result insertPatient(Patient patient);

    boolean selectPatientID(String patientID);

    List<Patient> selectPatientById(String patientId);
    List<Patient> selectPatientByPhone(String patientPhone);

}
