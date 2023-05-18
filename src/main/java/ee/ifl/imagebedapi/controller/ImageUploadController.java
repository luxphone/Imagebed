package ee.ifl.imagebedapi.controller;

import ee.ifl.imagebedapi.entity.Imagebed;
import ee.ifl.imagebedapi.model.Result;
import ee.ifl.imagebedapi.service.ImagebedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

@RestController
public class ImageUploadController {
    @Autowired
    ImagebedService imagebedService;
    private static final String IMAGE_DIRECTORY = System.getProperty("user.dir") + "/images/";
    private static final String PASSWORD = "taikula";

    @RequestMapping("/upload")
    public Result<String> upload(@RequestParam("image") MultipartFile image, @RequestParam("password") String password) {
        // 验证密码
        if (!password.equals(PASSWORD)) {
            return Result.error("Invalid password");
        }

        // 执行图片上传逻辑
        try {
            // 生成一个唯一的文件名
            String fileName = UUID.randomUUID() + "-" + image.getOriginalFilename();

            // 将图片保存到指定的目录
            Path filePath = Paths.get(IMAGE_DIRECTORY, fileName);
            Files.copy(image.getInputStream(), filePath);

            // 构建图片链接
            String imageUrl = "https://bed.ifl.ee/images/" + fileName;

            // 写入数据库
            Imagebed imagebed = new Imagebed();
            imagebed.setSrc(imageUrl);
            imagebed.setCreatetime(new Date());
            imagebedService.save(imagebed);

            return Result.success(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("Failed to upload image");
        }
    }
}
