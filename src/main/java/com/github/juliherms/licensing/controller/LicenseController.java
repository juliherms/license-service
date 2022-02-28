package com.github.juliherms.licensing.controller;

import com.github.juliherms.licensing.model.License;
import com.github.juliherms.licensing.service.LicenseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * This endpoints resposbile to maintain licenses by organization
 */
@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    /**
     * This method responsible to get License by organization and license id.
     * @param organizationId
     * @param licenseId
     * @return
     */
    @RequestMapping(value = "/{licenseId}",method = RequestMethod.GET)
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId){

        License license = licenseService.getLicense(licenseId,organizationId);
        return ResponseEntity.ok(license);
    }

    /**
     * This method responsible to update license by organizationId
     * @param organizationId
     * @param request
     * @return
     */
    @PutMapping
    public ResponseEntity<String> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request){

        return ResponseEntity.ok(licenseService.updateLicense(request,organizationId));

    }

    /**
     * This method responsible to create License by organization
     * @param organizationId
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale){

        return ResponseEntity.ok(licenseService.createLicense(request,organizationId,locale));
    }

    /**
     * This method responsible to delete license by organization
     * @param organizationId
     * @param licenseId
     * @return
     */
    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId){

        return ResponseEntity.ok(licenseService.deleteLicense(licenseId,organizationId));
    }



    }
