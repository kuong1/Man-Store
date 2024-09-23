package com.example.manstore.controller.admin;

import com.example.manstore.dto.respone.MauSacResponse;
import com.example.manstore.entity.MauSac;
import com.example.manstore.entity.ThuongHieu;
import com.example.manstore.repository.MauSacRepository;
import com.example.manstore.service.Impl.MauSacServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/color")
@CrossOrigin("*")
public class MauSacController {

    @Autowired
    private MauSacServiceImpl mauSacService;

    @Autowired
    private MauSacRepository mauSacRepository;

    @GetMapping("/getAll")
    public String getAllMauSac(Model model) {
        model.addAttribute("mausacs", mauSacService.getAllMauSac());
        return "color_list";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<?> getAllMauSac(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MauSacResponse> pageResult = mauSacRepository.findAllMauSac(pageable);

        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    private String update(Model model) {
        model.addAttribute("mausac", new MauSac());
        return "admin/color/color-create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute("mausac") MauSac mauSac
    ) {
        boolean isValid = false;

        //vòng for check mã
        for (MauSac s : mauSacService.getAllMauSac()) {
            if (mauSac.getMa().equalsIgnoreCase(s.getMa())) {
                isValid = true;
                model.addAttribute("errorMa", "Mã màu sắc đã tồn tại!");
            }
        }
        if (mauSac.getMa().isBlank()) {
            model.addAttribute("errorMa", "Mã màu sắc không được để trống!");
            isValid = true;
        }


        //vòng for check trùng tên
        for (MauSac s : mauSacService.getAllMauSac()) {
            if (mauSac.getTen().equalsIgnoreCase(s.getTen())) {
                isValid = true;
                model.addAttribute("errorName", "Tên màu sắc đã tồn tại!");
            }
        }
        if (mauSac.getTen().isBlank()) {
            model.addAttribute("errorName", "Tên màu sắc không được để trống!");
            isValid = true;
        }

        if (!isValid) {
            mauSacService.save(mauSac);
            model.addAttribute("message", true);
            return "admin/color/color-create";
        } else {
            model.addAttribute("message", false);
            return "admin/color/color-create";
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> detail(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(mauSacService.getMauSacById(id));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable(value = "id") Integer id, @ModelAttribute("th") MauSac mauSac, Model model) {
        MauSac ms = mauSacService.getMauSacById(id);
        ms.setMa(mauSac.getMa());
        ms.setTen(mauSac.getTen());
        mauSacService.update(mauSac);
        model.addAttribute("updateSuccess", true);
        return "admin/color/color-list";
    }

    @GetMapping(value = "/page/search/{pageNumber}/{keyWord}")
    public ResponseEntity<?> getPageSearchAndFilter(
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("keyWord") String keyWord
    ) {
        Pageable pageable = PageRequest.of(pageNumber, 3, Sort.by("id").descending());
        Page<MauSac> page;

        if (keyWord.equalsIgnoreCase("null")) {
            page = mauSacService.pageOfMauSac(pageable);
        } else {
            page = mauSacRepository.searchMauSac(keyWord, pageable);
        }

        return new ResponseEntity<>(page, HttpStatus.OK);
    }


}
