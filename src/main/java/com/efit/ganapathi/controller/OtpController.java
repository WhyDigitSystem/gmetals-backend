package com.efit.ganapathi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efit.ganapathi.dto.ResponseDTO;
import com.efit.ganapathi.service.OtpService;

@RestController
@RequestMapping("/email")
public class OtpController {

	public static final Logger LOGGER = LoggerFactory.getLogger(OtpController.class);

    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

//    @PostMapping("/send-otp")
//    public ResponseEntity<?> sendOtp(@RequestParam String email) {
//        otpService.sendOtp(email);
//        return ResponseEntity.ok("OTP sent");
//    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ResponseDTO> verifyOtp(
            @RequestParam String email,
            @RequestParam String otp) {

        ResponseDTO response = new ResponseDTO();

        boolean ok = otpService.verifyOtp(email, otp);

        if (ok) {
            response.setStatusFlag(ResponseDTO.OK);
            response.setStatus(true);
            response.addObject1("message","OTP verified successfully.");
            response.addObject1("email", email);
            response.addObject1("verified",true);

            return ResponseEntity.ok(response);
        } 
        else {
            response.setStatusFlag(ResponseDTO.ERROR);
            response.setStatus(false);
            response.addObject1("message","Invalid or expired OTP.");
            response.addObject1("email",email);
            response.addObject1("verified",false);

            return ResponseEntity.status(400).body(response);
        }
    }

}