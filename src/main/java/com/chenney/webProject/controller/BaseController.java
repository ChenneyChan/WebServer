package com.chenney.webProject.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.chenney.webProject.bean.ProjectInfo;
import com.chenney.webProject.bean.ResultBody;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@ResponseBody
@Slf4j
public class BaseController {
    Random random = new Random(System.currentTimeMillis());

    @GetMapping(value = "/test")
    public ResultBody getMethodName() {
        log.info("get test");
        return ResultBody.ok(null);
    }

    @GetMapping(value = {"/projects", "/smart/eipWorkOrder/queryWorkOrder"})
    public ResultBody getProjects(@RequestParam(name = "woNo", defaultValue = "0") String key) {
        List<ProjectInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProjectInfo projectInfo = new ProjectInfo();
            projectInfo.setWoNo("WoNo_" + String.valueOf(i));
            projectInfo.setEntityCode("entity_code_" + i);
            projectInfo.setModelSpec("model_spec_" + i);
            projectInfo.setMaterialsCode("materials_code_" + i);
            list.add(projectInfo);
        }
        if (key != null && key.compareTo("0") != 0) {
            list.remove(random.nextInt(list.size()));
        }
        return ResultBody.ok(list);
    }

    @PostMapping(value = "/post")
    public ResultBody postData(MultipartFile waveForm, HttpServletRequest request) {
        if (waveForm != null) {
            log.info("image name = " + waveForm.getOriginalFilename() + " size = " + waveForm.getSize());
        }
        Enumeration<String> utils = request.getParameterNames();
        while (utils.hasMoreElements()) {
            String key = utils.nextElement();
            String value = request.getParameter(key);
            log.info("key = \"{}\" value = {}", key, value);
        }
        return ResultBody.ok(null);
    }

    @PostMapping(value = {"/post_map", "/wg/zhdq/ldcj_sc", "/wg/zhdq/gp_sc", "/wg/zhdq/jf_sc"})
    public ResultBody postMap(@RequestParam(value = "waveForm", required = false) MultipartFile waveForm,
            @RequestParam Map<String, Object> testData) {
        if (waveForm != null) {
            log.info("file name = " + waveForm.getOriginalFilename() + " size = " + waveForm.getSize());
        }

        Set<String> keys = testData.keySet();
        keys.forEach((k) -> {
            log.info("Key = {} value = {}", k, testData.get(k));
        });

        return ResultBody.ok(null);
    }
}
