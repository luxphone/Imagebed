package ee.ifl.imagebedapi.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import ee.ifl.imagebedapi.entity.Imagebed;
import ee.ifl.imagebedapi.mapper.ImagebedMapper;
import ee.ifl.imagebedapi.service.ImagebedService;
import org.springframework.stereotype.Service;

@Service
public class ImagebedServiceImpl extends ServiceImpl<ImagebedMapper, Imagebed> implements ImagebedService {
}
