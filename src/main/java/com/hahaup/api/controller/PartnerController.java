package com.hahaup.api.controller;

import com.hahaup.api.model.dto.BaseResponse;
import com.hahaup.api.model.dto.IdDto;
import com.hahaup.api.model.dto.partner.CreatePartnerRequest;
import com.hahaup.api.model.dto.partner.PartnerResponse;
import com.hahaup.api.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping("/create")
    public BaseResponse<IdDto<String>> create(@Valid @RequestBody CreatePartnerRequest request) {
        return new BaseResponse<>(partnerService.createPartner(request));
    }

    @PostMapping("/{externalUserId}/add-photo")
    public BaseResponse<?> addPhoto(@PathVariable String externalUserId,
                                    @RequestParam("file") MultipartFile file) {
        partnerService.addPhoto(externalUserId, file);
        return new BaseResponse<>();
    }

    @GetMapping("/public/{username}")
    public BaseResponse<PartnerResponse> getInfo(@PathVariable String username) {
        return new BaseResponse<>(partnerService.getInfo(username));
    }

    @GetMapping("/public/all")
    public BaseResponse<List<PartnerResponse>> getAllPartners() {
        return new BaseResponse<>(partnerService.getAllPartners());
    }
}
